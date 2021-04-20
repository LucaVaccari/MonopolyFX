package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventType;

/**
 * Random event to make a player go to jail
 */
public class JailRandomEvent extends RandomEvent
{
	/**
	 * Jail square index
	 */
	private final int jailSquareIndex;

	/**
	 * Constructor for the JailRandomEvent
	 *
	 * @param message The message shown to the player when drawing this card
	 * @param randomEventType The type of the random event
	 * @param square Jail square index
	 */
	public JailRandomEvent(String message, RandomEventType randomEventType, int square)
	{
		super(message, randomEventType);
		this.jailSquareIndex = square;
	}

	/**
	 * Move the player to a specific cell, checking if it passes through the Go square
	 *
	 * @param player The player who drew the card and must be moved
	 * @param gameManager The game manager of the game
	 * @param gameManager The game manager of the game
	 */
	@Override
	public void applyEffect(Player player, GameManager gameManager)
	{
		player.setInPrison(true);
		player.setPosition(jailSquareIndex, false);
		gameManager.addRandomEvent(this, getType());
	}
}
