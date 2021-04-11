package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Interface representing a square on the board
 */
public interface Square
{
	/**
	 * Method invoked when a player lands on the square
	 *
	 * @param player The player who landed on the square
	 */
	void interact(Player player, GameManager gameManager);

	/**
	 * Get the contract of the square (or null otherwise)
	 */
	Contract getContract();
}
