package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to leave the game with the given game code (send only)
 */
public class LeaveGameClientMessage implements Message
{
	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for LeaveGameClientMessage
	 *
	 * @param gameCode The game code
	 */
	public LeaveGameClientMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
