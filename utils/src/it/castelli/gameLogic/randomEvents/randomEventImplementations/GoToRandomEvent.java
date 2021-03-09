package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;

/**
 * Random event to make a player move to a specific square (checking if it passes through the go square)
 */
public class GoToRandomEvent extends RandomEvent
{
	private final int square;
	private final boolean passThroughGo;

	/**
	 * Constructor for the GoToRandomEvent
	 * @param message The message shown to the player when drawing this card
	 */
	public GoToRandomEvent(String message, int square, boolean passThroughGo)
	{
		super(message);
		this.square = square;
		this.passThroughGo = passThroughGo;
	}

	/**
	 * Move the player to a specific cell, checking if it passes through the Go square
	 * @param player The player who drew the card and must be moved
	 */
	@Override
	public void applyEffect(Player player)
	{

	}
}
