package it.castelli.gameLogic.contracts;

/**
 * Contract for a Company (Electric Company or Water Company)
 */
public class CompanyContract extends Contract
{
	/**
	 * Constructor for the CompanyContract
	 *
	 * @param name  The name of the contract
	 * @param value The cost of the contract when buying from the bank
	 */
	public CompanyContract(String name, int value)
	{
		this.name = name;
		this.value = value;
		this.mortgageValue = value / 2;
	}

	/**
	 * Get the revenue of the contract
	 *
	 * @return The revenue of the property when a player lands on it (taking care of the other
	 * 		company)
	 */
	@Override
	public int getRevenue()
	{
		// TODO: check if owner owns both companies
		// dice roll
		// return diceResult * (has both ? 10 : 4)
		return 0;
	}


}
