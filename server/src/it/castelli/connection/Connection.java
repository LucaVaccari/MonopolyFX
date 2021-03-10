package it.castelli.connection;

import java.net.Socket;

public class Connection
{
	private User user;
	private Socket connectionSocket;
	private boolean isConnected;
	private final int keepAliveValue = 20;
	private Sender sender;

	public Connection(User user, Socket connectionSocket)
	{
		this.user = user;
		this.connectionSocket = connectionSocket;
		sender = new Sender(connectionSocket);
	}

	private boolean isConnected()
	{
		return isConnected;
	}

	private void sendKeepAlive()
	{

	}

	public void interrupt()
	{

	}

	public void send(byte[] message)
	{
		//TODO: sender.send(byte[] message);
	}

}
