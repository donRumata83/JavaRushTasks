package com.javarush.task.task30.task3008;

import java.io.*;
import java.net.*;


/**
 * Created by Rumata on 17.02.2017.
 */
public class Connection implements Closeable{
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public Connection(Socket socket) throws IOException{
        this.socket = socket;
        OutputStream sout = socket.getOutputStream();
        this.out = new ObjectOutputStream(sout);
        InputStream sin = socket.getInputStream();
        this.in = new ObjectInputStream(sin);
    }

    public void send(Message message) throws IOException
    {
        synchronized (out)
        {
            out.writeObject(message);
        }
    }

    public Message receive() throws IOException, ClassNotFoundException
    {
        Message message;
        synchronized (in)
        {
            message = (Message) in.readObject();
        }
        return message;
    }

    public SocketAddress getRemoteSocketAddress()
    {
        return socket.getRemoteSocketAddress();
    }

    public void close() throws IOException
    {
        in.close();
        out.close();
        socket.close();


    }

}
