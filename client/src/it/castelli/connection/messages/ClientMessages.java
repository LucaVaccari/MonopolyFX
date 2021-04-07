package it.castelli.connection.messages;

/**
 * List of Client message names
 */
public class ClientMessages
{
	public static final String KEEP_ALIVE_MESSAGE_NAME =
			"it.castelli.connection.messages.KeepAliveServerMessage";
	public static final String JOIN_GAME_MESSAGE_NAME =
			"it.castelli.connection.messages.JoinGameServerMessage";
	public static final String CREATE_GAME_MESSAGE_NAME =
			"it.castelli.connection.messages.CreateGameServerMessage";
	public static final String LEAVE_GAME_MESSAGE_NAME =
			"it.castelli.connection.messages.LeaveGameServerMessage";
	public static final String AUCTION_OFFER_MESSAGE_NAME =
			"it.castelli.connection.messages.AuctionOfferServerMessage";
	public static final String THROW_DICE_MESSAGE_NAME =
			"it.castelli.connection.messages.ThrowDiceServerMessage";
}
