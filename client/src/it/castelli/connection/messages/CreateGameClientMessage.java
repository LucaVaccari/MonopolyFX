package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * The create game request message (send only)
 */
public class CreateGameClientMessage implements Message
{

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
