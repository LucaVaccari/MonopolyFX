package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventManager;
import it.castelli.gameLogic.randomEvents.RandomEventType;

/**
 * Random event to make a player move to a specific square (checking if it
 * passes through the go
 * square)
 */
public class GoToRandomEvent extends RandomEvent
{
	private final int square;
	private final boolean passThroughGo;

	/**
	 * Constructor for the GoToRandomEvent
	 *
	 * @param message The message shown to the player when drawing this card
	 */
	public GoToRandomEvent(String message, RandomEventManager randomEventManager, RandomEventType randomEventType, int square, boolean passThroughGo)
	{
		super(message, randomEventManager, randomEventType);
		this.square = square;
		this.passThroughGo = passThroughGo;
	}

	/**
	 * Move the player to a specific cell, checking if it passes through the
	 * Go square
	 *
	 * @param player The player who drew the card and must be moved
	 */
	@Override
	public void applyEffect(Player player)
	{
		player.setPosition(square, passThroughGo);
		if (super.getType() == RandomEventType.CHANCE)
			super.getRandomEventManager().addChance(this);
		else
			super.getRandomEventManager().addCommunityChest(this);
	}
}
