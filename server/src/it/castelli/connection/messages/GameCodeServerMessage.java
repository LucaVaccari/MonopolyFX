package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Game code info message (send only)
 */
public class GameCodeServerMessage implements Message
{
	private int gameCode;

	/**
	 * Constructor for GameCodeServerMessage
	 *
	 * @param gameCode game code
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
