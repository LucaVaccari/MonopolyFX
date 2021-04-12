package it.castelli.gameLogic.randomEvents;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;

/**
 * Abstract class from which each RandomEvent derives. Represent a
 * RandomEvent, a card (either a
 * Chance or a CommunityChest) which can be drawn and will make the player do
 * something random
 */
public abstract class RandomEvent
{
	/**
	 * The message shown to the player when drawing the card
	 */
	private final String message;

	/**
	 * The type of the random event
	 */
	private final RandomEventType type;

	/**
	 * Generic constructor for the RandomEvent
	 *  @param message The message shown to the player when drawing the card
	 * @param type The type of random event (chance or community chest)
	 */
	public RandomEvent(String message, RandomEventType type)
	{
		this.message = message;
		this.type = type;
	}

	/**
	 * Getter for type
	 *
	 * @return The type of the random event
	 */
	public RandomEventType getType()
	{
		return type;
	}

	/**
	 * Apply the effect of the card (must be overridden by the subclasses)
	 *
	 * @param player The player who drew the card
	 */
	public abstract void applyEffect(Player player, GameManager manager);

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
