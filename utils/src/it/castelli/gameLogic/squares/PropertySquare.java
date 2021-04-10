package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;
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
	public void interact(Player player)
	{
		if (contract.getOwner() == null)
		{
			//TODO: give the player the possibility to buy the property
		}
		else
		{
			int revenue = contract.getRevenue();
			player.removeMoney(revenue);
			contract.getOwner().addMoney(revenue);
			// TODO: Send the message to all Clients
		}
	}
}
