package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message that closes the auction window (receive only)
 */
public class AuctionEndedClientMessage implements Message
{
    @Override
    public void onReceive(Connection connection, Player player)
    {
        //TODO: close the auction window
    }
}
