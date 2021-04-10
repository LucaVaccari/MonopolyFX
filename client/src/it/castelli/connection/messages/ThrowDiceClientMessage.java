package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to throw the dice (send only)
 */
public class ThrowDiceClientMessage implements Message
{
	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for ThrowDiceClientMessage
	 *
	 * @param gameCode The game code
	 */
	public ThrowDiceClientMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
