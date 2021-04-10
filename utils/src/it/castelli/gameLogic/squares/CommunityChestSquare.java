package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventManager;

/**
 * Square which makes the player pick a community chest card
 */
public class CommunityChestSquare implements Square
{

	private RandomEventManager randomEventManager;

	public CommunityChestSquare(RandomEventManager randomEventManager)
	{
		this.randomEventManager = randomEventManager;
	}

	/**
	 * Pick a community chest card
	 *
	 * @param player The player who landed on the square
	 */
	@Override
	public void interact(Player player)
	{
		RandomEvent randomEvent = randomEventManager.drawCommunityChest();
		randomEvent.applyEffect(player);
	}

	@Override
	public Contract getContract()
	{
		return null;
	}
}
