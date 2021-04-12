package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.controllers.AuctionController;
import it.castelli.gui.controllers.BoardController;
import it.castelli.gui.controllers.ExchangeController;
import it.castelli.gui.controllers.LobbyController;
import javafx.application.Platform;

/**
 * Message containing a simple text message (send and receive)
 */
public class ChatClientMessage implements Message
{
	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * The text message body
	 */
	private final String message;

	/**
	 * Constructor for ChatClientMessage
	 *
	 * @param gameCode     The game code
	 * @param senderPlayer The player that sends the message
	 * @param messageBody  The text message body
	 */
	public ChatClientMessage(int gameCode, Player senderPlayer, String messageBody)
	{
		this.gameCode = gameCode;
		message = senderPlayer.getName() + ": " + messageBody;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Platform.runLater(() -> {
			LobbyController.getInstance().getChat().addMessage(message);
			BoardController.getInstance().getChat().addMessage(message);
			AuctionController.getInstance().getChat().addMessage(message);
			ExchangeController.getInstance().getChat().addMessage(message);
		});
	}
}
