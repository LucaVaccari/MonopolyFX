package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.randomEvents.RandomEvent;

/**
 * Square which makes the player pick a community chest card
 */
public class CommunityChestSquare implements Square
{
	/**
	 * Pick a community chest card
	 *
	 * @param player The player who landed on the square
	 */
	@Override
	public void interact(Player player, GameManager gameManager)
	{
		RandomEvent randomEvent = gameManager.getRandomEventManager().drawCommunityChest();
		player.setLastEncounteredEvent("Community Chest", randomEvent.getMessage());
		randomEvent.applyEffect(player, gameManager);
	}

	@Override
	public Contract getContract()
	{
		return null;
	}
}
