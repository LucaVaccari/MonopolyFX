package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Auction;

/**
 * Message to update the auction (send only)
 */
public class UpdateAuctionServerMessage implements Message
{
    /**
     * The updated auction
     */
    private final Auction auction;

    /**
     * Constructor for UpdateAuctionServerMessage
     *
     * @param auction The updated auction
     */
    public UpdateAuctionServerMessage(Auction auction)
    {
        this.auction = auction;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //do nothing
    }
}
