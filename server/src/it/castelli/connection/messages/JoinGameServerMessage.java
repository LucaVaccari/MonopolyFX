package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

/**
 * Join game request message (receive only)
 */
public class JoinGameServerMessage implements Message
{
	private int code;
	private Player player;

	/**
	 * Add new player to the game
	 * @param connection connection of the player
	 * @param player player
	 */
	@Override
	public void onReceive(Connection connection, Player player)
	{
		ConnectionManager.getInstance().joinGame(code, connection, player);
	}
}
