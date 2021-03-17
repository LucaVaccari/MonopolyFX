package it.castelli;

import it.castelli.connection.Connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientMain
{
	public static void main(String[] args)
	{
		Socket clientSocket = null;
		try
		{
			clientSocket = new Socket(InetAddress.getLocalHost(), 1111);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Connection connection = new Connection(clientSocket);
		//launch(args);

	}


}
