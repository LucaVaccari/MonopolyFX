package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventType;


/**
 * Random event to make a player go back by number of steps that are indicated on the card
 */
public class GoBackRandomEvent extends RandomEvent
{
	/**
	 * Number of steps that the player must go back
	 */
	private final int numberOfSteps;

	/**
	 * Constructor for the GoBackRandomEvent
	 *
	 * @param message The message shown to the player when drawing this card
	 * @param randomEventType The type of the random event
	 * @param numberOfSteps Number of steps that the player must go back
	 */
	public GoBackRandomEvent(String message, RandomEventType randomEventType, int numberOfSteps)
	{
		super(message, randomEventType);
		this.numberOfSteps = -numberOfSteps;
	}

	/**
	 * Move a player back by number of steps indicated on the card
	 *
	 * @param player The player who drew the card
	 * @param gameManager The game manager of the game
	 */
	@Override
	public void applyEffect(Player player, GameManager gameManager)
	{
		player.move(numberOfSteps);
		gameManager.addRandomEvent(this, getType());
	}
}
