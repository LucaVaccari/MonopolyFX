package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventManager;

/**
 * Square which make the player pick a chance card
 */
public class ChanceSquare implements Square
{
	private RandomEventManager randomEventManager;

	public ChanceSquare(RandomEventManager randomEventManager)
	{
		this.randomEventManager = randomEventManager;
	}

	/**
	 * Pick a chance card
	 *
	 * @param player The player who landed on the square
	 */
	@Override
	public void interact(Player player, GameManager gameManager)
	{
		RandomEvent randomEvent = randomEventManager.drawChance();
		player.setLastRandomEvent("Chance", randomEvent.getMessage());
		randomEvent.applyEffect(player);
	}

	@Override
	public Contract getContract()
	{
		return null;
	}
}
