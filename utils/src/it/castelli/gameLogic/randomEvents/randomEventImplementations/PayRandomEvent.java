package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventType;

/**
 * Random event for making a payer pay
 */
public class PayRandomEvent extends RandomEvent
{
	/**
	 * The amount of money that this event will remove from the player
	 */
	private final int cost;

	/**
	 * Constructor for the PayRandomEvent
	 *
	 * @param message The message shown to the player when he draws the card
	 * @param randomEventType The type of the random event
	 * @param cost The amount of money that this event will remove from the player
	 */
	public PayRandomEvent(String message, RandomEventType randomEventType, int cost)
	{
		super(message, randomEventType);
		this.cost = cost;
	}

	/**
	 * Take money from the player
	 *
	 * @param player The player who drew the card
	 * @param gameManager  The game manager of the game
	 */
	@Override
	public void applyEffect(Player player, GameManager gameManager)
	{
		player.removeMoney(cost);
		gameManager.addRandomEvent(this, getType());
	}
}
