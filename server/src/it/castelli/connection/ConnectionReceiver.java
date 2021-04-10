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
	private int checkTime = 10;
	private Thread keepAliveReceiver;
	private Thread keepAliveSender;

	@Override
	public void run()
	{
		System.out.println("ConnectionReceiver is working");


		keepAliveReceiver = new Thread(new KeepAliveReceiver(checkTime));
		keepAliveSender = new Thread(new KeepAliveSender(checkTime / 2));
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
