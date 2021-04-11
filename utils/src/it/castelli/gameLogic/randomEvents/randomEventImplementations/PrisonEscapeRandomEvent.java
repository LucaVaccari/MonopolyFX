package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventManager;
import it.castelli.gameLogic.randomEvents.RandomEventType;

/**
 * Random event (which can be kept) to escape from the prison
 */
public class PrisonEscapeRandomEvent extends RandomEvent
{
	/**
	 * Constructor for the PrisonEscapeRandomEvent
	 *
	 * @param message The message shown from the player when drawing this card
	 */
	public PrisonEscapeRandomEvent(String message, RandomEventManager randomEventManager, RandomEventType randomEventType)
	{
		super(message, randomEventManager, randomEventType);
	}

	/**
	 * Give this card to the player
	 *
	 * @param player The player who drew the card
	 */
	@Override
	public void applyEffect(Player player)
	{
		if (!player.isInPrison())
			player.getKeptRandomEventCards().add(this);
		else
		{
			player.setInPrison(false);
			player.getKeptRandomEventCards().remove(this);
			if (super.getType() == RandomEventType.CHANCE)
				super.getRandomEventManager().addChance(this);
			else
				super.getRandomEventManager().addCommunityChest(this);
		}

	}
}
