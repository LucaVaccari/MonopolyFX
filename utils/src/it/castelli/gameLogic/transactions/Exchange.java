package it.castelli.gameLogic.transactions;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;

import java.util.Objects;

/**
 * Exchange class
 */
public class Exchange
{
	/**
	 * A player who wants to exchange something
	 */
	private final Player player1;

	/**
	 * Another player who wants to exchange something
	 */
	private final Player player2;

	/**
	 * The asset to exchange of the first player
	 */
	private Asset asset1;

	/**
	 * The asset to exchange of the second player
	 */
	private Asset asset2;

	/**
	 * Indicate if the first player has accepted the exchange conditions
	 */
	private Boolean accepted1 = false;

	/**
	 * Indicate if the second player has accepted the exchange conditions
	 */
	private Boolean accepted2 = false;

	/**
	 * The constructor for the Exchange class
	 *
	 * @param player1 A player who wants to exchange something
	 * @param player2 Another player who wants to exchange something
	 */
	public Exchange(Player player1, Player player2)
	{
		this.player1 = player1;
		this.player2 = player2;
		asset1 = new Asset();
		asset2 = new Asset();
	}

	/**
	 * Get the first player of the exchange
	 *
	 * @return A player who wants to exchange something
	 */
	public Player getPlayer1()
	{
		return player1;
	}

	/**
	 * Get the second player of the exchange
	 *
	 * @return Another player who wants to exchange something
	 */
	public Player getPlayer2()
	{
		return player2;
	}

	/**
	 * Change the exchange offer of a specific player
	 *
	 * @param player The player who updates his/er offer
	 * @param newOffer The new offer
	 */
	public void changeAsset(Player player, Asset newOffer)
	{
		if (player.betterEquals(this.player1))
			asset1 = newOffer;
		else
			asset2 = newOffer;
	}

	/**
	 * Accept the exchange by a player
	 *
	 * @param player The player who wants to accept the exchange
	 */
	public void acceptExchange(Player player)
	{
		if (player.betterEquals(this.player1))
			accepted1 = true;
		else
			accepted2 = true;
	}

	/**
	 * Undo the accept action by a player
	 *
	 * @param player Player who has changed his/er mind
	 */
	public void undoAcceptExchange(Player player)
	{
		if (player.betterEquals(this.player1))
			accepted1 = false;
		else
			accepted2 = false;
	}

	/**
	 * End exchange
	 *
	 * @param player1 A player who has participated to the exchange
	 * @param player2 Another player who has participated to the exchange
	 * @param gameManager
	 */
	public void endExchange(Player player1, Player player2, GameManager gameManager)
	{
		asset1.removeFromPlayer(player1, gameManager);
		asset1.addToPlayer(player2, gameManager);
		asset2.removeFromPlayer(player2, gameManager);
		asset2.addToPlayer(player1, gameManager);
	}

	/**
	 * Get if the first player has accepted the conditions of exchange
	 *
	 * @return Indicate if the first player has accepted the exchange conditions
	 */
	public Boolean getAccepted1()
	{
		return accepted1;
	}

	/**
	 * Get if the second player has accepted the conditions of exchange
	 *
	 * @return Indicate if the second player has accepted the exchange conditions
	 */
	public Boolean getAccepted2()
	{
		return accepted2;
	}

	/**
	 * Get the asset of the first player
	 *
	 * @return The asset to exchange of the first player
	 */
	public Asset getAsset1()
	{
		return asset1;
	}

	/**
	 * Get the asset of the first player
	 *
	 * @return The asset to exchange of the second player
	 */
	public Asset getAsset2()
	{
		return asset2;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Exchange)
			return arePlayersEquals((Exchange) obj);
		return false;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(player1, player2);
	}

	public boolean arePlayersEquals(Exchange exchange)
	{
		return (exchange.player1.betterEquals(this.player1) || exchange.player1.betterEquals(this.player2)) &&
				(exchange.player2.betterEquals(this.player1) || exchange.player2.betterEquals(this.player2));
	}

}