package it.castelli.gameLogic.contracts;

public class PropertyContract extends Contract
{
	private final int[] revenues;
	private int numberOfHouses = 0;
	private int houseCost;

	public PropertyContract(String name, int value, int revenueBase, int revenue1House, int revenue2Houses,
	                        int revenue3Houses, int revenue4Houses, int revenueHotel, int mortgageValue, int houseCost)
	{
		this.name = name;
		this.value = value;
		this.revenues =
				new int[]{revenueBase, revenue1House, revenue2Houses, revenue3Houses, revenue4Houses, revenueHotel};
		this.mortgageValue = mortgageValue;
		this.houseCost = houseCost;
	}

	public void addHouses(int numberOfHouses)
	{
		if (this.numberOfHouses + numberOfHouses <= 5)
			this.numberOfHouses += numberOfHouses;
		else
			this.numberOfHouses = 5;
	}

	public void removeHouses(int numberOfHouses)
	{
		if (this.numberOfHouses - numberOfHouses >= 0)
			this.numberOfHouses -= numberOfHouses;
		else
			this.numberOfHouses = 0;
	}

	public int getNumberOfHouses()
	{
		return numberOfHouses;
	}

	public int getHouseCost()
	{
		return houseCost;
	}

	@Override
	public int getRevenue()
	{
		if (this.numberOfHouses == 0) // TODO: && player has all properties)
			return revenues[0] * 2;
		return revenues[numberOfHouses];
	}
}
