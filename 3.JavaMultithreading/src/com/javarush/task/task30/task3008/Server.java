package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Rumata on 17.02.2017.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        int serverPort = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (Exception e) {
            ConsoleHelper.writeMessage(e.getLocalizedMessage());
        }
        ConsoleHelper.writeMessage("Server starts");
        Socket socket;
        Handler handler;
        while (true) {
            socket = null;
            try {
                socket = serverSocket.accept();
            } catch (Exception e) {
                serverSocket.close();
                ConsoleHelper.writeMessage(e.getMessage());
                break;
            }
            if (socket != null) {
                handler = new Handler(socket);
                handler.start();
            }

        }
    }

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for (String key : connectionMap.keySet()) {
            try {
                connectionMap.get(key).send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Can`t send a message to " + key);
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message reply;
            String name;
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                reply = connection.receive();
                if (reply != null && reply.getType().equals(MessageType.USER_NAME)) {
                    name = reply.getData();
                    if (name != null && !name.isEmpty() && !connectionMap.containsKey(name)) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return name;
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            if (connection != null && userName != null) {
                for (String NAME_IN_MAP : connectionMap.keySet()) {
                    if (!userName.equals(NAME_IN_MAP) && NAME_IN_MAP != null)
                        connection.send(new Message(MessageType.USER_ADDED, NAME_IN_MAP));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {

            while (true) {
                Message message = connection.receive();
                if (message != null && message.getType() == MessageType.TEXT) {
                    Message newMessage = new Message(MessageType.TEXT, userName + ": " + message.getData());
                    sendBroadcastMessage(newMessage);
                } else {
                    ConsoleHelper.writeMessage("Неверный тип сообщения от " + userName);
                }
            }
        }

        public void run() {
            ConsoleHelper.writeMessage("Established new connection with remote address " + socket.getRemoteSocketAddress());
            String REMOTE_ADDRESS = socket.getRemoteSocketAddress().toString(); // getting remote address from client
            String userName = null;


            try (Connection connection = new Connection(socket)){

                 // creating a new connection
                ConsoleHelper.writeMessage("Connection with port " + connection.getRemoteSocketAddress());
                userName = serverHandshake(connection); // getting a user name

                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName)); // sending a message to other users

                sendListOfUsers(connection, userName); // sending a user list to new user

                serverMainLoop(connection, userName); // getting messages from user

            } catch (IOException e1) {
                ConsoleHelper.writeMessage("Exception with address" + REMOTE_ADDRESS + e1.getLocalizedMessage());


            } catch (ClassNotFoundException e2) {
                ConsoleHelper.writeMessage("Exception with address" + REMOTE_ADDRESS + e2.getLocalizedMessage());

            } finally {
                if (userName != null && !userName.isEmpty() && connectionMap.containsKey(userName)) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));

                }
                ConsoleHelper.writeMessage("Connection was closed");
            }
        }
    }
}


