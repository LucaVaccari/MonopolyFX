package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

/**
 * Leave game request message (receive only)
 */
public class LeaveGameServerMessage implements Message
{
	private int code;

	/**
	 * Constructor of LeaveGameServerMessage
	 *
	 * @param code game code
	 */
	public LeaveGameServerMessage(int code)
	{
		this.code = code;
	}

	/**
	 * Remove the player from the game
	 *
	 * @param connection connection of the player
	 * @param player     player
	 */
	@Override
	public void onReceive(Connection connection, Player player)
	{
		ConnectionManager.getInstance().leaveGame(code, connection);
	}
}
