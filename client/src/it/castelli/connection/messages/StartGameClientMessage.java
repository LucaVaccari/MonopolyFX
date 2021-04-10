package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to start the game (made by the host) (send only)
 */
public class StartGameClientMessage implements Message
{
	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for StartGameClientMessage
	 *
	 * @param gameCode The game code
	 */
	public StartGameClientMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
