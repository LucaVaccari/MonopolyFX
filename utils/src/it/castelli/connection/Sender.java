package it.castelli.connection;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Sender
{
	private BufferedWriter out;

	public Sender(Socket connectionSocket)
	{
		try
		{
			out = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void send(String message)
	{
		try
		{
			out.write(message + '\n');
			out.flush();
		}
		catch (IOException e)
		{
			System.out.println("Connection interrupted");
		}
	}
}
