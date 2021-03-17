package it.castelli.connection;

import java.net.Socket;

public class Connection
{
	private Socket connectionSocket;
	private boolean isConnected;
	private boolean keepAliveFlag = true;
	private Sender sender;
	private Thread receiverThread;

	public Connection(Socket connectionSocket)
	{
		this.connectionSocket = connectionSocket;
		sender = new Sender(connectionSocket);

		receiverThread = new Thread(new Receiver(this));
		receiverThread.start();
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

	public void send(String message)
	{
		sender.send(message);
	}

	public Socket getSocket()
	{
		return connectionSocket;
	}

	@Override
	public int hashCode()
	{
		return connectionSocket.getInetAddress().getHostAddress().length();
	}

	@Override
	public boolean equals(Object obj)
	{
		//compares connections' remote IP addresses
		if (obj instanceof Connection)
			return connectionSocket.getInetAddress().getHostAddress().equals(((Connection) obj).connectionSocket.getInetAddress().getHostAddress());
		return false;
	}
}
