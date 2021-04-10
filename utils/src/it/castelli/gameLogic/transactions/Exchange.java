package it.castelli.gameLogic.transactions;

import it.castelli.gameLogic.Player;


public class Exchange
{
	private final Player player1;
	private final Player player2;
	private Asset asset1;
	private Asset asset2;
	private Boolean accepted1 = false;
	private Boolean accepted2 = false;

	public Exchange(Player player1, Player player2)
	{
		this.player1 = player1;
		this.player2 = player2;
		asset1 = new Asset();
		asset2 = new Asset();
	}

	public Player getPlayer1()
	{
		return player1;
	}

	public Player getPlayer2()
	{
		return player2;
	}

	public void changeAsset(Player player, Asset newOffer)
	{
		if (player == this.player1)
			asset1 = newOffer;
		else
			asset2 = newOffer;
	}

	public void acceptExchange(Player player)
	{
		if (player == this.player1)
			accepted1 = true;
		else
			accepted2 = true;
	}

	public void endExchange()
	{
		asset1.removeFromPlayer(player1);
		asset1.addToPlayer(player2);
		asset2.removeFromPlayer(player2);
		asset2.addToPlayer(player1);
	}

	public Boolean getAccepted1()
	{
		return accepted1;
	}

	public Boolean getAccepted2()
	{
		return accepted2;
	}

	public Asset getAsset1()
	{
		return asset1;
	}

	public Asset getAsset2()
	{
		return asset2;
	}

	@Override
	public boolean equals(Object obj)
	{
		return arePlayersEquals((Exchange) obj);

	}

	public boolean arePlayersEquals(Exchange exchange)
	{
		return (((Exchange) exchange).player1 == this.player1 || ((Exchange) exchange).player2 == this.player2) &&
				(((Exchange) exchange).player2 == this.player1 || ((Exchange) exchange).player2 == this.player2);
	}

}