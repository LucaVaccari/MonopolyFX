package it.castelli.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionReceiver implements Runnable
{
    private boolean isRunning = true;
    private int sendTime = 5;
    private Thread keepAliveReceiver;
    private Thread keepAliveSender;

    @Override
    public void run()
    {
        System.out.println("ConnectionReceiver is working");
        keepAliveReceiver = new Thread(new KeepAliveReceiver(sendTime * 2));
        keepAliveSender = new Thread(new KeepAliveSender(sendTime));
        keepAliveReceiver.start();
        keepAliveSender.start();

        ServerSocket welcomeSocket = null;
        try
        {
            welcomeSocket = new ServerSocket(ConnectionManager.SERVER_PORT);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        while (isRunning)
        {
            try
            {
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println(
                        "New connection established with " +
                                connectionSocket.getInetAddress().getHostAddress());
                //generate new connection
                Connection newConnection = new Connection(connectionSocket);
                ConnectionManager.getInstance().addToWaitingRoom(newConnection);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void interrupt()
    {
        keepAliveSender.interrupt();
        keepAliveReceiver.interrupt();
        isRunning = false;
    }
}
