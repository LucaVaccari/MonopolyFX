package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

/**
 * Message containing a simple text message (send and receive)
 */
public class ChatServerMessage implements Message
{
	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * The text message body
	 */
	private String message;

	/**
	 * Constructor for ChatServerMessage
	 *
	 * @param gameCode     The game code
	 * @param senderPlayer The player that sends the message
	 * @param messageBody  The text message body
	 */
	public ChatServerMessage(int gameCode, Player senderPlayer, String messageBody)
	{
		this.gameCode = gameCode;
		message = messageBody;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//send the message to all the users
		Message messageObj = new ChatServerMessage(gameCode, player, message);
		ConnectionManager.getInstance().getGames().get(gameCode)
				.sendAll(ServerMessages.CHAT_MESSAGE_NAME, Serializer.toJson(messageObj));
	}
}
