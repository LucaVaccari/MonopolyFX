package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class CreateGameClientMessage implements Message
{
	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
