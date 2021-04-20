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
	 * @param type The type of the random event
	 * @param numberOfSteps Number of steps that the player must go back
	 */
	public GoBackRandomEvent(String message, RandomEventType type, int numberOfSteps)
	{
		super(message, type);
		this.numberOfSteps = -numberOfSteps;
	}

	/**
	 * Move a player back by number of steps indicated on the card
	 *
	 * @param player The player who drew the card
	 * @param manager The game manager of the game
	 */
	@Override
	public void applyEffect(Player player, GameManager manager)
	{
		player.move(numberOfSteps);
		manager.addRandomEvent(this, getType());
	}
}
