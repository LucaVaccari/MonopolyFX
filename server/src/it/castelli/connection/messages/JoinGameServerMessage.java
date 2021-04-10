package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

/**
 * Message from the client that joins a game with the given game code (receive only)
 */
public class JoinGameServerMessage implements Message
{
	/**
	 * The game code of the game to join
	 */
	private final int gameCode;

	/**
	 * The player that wants to join
	 */
	private final Player player;

	/**
	 * Constructor for JoinGameServerMessage (do not use)
	 *
	 * @param gameCode The game code of the game to join
	 * @param player The player that joins
	 */
	public JoinGameServerMessage(int gameCode, Player player)
	{
		this.gameCode = gameCode;
		this.player = player;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		ConnectionManager.getInstance().joinGame(gameCode, connection, this.player);
	}
}
