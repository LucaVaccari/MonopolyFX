package it.castelli.gameLogic.transactions;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

public class Auction
{
	private Contract contract;
	private int bestOfferProposed;
	private Player player;

	public Auction(Contract contract)
	{
		this.contract = contract;
		bestOfferProposed = 10;
		player = null;
	}

	public void offer(Player player, int offer)
	{
		if (bestOfferProposed < offer)
		{
			bestOfferProposed = offer;
			this.player = player;
		}
	}

	public void startAuction()
	{
		//TODO: start timer
	}

	private void endAuction()
	{
		if (player != null)
		{
			player.getContracts().add(contract);
			//TODO: Print the winner of the auction
		}
	}
}
