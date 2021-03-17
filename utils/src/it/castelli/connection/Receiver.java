package it.castelli.connection;

import it.castelli.connection.messages.Message;
import it.castelli.serialization.Serializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		try
		{
			BufferedReader in  = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			while (isRunning)
			{
				String classType = in.readLine().trim();
				String jsonObject = in.readLine().trim();

				Message message = (Message) Serializer.fromJson(jsonObject, classType);
				message.onReceive(this.connection);
			}
		}
		catch (IOException e)
		{
			System.out.println("The connection has been interrupted");
		}

	}
}
