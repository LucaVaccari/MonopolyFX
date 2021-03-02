package it.castelli.gameLogic.contracts;

public class StationContract extends Contract
{
	public StationContract(String name, int value, int revenueBase, int mortgageValue)
	{
		this.name = name;
		this.value = value;
		this.revenue = revenueBase;
		this.mortgageValue = mortgageValue;
	}

	public int getRevenue()
	{
		// TODO: check the number of stations
		// return 2^(numberOfStations - 1) * revenue
		return 0;
	}
}
