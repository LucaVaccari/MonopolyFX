package it.castelli;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;

/**
 * Static class for storing information about the game
 */
public class Game
{
	/**
	 * The GameManager, the main class handling every aspect of the game logic
	 */
	private static final GameManager gameManager = new GameManager();
	/**
	 * The Player instance of this client
	 */
	private static Player player;

	/**
	 * The code of the game
	 */
	private static int gameCode;

	/**
	 * Private constructor, to prevent instantiating this class
	 */
	private Game() {}

	/**
	 * Getter for the gameManager
	 * @return The GameManager, the main class handling every aspect of the game logic
	 */
	public static GameManager getGameManager()
	{
		return gameManager;
	}

	/**
	 * Getter for the player
	 * @return The Player instance of this client
	 */
	public static Player getPlayer()
	{
		return player;
	}

	/**
	 * Setter for the player
	 * @param player The new Player instance of this client
	 */
	public static void setPlayer(Player player)
	{
		Game.player = player;
	}

	/**
	 * Getter for the game code
	 * @return The code of the game
	 */
	public static int getGameCode()
	{
		return gameCode;
	}

	/**
	 * Setter for the game code
	 * @param gameCode The new code of the game
	 */
	public static void setGameCode(int gameCode)
	{
		Game.gameCode = gameCode;
	}
}
