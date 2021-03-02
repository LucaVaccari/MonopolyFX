package it.castelli.gameLogic.contracts;

public class CompanyContract extends Contract
{
	public CompanyContract(String name, int value, int mortgageValue)
	{
		this.name = name;
		this.value = value;
		this.mortgageValue = mortgageValue;
	}

	public int getRevenue()
	{
		// TODO: check if owner owns both companies
		// dice roll
		// return diceResult * (has both ? 10 : 4)
		return 0;
	}
}
