package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

/**
 *
 */
public class LeaveGameServerMessage implements Message
{
	/**
	 * The game code
	 */
	private int gameCode;

	/**
	 * Constructor for LeaveGameServerMessage
	 *
	 * @param gameCode The game code
	 */
	public LeaveGameServerMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		ConnectionManager.getInstance().leaveGame(gameCode, connection);
	}
}
