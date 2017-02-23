package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
                handler.start();}

        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }
    }
}
