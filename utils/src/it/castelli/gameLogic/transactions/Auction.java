package it.castelli.gameLogic.transactions;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class Auction
{
	private Contract contract;
	private int bestOfferProposed;
	private Player player;
	private Timer timer;

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
			timer.cancel();
			bestOfferProposed = offer;
			this.player = player;
			startAuction();
		}
	}

	public void startAuction()
	{
		timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
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
			//TODO: player.addContract(contract);
		}
	}
}
