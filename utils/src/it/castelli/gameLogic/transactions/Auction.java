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

	public Player getPlayer()
	{
		return player;
	}

	public void setBestOfferProposed(int bestOfferProposed)
	{
		this.bestOfferProposed = bestOfferProposed;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}
}
