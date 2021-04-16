package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message sent to the last player in the game, which won (send only)
 */
public class VictoryServerMessage implements Message
{
    @Override
    public void onReceive(Connection connection, Player player)
    {
        //do nothing
    }
}
