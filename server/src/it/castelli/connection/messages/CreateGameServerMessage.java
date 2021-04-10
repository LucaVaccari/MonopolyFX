package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

/**
 * Message from the client that creates a new game (receive only)
 */
public class CreateGameServerMessage implements Message
{
	/**
	 * The new player created
	 */
	private final Player player;

	/**
	 * Constructor for CreateGameServerMessage (do not use)
	 *
	 * @param playerName the new player name
	 */
	public CreateGameServerMessage(String playerName)
	{
		this.player = new Player(1500, playerName);
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		int gameCode = ConnectionManager.getInstance().createGame();
		ConnectionManager.getInstance().joinGame(gameCode, connection, this.player);
	}
}
