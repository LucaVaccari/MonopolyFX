package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Player's offer info message (send only)
 */
public class AuctionOfferClientMessage implements Message
{
	private int offer;
	private int gameCode;

	/**
	 * Constructor for AuctionOfferClientMessage
	 * @param offer player's offer value
	 * @param gameCode game code
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
