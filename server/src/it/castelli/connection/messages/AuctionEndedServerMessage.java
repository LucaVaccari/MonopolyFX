package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message to close the auction in the client (send only)
 */
public class AuctionEndedServerMessage implements Message
{
    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
