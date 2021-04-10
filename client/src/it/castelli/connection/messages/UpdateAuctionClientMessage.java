package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Auction;
import it.castelli.gui.controllers.AuctionController;
import javafx.application.Platform;

/**
 * Message received from the server that updates the auction (receive only)
 */
public class UpdateAuctionClientMessage implements Message
{
    /**
     * The updated auction
     */
    private final Auction auction;

    /**
     * Constructor for UpdateAuctionClientMessage (do not use)
     *
     * @param auction The updated auction
     */
    public UpdateAuctionClientMessage(Auction auction)
    {
        this.auction = auction;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        Game.getGameManager().setAuction(auction);
        Platform.runLater(() -> AuctionController.getInstance().update());
    }
}
