package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.AlertUtil;
import javafx.application.Platform;

/**
 * Message containing an error from the server (receive only)
 */
public class ErrorClientMessage implements Message
{
	/**
	 * The error message text body
	 */
	private final String errorMessage;

	/**
	 * Constructor for ErrorClientMessage
	 *
	 * @param errorMessage The error message text body
	 */
	public ErrorClientMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Platform.runLater(
				() -> AlertUtil.showInformationAlert("Error", "Error from the server", errorMessage));
	}
}
