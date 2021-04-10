package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Auction;

public class UpdateAuctionClientMessage implements Message
{
    private final Auction auction;

    public UpdateAuctionClientMessage(Auction auction)
    {
        this.auction = auction;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        Game.getGameManager().setAuction(auction);
        //TODO: refresh Auction window
    }
}
