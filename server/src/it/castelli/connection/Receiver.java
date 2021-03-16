package it.castelli.connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Receiver implements Runnable
{
	private Socket connectionSocket;
	private byte[] receivedMessage = new byte[2048];
	private boolean isRunning = true;

	public Receiver(Socket connectionSocket)
	{
		this.connectionSocket = connectionSocket;
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

			}
		}
		catch (IOException e)
		{
			System.out.println("The connection has been interrupted");
		}

	}
}
