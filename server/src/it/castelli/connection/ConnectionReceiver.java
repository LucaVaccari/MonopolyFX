package it.castelli.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionReceiver implements Runnable
{
    private boolean isRunning = true;

    @Override
    public void run()
    {
        while(isRunning)
        {
            try
            {
                ServerSocket welcomeSocket = new ServerSocket(ConnectionManager.SERVER_PORT);
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println(
                        "New connection established with " + connectionSocket.getInetAddress().getHostAddress());
                //generate thread for the receiver connection
                Connection newConnection = new Connection(connectionSocket);
                // TODO: add connection to the waitingRoom

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void interrupt()
    {
        isRunning = false;
    }
}
