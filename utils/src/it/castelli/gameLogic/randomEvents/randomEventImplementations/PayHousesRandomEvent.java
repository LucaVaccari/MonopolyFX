package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;

/**
 * Random event to make a player pay a certain amount based on the number of houses he owns
 */
public class PayHousesRandomEvent extends RandomEvent
{
	/**
	 * Constructor for the PayHousesRandomEvent
	 * @param message The message shown to the player when he draws the card
	 */
	public PayHousesRandomEvent(String message)
	{
		super(message);
	}

	/**
	 * Make the player pay a certain amount based on the number of houses he has
	 * @param player The player who must pay
	 */
	@Override
	public void applyEffect(Player player)
	{

	}
}
