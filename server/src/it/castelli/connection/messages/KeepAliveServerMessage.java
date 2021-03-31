package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Keep Alive message
 */
public class KeepAliveServerMessage implements Message
{

	/**
	 * Confirm that the connection is still active
	 * @param connection connection of the player
	 * @param player playe
	 */
	@Override
	public void onReceive(Connection connection, Player player)
	{
		connection.setKeepAliveFlag(true);
	}
}
