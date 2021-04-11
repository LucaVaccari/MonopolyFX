package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventManager;
import it.castelli.gameLogic.randomEvents.RandomEventType;

/**
 * Random event for giving money to a player
 */
public class YouWonRandomEvent extends RandomEvent
{
	private final int reward;

	/**
	 * Constructor for the YouWonRandomEvent
	 *
	 * @param message The message shown to the player when drawing this card
	 */
	public YouWonRandomEvent(String message, RandomEventManager randomEventManager, RandomEventType randomEventType, int reward)
	{
		super(message, randomEventManager, randomEventType);
		this.reward = reward;
	}

	/**
	 * Give money to the player
	 *
	 * @param player The player who drew the cards
	 */
	@Override
	public void applyEffect(Player player)
	{
		player.addMoney(reward);
		if (super.getType() == RandomEventType.CHANCE)
			super.getRandomEventManager().addChance(this);
		else
			super.getRandomEventManager().addCommunityChest(this);
	}
}
