package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message containing the game Code (send only)
 */
public class GameCodeServerMessage implements Message
{
	/**
	 * The game Code
	 */
	private final int gameCode;

	/**
	 * Constructor for GameCodeServerMessage
	 *
	 * @param gameCode The game Code
	 */
	public GameCodeServerMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
