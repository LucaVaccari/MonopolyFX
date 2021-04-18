package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to exit prison (send only)
 */
public class ExitFromJailClientMessage implements Message
{
	/**
	 * The player that wants to exit from jail
	 */
	private final Player player;

	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Does the player pay to exit?
	 */
	private final boolean pay;

	/**
	 * Constructor for ExitFromJailClientMessage
	 *
	 * @param player   The player that wants to exit from jail
	 * @param gameCode The game code
	 * @param pay      Does the player pay to exit?
	 */
	public ExitFromJailClientMessage(Player player, int gameCode, boolean pay)
	{
		this.player = player;
		this.gameCode = gameCode;
		this.pay = pay;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
