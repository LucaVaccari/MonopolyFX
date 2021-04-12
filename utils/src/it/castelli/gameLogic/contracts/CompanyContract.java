package it.castelli.gameLogic.contracts;

import java.io.Serializable;

/**
 * Contract for a Company (Electric Company or Water Company)
 */
public class CompanyContract extends Contract implements Serializable
{
	/**
	 * The company enum
	 */
	private final Company company;

	/**
	 * Constructor for the CompanyContract
	 *
	 * @param name    The name of the contract
	 * @param company The Company enum
	 * @param value   The cost of the contract when buying from the bank
	 */
	public CompanyContract(String name, Company company, int value)
	{
		this.name = name;
		this.company = company;
		this.value = value;
		this.mortgageValue = value / 2;
	}

	/**
	 * Get the revenue of the contract (do NOT use)
	 *
	 * @return The revenue of the property when a player lands on it (taking care of the other company)
	 */
	@Override
	public int getRevenue()
	{
		return 0;
	}

	public Company getCompany()
	{
		return company;
	}

	/**
	 * Enumeration of companies: Electric company and Water works
	 */
	public enum Company
	{
		ELECTRIC, WATER;
	}
}
