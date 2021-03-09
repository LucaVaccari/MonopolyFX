package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.StationContract;

/**
 * Square containing a station
 */
public class StationSquare implements Square
{
	/**
	 * The StationContract containing information about the station
	 */
	private final StationContract contract;

	/**
	 * Constructor for the StationSquare
	 * @param contract The StationContract containing information about the station
	 */
	public StationSquare(StationContract contract)
	{
		this.contract = contract;
	}

	/**
	 * Getter for the contract
	 * @return The StationContract containing information about the station
	 */
	public StationContract getContract()
	{
		return contract;
	}

	/**
	 * If it's not bought yet, give the player the possibility to buy it, otherwise make the player pay the owner
	 * @param player The player who landed on the square
	 */
	@Override
	public void interact(Player player)
	{
		// TODO: invoke station method
	}
}
