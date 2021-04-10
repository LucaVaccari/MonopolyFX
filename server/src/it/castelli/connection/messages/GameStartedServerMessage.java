package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message to start the game (send only)
 */
public class GameStartedServerMessage implements Message
{
    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
