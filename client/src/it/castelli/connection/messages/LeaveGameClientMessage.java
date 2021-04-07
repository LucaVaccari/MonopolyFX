package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class LeaveGameClientMessage implements Message
{
	private final int code;

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
