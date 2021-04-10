package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Auction;

public class UpdateAuctionServerMessage implements Message
{
	private final Auction auction;

	public UpdateAuctionServerMessage(Auction auction)
	{
		this.auction = auction;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
