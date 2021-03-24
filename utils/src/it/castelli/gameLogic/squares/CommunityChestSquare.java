package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

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
	public void interact(Player player)
	{
		// TODO: pick a CommunityChest card
	}

	@Override
	public Contract getContract()
	{
		return null;
	}
}
