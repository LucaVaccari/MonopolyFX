package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Square which make the player pick a chance card
 */
public class ChanceSquare implements Square
{
	/**
	 * Pick a chance card
	 *
	 * @param player The player who landed on the square
	 */
	@Override
	public void interact(Player player)
	{
		// TODO: pick a Chance card
	}

	@Override
	public Contract getContract()
	{
		return null;
	}
}
