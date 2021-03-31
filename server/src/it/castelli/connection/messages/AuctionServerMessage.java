package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Auction info message (send only)
 */
public class AuctionServerMessage implements Message
{
    private Contract contract;
    private Player player;
    private int bestOfferProposed;

    /**
     * Constructor for AuctionServerMessage
     * @param contract the contract to sell
     * @param player player with the best offer
     * @param bestOfferProposed the best offer value
     */
    public AuctionServerMessage(Contract contract, Player player, int bestOfferProposed)
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
