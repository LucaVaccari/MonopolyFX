package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gameLogic.contracts.Contract;

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
	public void interact(Player player, GameManager gameManager)
	{
		int revenue = player.getPosition() - player.getPreviousPosition();
		Player owner = gameManager.getSamePlayer(contract.getOwner().toPlayer());

		int numberOfCompaniesOwned = 0;
		for (Contract contract : owner.getContracts())
		{
			if (contract instanceof CompanyContract)
				numberOfCompaniesOwned++;
		}

		if (numberOfCompaniesOwned == 2)
			revenue *= 10;
		else
			revenue *= 4;

		System.out.println("CompanySquare, the player has paid " + revenue);

		player.removeMoney(revenue);
		owner.addMoney(revenue);
	}

}
