package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

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
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		gameConnectionManager.getGameManager().auctionOffer(player, offer);
		gameConnectionManager.sendAll(ServerMessages.UPDATE_AUCTION_MESSAGE_NAME, Serializer.toJson(new UpdateAuctionServerMessage(gameConnectionManager.getGameManager().getAuction())));
	}
}
