package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

public class LeaveGameServerMessage implements Message
{
	private int code;

	public LeaveGameServerMessage(int code)
	{
		this.code = code;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		ConnectionManager.getInstance().leaveGame(code, connection);
	}
}
