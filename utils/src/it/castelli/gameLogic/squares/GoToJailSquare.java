package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Square which makes the player go to jail without passing through go)
 */
public class GoToJailSquare implements Square
{
	/**
	 * Send the player to prison
	 *
	 * @param player The player to be arrested
	 */
	@Override
	public void interact(Player player, GameManager gameManager)
	{
		player.setInPrison(true);
		player.setLastEncounteredEvent("Prigione", "Andate in prigione direttamente e senza passare dal Via!");
		player.setPosition(10, false);
	}

	@Override
	public Contract getContract()
	{
		return null;
	}
}
