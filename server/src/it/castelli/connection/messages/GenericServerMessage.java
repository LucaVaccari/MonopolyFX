package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message used to send an error to the client (send only)
 */
public class GenericServerMessage implements Message
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
	 * Constructor for GenericServerMessage
	 *
	 * @param messageTitle The title of the message
	 * @param messageBody The body of the message text
	 */
	public GenericServerMessage(String messageTitle, String messageBody)
	{
		this.messageTitle = messageTitle;
		this.messageBody = messageBody;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
