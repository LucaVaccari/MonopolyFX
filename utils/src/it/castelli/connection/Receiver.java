package it.castelli.connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Receiver implements Runnable
{
	private final Socket connectionSocket;
	private final Connection connection;
	private byte[] receivedMessage = new byte[2048];
	private boolean isRunning = true;

	public Receiver(Connection connection)
	{
		this.connection = connection;
		this.connectionSocket = connection.getSocket();
	}

	public void interrupt()
	{
		isRunning = false;
		try
		{
			connectionSocket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		try (InputStream inStream = connectionSocket.getInputStream())
		{
			while (isRunning)
			{
				// TODO: read messages
			}
		}
		catch (IOException e)
		{
			System.out.println("The connection has been interrupted");
		}

	}
}
