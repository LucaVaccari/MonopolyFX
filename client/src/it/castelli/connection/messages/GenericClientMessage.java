package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.AlertUtil;
import javafx.application.Platform;

/**
 * Message from the server containing an error (receive only)
 */
public class GenericClientMessage implements Message
{
	/**
	 * The title of the message
	 */
	private final String messageTitle;

	/**
	 * The body of the message text
	 */
	private final String messageBody;

	/**
	 * Constructor for GenericClientMessage
	 *
	 * @param messageTitle The title of the message
	 * @param messageBody The body of the message text
	 */
	public GenericClientMessage(String messageTitle, String messageBody)
	{
		this.messageTitle = messageTitle;
		this.messageBody = messageBody;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Platform.runLater(
				() -> AlertUtil.showInformationAlert(messageTitle, "Informazione", messageBody));
	}
}
