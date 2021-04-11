package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventManager;
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
	 */
	public PayRandomEvent(String message, RandomEventManager randomEventManager, RandomEventType randomEventType, int cost)
	{
		super(message, randomEventManager, randomEventType);
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
		player.removeMoney(cost);
		if (super.getType() == RandomEventType.CHANCE)
			super.getRandomEventManager().addChance(this);
		else
			super.getRandomEventManager().addCommunityChest(this);
	}
}
