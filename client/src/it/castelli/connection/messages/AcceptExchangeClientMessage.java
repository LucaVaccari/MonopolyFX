package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to accept the current exchange (send only)
 */
public class AcceptExchangeClientMessage implements Message
{
	/**
	 * Does the player accept the offer?
	 */
	private final boolean accept;

	/**
	 * The player
	 */
	private final Player player;

	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for AcceptExchangeClientMessage
	 *
	 * @param accept
	 * @param player   The player accepting the exchange
	 * @param gameCode The game code
	 */
	public AcceptExchangeClientMessage(boolean accept, Player player, int gameCode)
	{
		this.accept = accept;
		this.player = player;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
