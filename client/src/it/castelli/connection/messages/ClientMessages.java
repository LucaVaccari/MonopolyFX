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
	public static final String START_GAME_MESSAGE_NAME =
			"it.castelli.connection.messages.StartGameServerMessage";
	public static final String END_ROUND_MESSAGE_NAME =
			"it.castelli.connection.messages.EndRoundServerMessage";
	public static final String CHAT_MESSAGE_NAME =
			"it.castelli.connection.messages.ChatServerMessage";
	public static final String PAWN_MESSAGE_NAME =
			"it.castelli.connection.messages.PawnServerMessage";
	public static final String CREATE_EXCHANGE_MESSAGE_NAME =
			"it.castelli.connection.messages.CreateExchangeServerMessage";
	public static final String ACCEPT_EXCHANGE_MESSAGE_NAME =
			"it.castelli.connection.messages.AcceptExchangeServerMessage";
	public static final String CHANGE_EXCHANGE_ASSET_MESSAGE_NAME =
			"it.castelli.connection.messages.ChangeExchangeAssetServerMessage";
	public static final String REFUSE_EXCHANGE_MESSAGE_NAME =
			"it.castelli.connection.messages.RefuseExchangeServerMessage";
}
