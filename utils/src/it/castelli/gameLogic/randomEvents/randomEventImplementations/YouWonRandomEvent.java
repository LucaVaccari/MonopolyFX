package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;

/**
 * Random event for giving money to a player
 */
public class YouWonRandomEvent extends RandomEvent
{
	/**
	 * Constructor for the YouWonRandomEvent
	 * @param message The message shown to the player when drawing this card
	 */
	public YouWonRandomEvent(String message)
	{
		super(message);
	}

	/**
	 * Give money to the player
	 * @param player The player who drew the cards
	 */
	@Override
	public void applyEffect(Player player)
	{

	}
}
