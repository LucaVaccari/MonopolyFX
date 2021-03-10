package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;

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
}
