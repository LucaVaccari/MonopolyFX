package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventType;

/**
 * Random event to make a player move to a specific square (checking if it passes through the go square)
 */
public class GoToRandomEvent extends RandomEvent
{
	/**
	 * The index of the square to move the player to
	 */
	private final int square;

	/**
	 * Does the player pass through go square in the journey?
	 */
	private final boolean passThroughGo;

	/**
	 * Constructor for the GoToRandomEvent
	 *
	 * @param message The description of this random event
	 * @param randomEventType The type of this randomEvent (chance / community chest)
	 * @param square The index of the square to move the player to
	 * @param passThroughGo Does the player pass through go square in the journey?
	 */
	public GoToRandomEvent(String message, RandomEventType randomEventType, int square, boolean passThroughGo)
	{
		super(message, randomEventType);
		this.square = square;
		this.passThroughGo = passThroughGo;
	}

	/**
	 * Move the player to a specific cell, checking if it passes through the Go square
	 *
	 * @param player The player who drew the card and must be moved
	 * @param gameManager The GameManager of the game
	 */
	@Override
	public void applyEffect(Player player, GameManager gameManager)
	{
		player.setPosition(square, passThroughGo);
		gameManager.addRandomEvent(this, getType());
	}
}
