package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.CompanyContract;

/**
 * Square containing a company
 */
public class CompanySquare implements Square
{
	/**
	 * The CompanyContract containing information about this company
	 */
	private final CompanyContract contract;

	/**
	 * Constructor for the CompanySquare
	 *
	 * @param contract The contract of the company
	 */
	public CompanySquare(CompanyContract contract)
	{
		this.contract = contract;
	}

	/**
	 * Getter for the contract
	 *
	 * @return The CompanyContract containing information about this company
	 */
	public CompanyContract getContract()
	{
		return contract;
	}

	/**
	 * If it's not bought yet, give the player the possibility to buy,
	 * otherwise make the player
	 * pay
	 * the owner
	 *
	 * @param player The player who landed on the square
	 */
	@Override
	public void interact(Player player)
	{
		// TODO: invoke company method
	}
}
