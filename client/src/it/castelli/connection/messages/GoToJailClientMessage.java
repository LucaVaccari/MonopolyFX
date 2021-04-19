package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to move the player into the prison (send only)
 */
public class GoToJailClientMessage implements Message
{
	/**
	 * The game code
	 */
	private int gameCode;

	/**
	 * The player to move into the prison
	 */
	private Player player;

	/**
	 * Constructor for GoToJailClientMessage
	 *
	 * @param gameCode The game code
	 * @param player   The player to move into the prison
	 */
	public GoToJailClientMessage(int gameCode, Player player)
	{
		this.gameCode = gameCode;
		this.player = player;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
