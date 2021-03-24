package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * The first square of the board
 */
public class GoSquare implements Square
{
	/**
	 * Do nothing
	 *
	 * @param player The player who landed on the square
	 */
	@Override
	public void interact(Player player)
	{
		// do nothing
	}

	@Override
	public Contract getContract()
	{
		return null;
	}
}
