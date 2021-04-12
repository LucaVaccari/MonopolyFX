package it.castelli.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Receive connections on the specific port
 */
public class ConnectionReceiver implements Runnable
{
	/**
	 * Indicate if connection receiver is working
	 */
	private boolean isRunning = true;

	/**
	 * Indicate the time between two server's checks to verify a connection activity (in seconds)
	 */
	private static final int CHECK_TIME = 10;
	private Thread keepAliveReceiver;
	private Thread keepAliveSender;

	@Override
	public void run()
	{
		System.out.println("ConnectionReceiver is working");

		keepAliveReceiver = new Thread(new KeepAliveReceiver(CHECK_TIME));
		keepAliveSender = new Thread(new KeepAliveSender(CHECK_TIME / 2));
		keepAliveReceiver.start();
		keepAliveSender.start();

		try
		{
			ServerSocket welcomeSocket = new ServerSocket(ConnectionManager.SERVER_PORT);
			while (isRunning)
			{
				Socket connectionSocket = welcomeSocket.accept();
				System.out.println(
						"New connection established with " + connectionSocket.getInetAddress().getHostAddress());
				//generate new connection
				Connection newConnection = new Connection(connectionSocket);
				ConnectionManager.getInstance().addToWaitingRoom(newConnection);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void interrupt()
	{
		keepAliveSender.interrupt();
		keepAliveReceiver.interrupt();
		isRunning = false;
	}
}
