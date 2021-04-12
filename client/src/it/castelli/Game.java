package it.castelli;

import it.castelli.connection.messages.*;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.dice.DiceResult;
import it.castelli.serialization.Serializer;

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
	 * The result from the last dice throw
	 */
	private static DiceResult lastDiceResult;

	/**
	 * is the player the host?
	 */
	private static boolean host;

	/**
	 * Private constructor, to prevent instantiating this class
	 */
	private Game()
	{
	}

	/**
	 * Getter for the gameManager
	 *
	 * @return The GameManager, the main class handling every aspect of the game logic
	 */
	public static GameManager getGameManager()
	{
		return gameManager;
	}

	/**
	 * Getter for the player
	 *
	 * @return The Player instance of this client
	 */
	public static Player getPlayer()
	{
		return player;
	}

	/**
	 * Setter for the player
	 *
	 * @param player The new Player instance of this client
	 */
	public static void setPlayer(Player player)
	{
		Game.player = player;
	}

	/**
	 * Getter for the game code
	 *
	 * @return The code of the game
	 */
	public static int getGameCode()
	{
		return gameCode;
	}

	/**
	 * Setter for the game code
	 *
	 * @param gameCode The new code of the game
	 */
	public static void setGameCode(int gameCode)
	{
		Game.gameCode = gameCode;
	}

	/**
	 * Getter for lastDiceResult
	 *
	 * @return The result from the last dice throw
	 */
	public static DiceResult getLastDiceResult()
	{
		return lastDiceResult;
	}

	/**
	 * Setter for lastDiceResult
	 *
	 * @param lastDiceResult the new dice result
	 */
	public static void setLastDiceResult(DiceResult lastDiceResult)
	{
		Game.lastDiceResult = lastDiceResult;
	}

	/**
	 * Use the results of a dice throw, checking whether the player has to move or not
	 */
	public static void useDiceResult()
	{
		if (player.isInPrison())
		{
			player.setThrowDiceInPrison(player.getThrowDiceInPrison() + 1);
			if (!lastDiceResult.areResultsEquals() && player.getThrowDiceInPrison() == 3)
			{
				ClientMain.getConnection().send(ClientMessages.EXIT_FROM_JAIL_MESSAGE_NAME, Serializer.toJson(new ExitFromJailClientMessage(player, Game.getGameCode(), true)));
				player.setInPrison(false);
			} else if (lastDiceResult.areResultsEquals())
			{
				ClientMain.getConnection().send(ClientMessages.EXIT_FROM_JAIL_MESSAGE_NAME, Serializer.toJson(new ExitFromJailClientMessage(player, Game.getGameCode(), false)));
			}
		}
		else
		{
			if (lastDiceResult.areResultsEquals())
				player.setDoubleDiceResult(player.getDoubleDiceResult() + 1);
			else
				player.setDoubleDiceResult(0);
			if (player.getDoubleDiceResult() == 3)
			{
				ClientMain.getConnection().send(ClientMessages.GO_TO_JAIL_MESSAGE_NAME, Serializer.toJson(new GoToJailClientMessage(Game.getGameCode(), player)));
			}
			else
			{
				Message moveMessage = new MovePlayerClientMessage(Game.getPlayer(), lastDiceResult.resultsSum(), Game.getGameCode());
				ClientMain.getConnection().send(ClientMessages.MOVE_PLAYER_MESSAGE_NAME, Serializer.toJson(moveMessage));
//
//			}
			}
		}
	}

	public static boolean isHost()
	{
		return host;
	}

	public static void setHost(boolean isHost)
	{
		Game.host = isHost;
	}
}
