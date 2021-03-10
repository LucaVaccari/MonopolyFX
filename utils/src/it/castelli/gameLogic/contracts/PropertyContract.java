package it.castelli.gameLogic.contracts;

/**
 * Contract for a property (all terrains)
 */
public class PropertyContract extends Contract
{
	/**
	 * The list of revenues (revenues of the property when a player lands on
	 * it) for each house quantity
	 */
	private final int[] revenues;
	/**
	 * The color of the property
	 */
	private final PropertyColor color;
	/**
	 * The cost of each house
	 */
	private final int houseCost;
	/**
	 * The number of houses bought by the player (5 houses = hotel)
	 */
	private int numberOfHouses = 0;

	/**
	 * Constructor of the PropertyContract
	 *
	 * @param name           The name of the contract
	 * @param value          The cost of the property when buying from the bank
	 * @param revenueBase    The revenue of the property when a player lands
	 *                       on it when the property has no houses
	 * @param revenue1House  The revenue with 1 house
	 * @param revenue2Houses The revenue with 2 houses
	 * @param revenue3Houses The revenue with 3 houses
	 * @param revenue4Houses The revenue with 4 houses
	 * @param revenueHotel   The revenue with the hotel (5 houses)
	 * @param houseCost      The cost of each house
	 * @param color          The color of the property
	 */
	public PropertyContract(String name, int value, int revenueBase,
	                        int revenue1House, int revenue2Houses,
	                        int revenue3Houses, int revenue4Houses,
	                        int revenueHotel, int houseCost,
	                        PropertyColor color)
	{
		this.name = name;
		this.value = value;
		this.revenues =
				new int[]{
						revenueBase, revenue1House, revenue2Houses,
						revenue3Houses, revenue4Houses, revenueHotel
				};
		this.mortgageValue = value / 2;
		this.houseCost = houseCost;
		this.color = color;
	}

	/**
	 * Add houses to the property (this function DOES NOT take money from
	 * player)
	 *
	 * @param numberOfHouses The number of houses to add
	 */
	public void addHouses(int numberOfHouses)
	{
		if (this.numberOfHouses + numberOfHouses <= 5)
			this.numberOfHouses += numberOfHouses;
		else
			this.numberOfHouses = 5;
	}

	/**
	 * Revome houses from the property (this function DOES NOT give money to
	 * the player)
	 *
	 * @param numberOfHouses The number of houses to remove
	 */
	public void removeHouses(int numberOfHouses)
	{
		if (this.numberOfHouses - numberOfHouses >= 0)
			this.numberOfHouses -= numberOfHouses;
		else
			this.numberOfHouses = 0;
	}

	/**
	 * Getter for the number of houses
	 *
	 * @return The number of houses on the property
	 */
	public int getNumberOfHouses()
	{
		return numberOfHouses;
	}

	/**
	 * Getter for the house cost
	 *
	 * @return The cost of each house
	 */
	public int getHouseCost()
	{
		return houseCost;
	}

	/**
	 * Getter for the revenue
	 *
	 * @return The revenue of the property when a player lands on it (taking
	 * care of the other properties)
	 */
	@Override
	public int getRevenue()
	{
		if (this.numberOfHouses == 0) // TODO: && player has all properties)
			return revenues[0] * 2;
		return revenues[numberOfHouses];
	}
}
