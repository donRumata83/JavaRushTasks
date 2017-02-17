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
        while (true)
        {
            Socket socket = serverSocket.accept();
            Handler handler = new Handler(socket);
        }
    }

    private static class Handler extends Thread
    {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }
    }
}
