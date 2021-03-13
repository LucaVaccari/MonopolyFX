package it.castelli.gameLogic.randomEvents;

import it.castelli.gameLogic.Player;

/**
 * Abstract class from which each RandomEvent derives. Represent a RandomEvent, a card (either a
 * Chance or a CommunityChest) which can be drawn and will make the player do something random
 */
public abstract class RandomEvent
{
	/**
	 * The message shown to the player when drawing the card
	 */
	private final String message;

	/**
	 * Generic constructor for the RandomEvent
	 *
	 * @param message The message shown to the player when drawing the card
	 */
	public RandomEvent(String message)
	{
		this.message = message;
	}

	/**
	 * Apply the effect of the card (must be overridden by the subclasses)
	 *
	 * @param player The player who drew the card
	 */
	public abstract void applyEffect(Player player);

	/**
	 * Getter for the message
	 *
	 * @return The message shown to the player when drawing the card
	 */
	public String getMessage()
	{
		return message;
	}
}
