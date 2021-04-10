package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Message to send a new auction to the client (send only)
 */
public class NewAuctionServerMessage implements Message
{
	/**
	 * The contract at auction
	 */
	private final Contract contract;

	/**
	 * The player
	 */
	private final Player player;

	/**
	 * The highest amount of money offered
	 */
	private final int bestOfferProposed;

	/**
	 * Constructor for NewAuctionServerMessage
	 *
	 * @param contract The contract at auction
	 * @param player The player
	 * @param bestOfferProposed The highest amount of money offered
	 */
	public NewAuctionServerMessage(Contract contract, Player player, int bestOfferProposed)
	{
		this.contract = contract;
		this.player = player;
		this.bestOfferProposed = bestOfferProposed;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
