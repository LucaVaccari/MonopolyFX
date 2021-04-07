package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.AlertUtil;

public class ErrorClientMessage implements Message
{
	String errorMessage;

	public ErrorClientMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		AlertUtil.showInformationAlert("Error", "Error from the server", errorMessage);
	}
}
