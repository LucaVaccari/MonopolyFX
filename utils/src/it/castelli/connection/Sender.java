package it.castelli.connection;

import java.io.*;
import java.net.Socket;

public class Sender
{
	BufferedWriter out;

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
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
