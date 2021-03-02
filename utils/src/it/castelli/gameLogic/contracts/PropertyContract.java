package it.castelli.gameLogic.contracts;

import it.castelli.gameLogic.Player;

public class PropertyContract extends Contract
{
	private final int[] revenues;

	public PropertyContract(String name, int value, int revenueBase, int revenue1House, int revenue2Houses,
	                        int revenue3Houses, int revenue4Houses, int revenueHotel, int mortgageValue)
	{
		this.name = name;
		this.value = value;
		this.revenues =
				new int[]{revenueBase, revenue1House, revenue2Houses, revenue3Houses, revenue4Houses, revenueHotel};
		this.mortgageValue = mortgageValue;
	}

	public int getRevenue(int numberOfHouses, Player owner)
	{
		if (numberOfHouses == 0) // TODO: && player has all properties)
			return revenues[0] * 2;
		return revenues[numberOfHouses];
	}
}
