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
        ConsoleHelper.writeMessage("Input server port to start");
        int serverPort = ConsoleHelper.readInt();

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            ConsoleHelper.writeMessage("Server starts");
            while (true) {new Handler(serverSocket.accept()).start();}

        } catch (Exception e) {
            ConsoleHelper.writeMessage(e.getLocalizedMessage());
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
                if (reply.getType().equals(MessageType.USER_NAME)) {
                    name = reply.getData();
                    if (!name.isEmpty() && !connectionMap.containsKey(name)) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return name;
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {

                for (String NAME_IN_MAP : connectionMap.keySet()) {
                    if (!userName.equals(NAME_IN_MAP))
                        connection.send(new Message(MessageType.USER_ADDED, NAME_IN_MAP));
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
            String remoteAddress = socket.getRemoteSocketAddress().toString(); // getting remote address from client
            String userName = null;


            try (Connection connection = new Connection(socket)) {

                // creating a new connection
                ConsoleHelper.writeMessage("Connection with port " + connection.getRemoteSocketAddress());
                userName = serverHandshake(connection); // getting a user name

                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName)); // sending a message to other users

                sendListOfUsers(connection, userName); // sending a user list to new user

                serverMainLoop(connection, userName); // getting messages from user

            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Exception with address" + remoteAddress + e.getLocalizedMessage());
            } finally {
                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));

                }
                ConsoleHelper.writeMessage("Connection was closed");
            }
        }
    }
}


