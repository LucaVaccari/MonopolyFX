package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class KeepAliveServerMessage implements Message
{
	@Override
	public void onReceive(Connection connection, Player player)
	{
		connection.setKeepAliveFlag(true);
	}
}
