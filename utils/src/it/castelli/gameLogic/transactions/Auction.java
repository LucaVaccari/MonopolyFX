package it.castelli.gameLogic.transactions;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

public class Auction
{
	private final Contract contract;
	private int bestOfferProposed;
	private Player player;

	public Auction(Contract contract, int bestOfferProposed, Player player)
	{
		this.contract = contract;
		this.bestOfferProposed = bestOfferProposed;
		this.player = player;
	}


	public Contract getContract()
	{
		return contract;
	}

	public int getBestOfferProposed()
	{
		return bestOfferProposed;
	}

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

	public void setPlayer(Player player)
	{
		this.player = player;
	}
}
