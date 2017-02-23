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
        ServerSocket serverSocket = new ServerSocket(serverPort);
        ConsoleHelper.writeMessage("Server starts");
        Socket socket;
        Handler handler;
        while (true) {
            try {
                socket = serverSocket.accept();
                handler = new Handler(socket);
                handler.run();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                serverSocket.close();
            }

        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }
    }
}
