package it.castelli.gameLogic.transactions;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

import java.util.Timer;
import java.util.TimerTask;

public class Auction
{
	private Contract contract;
	private int bestOfferProposed;
	private Player player;
	private Timer timer;

	public Auction(Contract contract, int bestOfferProposed, Player player)
	{
		this.contract = contract;
		this.bestOfferProposed = bestOfferProposed;
		this.player = player;
	}

	public void offer(Player player, int offer)
	{
		if (bestOfferProposed < offer)
		{
			timer.cancel();
			bestOfferProposed = offer;
			this.player = player;
			startAuction();
		}
	}

	public void startAuction()
	{
		timer = new Timer();
		TimerTask task = new TimerTask()
		{
			public void run()
			{
				endAuction();
				timer.cancel();
			}
		};

		long delay = 10000L;
		timer.schedule(task, delay);
	}

	private void endAuction()
	{
		if (player != null)
		{
			player.addContract(contract);
		}
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
}
