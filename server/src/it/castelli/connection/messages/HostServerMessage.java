package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class HostServerMessage implements Message
{
	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
