package it.castelli.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionReceiver implements Runnable
{
	private boolean isRunning = true;
	private int sendTime = 10;
	private Thread keepAliveReceiver;
	private Thread keepAliveSender;

	@Override
	public void run()
	{
		keepAliveReceiver = new Thread(new KeepAliveReceiver(sendTime * 2));
		keepAliveSender = new Thread(new KeepAliveSender(sendTime));
		keepAliveReceiver.start();
		keepAliveSender.start();

		while (isRunning)
		{
			try
			{
				ServerSocket welcomeSocket = new ServerSocket(ConnectionManager.SERVER_PORT);
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
