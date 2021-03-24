package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Useless square
 */
public class JustVisitingSquare implements Square
{
	/**
	 * Do nothing
	 *
	 * @param player The player who landed on the square
	 */
	@Override
	public void interact(Player player)
	{
		// DO NOTHING
	}

	@Override
	public Contract getContract()
	{
		return null;
	}
}
