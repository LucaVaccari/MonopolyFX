package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;

/**
 * Message from the client that ends the current round and triggers the next one (receive only)
 */
public class EndRoundServerMessage implements Message
{
	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for EndRoundServerMessage (do not use)
	 *
	 * @param gameCode The game code
	 */
	public EndRoundServerMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		GameManager gameManager = gameConnectionManager.getGameManager();
		gameManager.nextRound(0);

		System.out.println("EndRoundServerMessageCalled");
		gameConnectionManager.updatePlayers();
	}
}
