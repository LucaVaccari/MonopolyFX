package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to exit prison (send only)
 */
public class ExitFromPrisonClientMessage implements Message
{
    /**
     * The player that wants to exit from prison
     */
    private final Player player;

    /**
     * The game code
     */
    private final int gameCode;

    /**
     * Does the player pay to exit?
     */
    private final boolean pay;

    /**
     * Constructor for ExitFromPrisonClientMessage
     *
     *  @param player The player that wants to exit from prison
     * @param gameCode The game code
     * @param pay Does the player pay to exit?
     */
    public ExitFromPrisonClientMessage(Player player, int gameCode, boolean pay)
    {
        this.player = player;
        this.gameCode = gameCode;
        this.pay = pay;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
