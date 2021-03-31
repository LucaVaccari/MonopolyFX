package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Error info message (receive only)
 */
public class ErrorClientMessage implements Message
{
	String errorMessage;

	/**
	 * Constructor for ErrorClientMessage
	 * @param errorMessage error message text
	 */
	public ErrorClientMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//TODO: Pop-up error message
	}
}
