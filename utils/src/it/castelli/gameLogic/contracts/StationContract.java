package it.castelli.gameLogic.contracts;

import it.castelli.gameLogic.Player;

public class StationContract extends Contract
{
	public StationContract(String name, int value, int revenueBase, int mortgageValue)
	{
		this.name = name;
		this.value = value;
		this.revenue = revenueBase;
		this.mortgageValue = mortgageValue;
	}

	public int getRevenue(Player player)
	{
		// TODO: check the number of stations
		// return 2^(numberOfStations - 1) * revenue
		return 0;
	}
}
