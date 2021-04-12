package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.ConnectionReceiver;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;

/**
 * Request to move the player into the prison (receive only)
 */
public class GoToJailServerMessage implements Message
{
	/**
	 * The game code
	 */
	private int gameCode;

	/**
	 * The player to move into the prison
	 */
	private Player player;

	/**
	 * Constructor for GoToJailServerMessage (do not use)
	 *
	 * @param gameCode The game code
	 * @param player The player to move into the prison
	 */
	public GoToJailServerMessage(int gameCode, Player player)
	{
		this.gameCode = gameCode;
		this.player = player;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		GameManager gameManager = gameConnectionManager.getGameManager();

		Player prisoner = gameManager.getSamePlayer(this.player);
		prisoner.setInPrison(true);
		prisoner.setPosition(10, false);
		gameConnectionManager.updatePlayers();
	}
}
