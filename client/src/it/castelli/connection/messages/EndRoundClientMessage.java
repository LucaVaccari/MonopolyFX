package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to end the current round and trigger the next one (send only)
 */
public class EndRoundClientMessage implements Message
{
	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for EndRoundClientMessage
	 *
	 * @param gameCode The game code
	 */
	public EndRoundClientMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
