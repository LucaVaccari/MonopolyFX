package it.castelli.connection;

import it.castelli.gameLogic.Player;

import java.net.Socket;

public class Connection
{
	private final Socket connectionSocket;
	private final Sender sender;
	private final Receiver receiver;
	private boolean isConnected;
	private boolean keepAliveFlag = true;

	public Connection(Socket connectionSocket)
	{
		this.connectionSocket = connectionSocket;
		sender = new Sender(connectionSocket);
		receiver = new Receiver(this);
		new Thread(receiver).start();
	}

	private boolean isConnected()
	{
		return isConnected;
	}

	public void interrupt()
	{

	}

	public void addPlayer(Player player)
	{
		this.receiver.setPlayer(player);
	}

	public boolean getKeepAliveFlag()
	{
		return keepAliveFlag;
	}

	public void setKeepAliveFlag(boolean flag)
	{
		keepAliveFlag = flag;
	}

	public void send(String messageName, String message)
	{
		sender.send(messageName);
		sender.send(message);
	}

	public Socket getSocket()
	{
		return connectionSocket;
	}

	public Receiver getReceiver()
	{
		return receiver;
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
			return connectionSocket.getInetAddress().getHostAddress()
					.equals(((Connection) obj).connectionSocket.getInetAddress()
							        .getHostAddress());
		return false;
	}
}
