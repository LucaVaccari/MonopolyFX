package it.castelli.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Sender
{
	private InputStream inputStream;
	private OutputStream outputStream;

	public Sender(Socket connectionSocket)
	{
		try
		{
			inputStream = connectionSocket.getInputStream();
			outputStream = connectionSocket.getOutputStream();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void send(byte[] message)
	{
		try
		{
			outputStream.write(message);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
