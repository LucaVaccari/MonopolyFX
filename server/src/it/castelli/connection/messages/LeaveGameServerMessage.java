package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

/**
 * Message from the client that leaves the game with the given game code (receive only)
 */
public class LeaveGameServerMessage implements Message
{
	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for LeaveGameServerMessage (do not use)
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
		ConnectionManager.getInstance().getGames().get(gameCode).resetPlayerProperties(connection);
		ConnectionManager.getInstance().leaveGame(gameCode, connection);
	}
}
