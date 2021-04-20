package it.castelli;

import it.castelli.connection.ConnectionManager;

/**
 * Start the server
 */
public class ServerMain
{
	public static void main(String[] args)
	{
		ConnectionManager.getInstance().start();
	}
}
