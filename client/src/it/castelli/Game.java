package it.castelli;

import it.castelli.connection.messages.*;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.dice.DiceResult;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.controllers.BoardController;
import it.castelli.serialization.Serializer;
import javafx.application.Platform;

import java.util.ArrayList;

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
	 * Is any auction active?
	 */
	private static boolean inAuction = false;

	/**
	 * The result from the last dice throw
	 */
	private static DiceResult lastDiceResult;

	/**
	 * is the player the host?
	 */
	private static boolean host;

	private static final ArrayList<Player> voteKickedPlayers = new ArrayList<>();

	/**
	 * Private constructor, to prevent instantiating this class
	 */
	private Game()
	{
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
						                                                                        "prigione pagando 50 " +
						                                                                        "M"));
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
						                                                                        "prigione tirando i " +
						                                                                        "dadi e facendo doppio" +
						                                                                        ". Complimenti!"));
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
						                                                                        "prigione dopo aver " +
						                                                                        "fatto tre volte il " +
						                                                                        "tiro dei dadi " +
						                                                                        "doppio"));

			}
			else
			{
				Message moveMessage =
						new MovePlayerClientMessage(Game.getPlayer(), lastDiceResult.resultsSum(), Game.getGameCode());
				ClientMain.getConnection()
						.send(ClientMessages.MOVE_PLAYER_MESSAGE_NAME, Serializer.toJson(moveMessage));
			}
		}
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
	 * Setter for lastDiceResult
	 *
	 * @param lastDiceResult the new dice result
	 */
	public static void setLastDiceResult(DiceResult lastDiceResult)
	{
		Game.lastDiceResult = lastDiceResult;
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
	 * Getter for inAuction
	 *
	 * @return Is any auction active?
	 */
	public static boolean isInAuction()
	{
		return inAuction;
	}

	/**
	 * Setter for inAuction
	 *
	 * @param inAuction The new auction state (active or inactive)
	 */
	public static void setInAuction(boolean inAuction)
	{
		Game.inAuction = inAuction;
	}

	/**
	 * Getter for the list of voteKicked player.
	 * DO NOT USE THE .contains() METHOD.
	 *
	 * @return The list of voteKicked players
	 * @see #getVoteKickedPlayers()
	 */
	public static ArrayList<Player> getVoteKickedPlayers()
	{
		return voteKickedPlayers;
	}

	/**
	 * Has the player been voted to kick?
	 *
	 * @param player The player to check
	 * @return True if the player has been kicked, false otherwise
	 */
	public static boolean hasKickedPlayer(Player player)
	{
		for (var kickedPlayer : voteKickedPlayers)
		{
			if (kickedPlayer.betterEquals(player))
				return true;
		}
		return false;
	}
}
