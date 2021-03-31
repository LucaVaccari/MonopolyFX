package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class AuctionOfferClientMessage implements Message
{
	private int offer;
	private int gameCode;

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
