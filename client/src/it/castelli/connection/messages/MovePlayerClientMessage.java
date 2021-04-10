package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to move the given player n squares forward (send only)
 */
public class MovePlayerClientMessage implements Message
{
    /**
     * The player to move
     */
    private final Player player;

    /**
     * The number of squares the player has to be moved forward
     */
    private final int squares;

    /**
     * The game code
     */
    private final int gameCode;

    /**
     * Constructor for MovePlayerClientMessage
     *
     * @param player The player to move
     * @param squares The number of squares the player has to be moved forward
     * @param gameCode The game code
     */
    public MovePlayerClientMessage(Player player, int squares, int gameCode)
    {
        this.player = player;
        this.squares = squares;
        this.gameCode = gameCode;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //do nothing
    }
}
