package it.castelli.connection;

import java.net.Socket;

public class Connection
{
	private Socket connectionSocket;
	private boolean isConnected;
	private boolean keepAliveFlag = true;
	private Sender sender;

	public Connection(Socket connectionSocket)
	{
		this.connectionSocket = connectionSocket;
		sender = new Sender(connectionSocket);
	}

	private boolean isConnected()
	{
		return isConnected;
	}

	public void interrupt()
	{

	}

	public boolean getKeepAliveFlag()
	{
		return keepAliveFlag;
	}

	public void setKeepAliveFlag(boolean flag)
	{
		keepAliveFlag = flag;
	}

	public void send(byte[] message)
	{
		//TODO: sender.send(byte[] message);
	}

}
