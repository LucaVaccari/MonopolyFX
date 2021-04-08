package it.castelli.gameLogic.transactions;

import it.castelli.gameLogic.Player;



public class Exchange
{
	private Player offerer;
	private Player offerer1;
	private Asset asset;
	private Asset asset1;
	private Boolean accepted = false;
	private Boolean accepted1 = false;

	public Player getOfferer()
	{
		return offerer;
	}

	public Exchange(Player offerer, Player offerer1)
	{
		this.offerer = offerer;
		this.offerer1 = offerer1;
	}

	public void changeAsset(Player player, Asset newOffer)
	{
		if (player == offerer)
			asset = newOffer;
		else
			asset1 = newOffer;
	}

	public void acceptExchange(Player player)
	{
		if (player == offerer)
			accepted = true;
		else
			accepted1 = true;

		if (accepted && accepted1)
			endExchange();
	}

	public void endExchange()
	{
		asset.removeFromPlayer(offerer);
		asset.addToPlayer(offerer1);
		asset1.removeFromPlayer(offerer1);
		asset1.addToPlayer(offerer);
	}
}