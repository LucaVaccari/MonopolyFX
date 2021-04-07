package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Leave game request message (send only)
 */
public class LeaveGameClientMessage implements Message
{
	private final int code;

	/**
	 * Constructor for LeaveGameClientMessage
	 * @param code game code
	 */
	public LeaveGameClientMessage(int code)
	{
		this.code = code;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
