package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message to inform the client about a kicked player (send only)
 */
public class PlayerKickedServerMessage implements Message
{
    /**
     * The player who got kicked
     */
    private final Player player;

    /**
     * Constructor for PlayerKickedServerMessage
     *
     * @param player The player who got kicked
     */
    public PlayerKickedServerMessage(Player player)
    {
        this.player = player;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
