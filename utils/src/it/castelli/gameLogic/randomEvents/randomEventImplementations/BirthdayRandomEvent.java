package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventType;

/**
 * The random event that gives a player 10M for each other player in the game, and removes 10M to any other player
 */
public class BirthdayRandomEvent extends RandomEvent
{
	/**
	 * Constructor for BirthdayRandomEvent
	 *
	 * @param message The description of this random event
	 * @param type The type of this randomEvent (chance / community chest)
	 */
	public BirthdayRandomEvent(String message, RandomEventType type)
	{
		super(message, type);
	}

	/**
	 * Give a player 10M for each other player in the game, and removes 10M to any other player
	 *
	 * @param player The player who drew the card and must gain money
	 * @param gameManager The GameManager of the game
	 */
	@Override
	public void applyEffect(Player player, GameManager gameManager)
	{
		player.addMoney((gameManager.getPlayers().size() - 1) * 10);
		for (Player element : gameManager.getPlayers())
		{
			if (!element.betterEquals(player))
				element.removeMoney(10);
		}
		gameManager.addRandomEvent(this, getType());
	}
}
