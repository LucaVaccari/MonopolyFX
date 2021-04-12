package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.transactions.Auction;
import it.castelli.gui.controllers.AuctionController;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Platform;

/**
 * Message received from the server when a new Auction starts (receive only)
 */
public class NewAuctionClientMessage implements Message
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
	 * Constructor for AuctionClientMessage (do not use)
	 *
	 * @param contract          The contract at auction
	 * @param player            The player
	 * @param bestOfferProposed The highest amount of money offered
	 */
	public NewAuctionClientMessage(Contract contract, Player player, int bestOfferProposed)
	{
		this.contract = contract;
		this.player = player;
		this.bestOfferProposed = bestOfferProposed;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Auction auction = new Auction(contract, bestOfferProposed, this.player);
		Game.getGameManager().setAuction(auction);
		Platform.runLater(() -> {
			SceneManager.getInstance().showScene(SceneType.AUCTION);
			AuctionController.getInstance().setAuction(auction);
		});
	}
}
