package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

/**
 * Player's offer info message (receive only)
 */
public class AuctionOfferServerMessage implements Message
{
    private int offer;
    private int gameCode;

    /**
     * Constructor for AuctionOfferServerMessage
     * @param offer player's offer value
     * @param gameCode game code
     */
    public AuctionOfferServerMessage(int offer, int gameCode)
    {
        this.offer = offer;
        this.gameCode = gameCode;
    }

    /**
     * Propose player's offer in the auction
     * @param connection connection of the player
     * @param player player
     */
    @Override
    public void onReceive(Connection connection, Player player)
    {
        ConnectionManager.getInstance().getGames().get(gameCode).offer(player, offer);
    }
}
