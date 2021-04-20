package it.castelli.connection;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Sender class
 */
public class Sender
{
	/**
	 * Write text to character output stream when used
	 */
	private BufferedWriter out;

	/**
	 * Constructor for the sender class
	 *
	 * @param connectionSocket a socket of a connection
	 */
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

	/**
	 * Send a message
	 *
	 * @param message Message to send
	 */
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
