package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventType;
import it.castelli.gameLogic.squares.Square;

/**
 * Random event to make a player go to jail
 */
public class JailRandomEvent extends RandomEvent
{
	private final int square;

	/**
	 * Constructor for the JailRandomEvent
	 *
	 * @param message The message shown to the player when drawing this card
	 */
	public JailRandomEvent(String message, RandomEventType randomEventType, int square)
	{
		super(message, randomEventType);
		this.square = square;
	}

	/**
	 * Move the player to a specific cell, checking if it passes through the Go square
	 *
	 * @param player The player who drew the card and must be moved
	 */
	@Override
	public void applyEffect(Player player, GameManager gameManager)
	{
		player.setInPrison(true);
		player.setPosition(square, false);
		gameManager.addRandomEvent(this, getType());
	}
}
