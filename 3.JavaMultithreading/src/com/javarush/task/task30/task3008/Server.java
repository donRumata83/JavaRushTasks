package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message reply = connection.receive();
                if (reply != null && reply.getType() == MessageType.USER_NAME) {
                    String name = reply.getData();
                    if (!name.isEmpty() && !connectionMap.containsKey(name)) {
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
    }
}


