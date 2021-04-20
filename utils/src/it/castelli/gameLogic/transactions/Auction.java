package it.castelli.gameLogic.transactions;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Class containing an auction
 */
public class Auction
{
	/**
	 * contract in the auction
	 */
	private final Contract contract;

	/**
	 * integer of the best offer proposed
	 */
	private int bestOfferProposed;

	/**
	 * Player who offered the best offer
	 */
	private Player player;

	/**
	 *  Constructor of Auction
	 */
	public Auction(Contract contract, int bestOfferProposed, Player player)
	{
		this.contract = contract;
		this.bestOfferProposed = bestOfferProposed;
		this.player = player;
	}

	/**
	 * Getter for the contract
	 *
	 * @return contract in the auction
	 */
	public Contract getContract()
	{
		return contract;
	}

	/**
	 * Getter for the bestOfferProposed
	 *
	 * @return integer of the best offer proposed
	 */
	public int getBestOfferProposed()
	{
		return bestOfferProposed;
	}

	/**
	 * Setter for the bestOfferProposed
	 *
	 * @param bestOfferProposed integer of the best offer proposed
	 */
	public void setBestOfferProposed(int bestOfferProposed)
	{
		this.bestOfferProposed = bestOfferProposed;
	}

	/**
	 * Get the player with the highest offer (null if no one offered)
	 *
	 * @return The winning player
	 */
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * Setter for the winning player
	 *
	 * @param player the winning player
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}
}
