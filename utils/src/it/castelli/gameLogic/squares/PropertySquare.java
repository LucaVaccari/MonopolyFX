package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;

/**
 * Square containing a property
 */
public class PropertySquare implements Square
{
	/**
	 * The PropertyContract containing information about the property
	 */
	private final PropertyContract contract;

	/**
	 * Constructor for the PropertySquare
	 *
	 * @param contract The PropertyContract containing information about the
	 *                 property
	 */
	public PropertySquare(PropertyContract contract)
	{
		this.contract = contract;
	}

	/**
	 * Getter for the contract
	 *
	 * @return The PropertyContract containing information about the property
	 */
	public PropertyContract getContract()
	{
		return contract;
	}

	/**
	 * If it's not bought yet, give the player the possibility to buy it,
	 * otherwise make the player
	 * pay the owner
	 *
	 * @param player The player who landed on the square
	 */
	@Override
	public void interact(Player player, GameManager gameManager)
	{
		int revenue = contract.getRevenue();
		Player owner = gameManager.getSamePlayer(contract.getOwner().toPlayer());

		//checks if the owner owns all the company of the set
		int numberOfPropertiesOfTheSameSet = 0;
		for (Contract contract : owner.getContracts())
		{
			if (contract instanceof PropertyContract)
			{
				if (((PropertyContract) contract).getColor() == this.contract.getColor())
					numberOfPropertiesOfTheSameSet++;
			}
		}
		if (this.contract.getNumberOfHouses() == 0 && numberOfPropertiesOfTheSameSet == this.contract.getColorSetContractNumber())
			revenue *= 2;


	}
}
