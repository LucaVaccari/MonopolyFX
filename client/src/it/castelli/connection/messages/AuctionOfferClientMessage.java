package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to offer money in the auction (send only)
 */
public class AuctionOfferClientMessage implements Message
{
	/**
	 * The money amount to offer
	 */
	private final int offer;

	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for AuctionOfferClientMessage
	 *
	 * @param offer The money amount to offer
	 * @param gameCode The game code
	 */
	public AuctionOfferClientMessage(int offer, int gameCode)
	{
		this.offer = offer;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
