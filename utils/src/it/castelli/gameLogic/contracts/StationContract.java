package it.castelli.gameLogic.contracts;

/**
 * Contract for a station
 */
public class StationContract extends Contract
{
	/**
	 * Constructor for the Station Contract
	 *
	 * @param name        The name of the station
	 * @param value       The cost of the property when buying it from the bank
	 * @param revenueBase The revenue when a player lands on it
	 */
	public StationContract(String name, int value, int revenueBase)
	{
		this.name = name;
		this.value = value;
		this.revenue = revenueBase;
		this.mortgageValue = value / 2;
	}

	/**
	 * Getter for the revenue
	 *
	 * @return The revenue of the station when a player lands on it (taking
	 * care of the other
	 * stations)
	 */
	@Override
	public int getRevenue()
	{
		//TODO: Move in StationSquare
		/*int numberOfOwnedStations = 0;

		for (Contract contract : owner.getContracts())
		{
			if (contract instanceof StationContract)
			{
				numberOfOwnedStations++;
			}
		}

		return 2^(numberOfOwnedStations - 1) * revenue;*/
		return 0;
	}

	/**
	 * Get the revenue based on the number of station taken as int
	 *
	 * @param numberOfStation The number of station from which to calculate
	 *                        the revenue
	 * @return The revenue calculated
	 */
	public int getRevenueFromNumberOfStations(int numberOfStation)
	{
		return (int) (Math.pow(2, numberOfStation - 1) * revenue);
	}
}
