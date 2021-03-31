package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

public class AuctionOfferServerMessage implements Message
{
	private int offer;
	private int gameCode;

	public AuctionOfferServerMessage(int offer, int gameCode)
	{
		this.offer = offer;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		ConnectionManager.getInstance().getGames().get(gameCode).offer(player, offer);
	}
}
