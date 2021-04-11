package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;

/**
 * Random event (which can be kept) to escape from the prison
 */
public class PrisonEscapeRandomEvent extends RandomEvent
{
	//TODO: verify if necessary
	private String message;
	/**
	 * Constructor for the PrisonEscapeRandomEvent
	 *
	 * @param message The message shown from the player when drawing this card
	 */
	public PrisonEscapeRandomEvent(String message)
	{
		super(message);
		this.message = message;
	}

	/**
	 * Give this card to the player
	 *
	 * @param player The player who drew the card
	 */
	@Override
	public void applyEffect(Player player)
	{
		// TODO: verify the implementation
		player.getKeptRandomEventCards().add(new PrisonEscapeRandomEvent(message));
		// TODO: send the message to all players
	}
}
