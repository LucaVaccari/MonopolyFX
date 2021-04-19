package it.castelli;

import it.castelli.connection.messages.*;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.dice.DiceResult;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.controllers.BoardController;
import it.castelli.serialization.Serializer;
import javafx.application.Platform;

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
	 * counter of dice results equals
	 */
	private static int doubleDiceResult;

	/**
	 * number of dice thrown where you are in prison
	 */
	private static int throwDiceInPrison = 0;


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
			throwDiceInPrison += 1;
			if (!lastDiceResult.areResultsEquals() && throwDiceInPrison == 3)
			{
				throwDiceInPrison = 0;
				ClientMain.getConnection().send(ClientMessages.EXIT_FROM_JAIL_MESSAGE_NAME, Serializer
						.toJson(new ExitFromJailClientMessage(player, Game.getGameCode(), true)));
				Platform.runLater(() -> AlertUtil
						.showInformationAlert("Usciti di prigione", "Siete usciti di prigione", "Siete usciti di " +
								"prigione pagando 50 M"));
				Game.getGameManager().getCurrentRound().setDiceThrown(false);
				Platform.runLater(() -> BoardController.getInstance().update());
				player.setInPrison(false);
			}
			else if (lastDiceResult.areResultsEquals())
			{
				throwDiceInPrison = 0;
				ClientMain.getConnection().send(ClientMessages.EXIT_FROM_JAIL_MESSAGE_NAME, Serializer
						.toJson(new ExitFromJailClientMessage(player, Game.getGameCode(), false)));

				Platform.runLater(() -> AlertUtil
						.showInformationAlert("Usciti di prigione", "Siete usciti di prigione", "Siete usciti di " +
								"prigione tirando i dadi e facendo doppio. Complimenti!"));
			}
		}
		else
		{
			if (lastDiceResult.areResultsEquals())
				doubleDiceResult += 1;
			else
				doubleDiceResult = 0;
			if (doubleDiceResult == 3)
			{
				ClientMain.getConnection().send(ClientMessages.GO_TO_JAIL_MESSAGE_NAME, Serializer
						.toJson(new GoToJailClientMessage(Game.getGameCode(), player)));
				Platform.runLater(() -> AlertUtil
						.showInformationAlert("Finiti in prigione", "Siete finiti in prigione", "Siete finiti in " +
								"prigione dopo aver fatto tre volte il tiro dei dadi doppio"));

			}
			else
			{
				Message moveMessage =
						new MovePlayerClientMessage(Game.getPlayer(), lastDiceResult.resultsSum(), Game.getGameCode());
				ClientMain.getConnection()
						.send(ClientMessages.MOVE_PLAYER_MESSAGE_NAME, Serializer.toJson(moveMessage));
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

	/**
	 * Getter for the number of dice thrown in prison
	 *
	 * @return the number of dice thrown in prison
	 */
	public int getThrowDiceInPrison()
	{
		return throwDiceInPrison;
	}

	/**
	 * Setter for the number of dice thrown in prison
	 *
	 * @param throwDiceInPrison the int of number of dice thrown
	 */
	public void setThrowDiceInPrison(int throwDiceInPrison)
	{
		Game.throwDiceInPrison = throwDiceInPrison;
	}

	/**
	 * Getter for the counter of double dice results
	 *
	 * @return the counter of double dice results
	 */
	public int getDoubleDiceResult()
	{
		return doubleDiceResult;
	}

	/**
	 * Setter for the counter of double dice result
	 *
	 * @param doubleDiceResult the counter of double dice result
	 */
	public void setDoubleDiceResult(int doubleDiceResult)
	{
		Game.doubleDiceResult = doubleDiceResult;
	}
}
