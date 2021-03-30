package it.castelli.connection;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Sender
{
	BufferedWriter out;
	String address;

	public Sender(Socket connectionSocket)
	{
		try
		{
			out = new BufferedWriter(
					new OutputStreamWriter(connectionSocket.getOutputStream()));
			address = connectionSocket.getInetAddress().getHostAddress();
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
