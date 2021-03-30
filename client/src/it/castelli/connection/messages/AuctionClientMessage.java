package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.transactions.Auction;

public class AuctionClientMessage implements Message
{
    private Contract contract;
    private Player player;
    private int bestOfferProposed;

    public AuctionClientMessage(Contract contract, Player player, int bestOfferProposed)
    {
        this.contract = contract;
        this.player = player;
        this.bestOfferProposed = bestOfferProposed;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        Game.getGameManager().setAuction(new Auction(contract, bestOfferProposed, this.player));
        //TODO: show auction window
    }
}
