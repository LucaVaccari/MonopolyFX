package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message to set the client as the game host (send only)
 */
public class SetHostServerMessage implements Message
{
	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
