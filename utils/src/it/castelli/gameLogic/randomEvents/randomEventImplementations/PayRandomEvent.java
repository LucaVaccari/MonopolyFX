package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;

/**
 * Random event for making a payer pay
 */
public class PayRandomEvent extends RandomEvent
{
	private final int cost;
	// TODO: add parameters (cost: int)

	/**
	 * Constructor for the PayRandomEvent
	 *
	 * @param message The message shown to the player when he draws the card
	 */
	public PayRandomEvent(String message, int cost)
	{
		super(message);
		this.cost = cost;
	}

	/**
	 * Take money from the player
	 *
	 * @param player The player who drew the card
	 */
	@Override
	public void applyEffect(Player player)
	{

	}
}
