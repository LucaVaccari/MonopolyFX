package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;

/**
 * Random event for make every player give money to another
 */
public class BirthdayRandomEvent extends RandomEvent
{
	/**
	 * Constructor for the BirthdayRandomEvent
	 * @param message The message shown to the player when drawing this card
	 */
	public BirthdayRandomEvent(String message)
	{
		super(message);
	}

	/**
	 * Take money from each player and give them to one of them (the one who drew the card)
	 * @param player The player who drew the card, which will take money from all others
	 */
	@Override
	public void applyEffect(Player player)
	{

	}
}
