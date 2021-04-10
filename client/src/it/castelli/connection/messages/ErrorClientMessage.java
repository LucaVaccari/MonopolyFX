package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.AlertUtil;
import javafx.application.Platform;

/**
 * Error info message (receive only)
 */
public class ErrorClientMessage implements Message
{
	String errorMessage;

	/**
	 * Constructor for ErrorClientMessage
	 *
	 * @param errorMessage error message text
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
