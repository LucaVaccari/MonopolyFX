package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

/**
 * Message from the client that offers money in the auction (receive only)
 */
public class AuctionOfferServerMessage implements Message
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
	 * Constructor for AuctionOfferServerMessage (do not use)
	 *
	 * @param offer    The money amount to offer
	 * @param gameCode The game code
	 */
	public AuctionOfferServerMessage(int offer, int gameCode)
	{
		this.offer = offer;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		Player offeringPlayer = gameConnectionManager.getGameManager().getSamePlayer(player);
		gameConnectionManager.auctionOffer(offeringPlayer, offer);
		gameConnectionManager.sendAll(ServerMessages.UPDATE_AUCTION_MESSAGE_NAME, Serializer
				.toJson(new UpdateAuctionServerMessage(gameConnectionManager.getGameManager().getAuction())));
	}
}
