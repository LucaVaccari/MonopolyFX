package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Error info message (send only)
 */
public class ErrorServerMessage implements Message
{
	String errorMessage;

	/**
	 * Constructor for ErrorServerMessage
	 *
	 * @param errorMessage error message text
	 */
	public ErrorServerMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
