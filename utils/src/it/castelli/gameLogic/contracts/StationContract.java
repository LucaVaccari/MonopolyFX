package it.castelli.gameLogic.contracts;

import java.io.Serializable;

/**
 * Contract for a station
 */
public class StationContract extends Contract implements Serializable
{
	/**
	 * Constructor for the Station Contract
	 *
	 * @param name        The name of the station
	 * @param value       The cost of the property when buying it from the bank
	 * @param revenueBase The revenue when a player lands on it
	 */
	public StationContract(int id, String name, int value, int revenueBase)
	{
		super(id);
		this.name = name;
		this.value = value;
		this.revenue = revenueBase;
		this.mortgageValue = value / 2;
	}

	/**
	 * Getter for the revenue
	 *
	 * @return The revenue of the station when a player lands on it (taking care of the other stations)
	 */
	@Override
	public int getRevenue()
	{
		return revenue;
	}

	/**
	 * Get the revenue based on the number of station taken as int
	 *
	 * @param numberOfStation The number of station from which to calculate the revenue
	 * @return The revenue calculated
	 */
	public int getRevenueFromNumberOfStations(int numberOfStation)
	{
		return (int) (Math.pow(2, numberOfStation - 1) * revenue);
	}
}
