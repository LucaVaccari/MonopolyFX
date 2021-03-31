package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.squares.Square;

/**
 * The board info message (send only)
 */
public class BoardServerMessage implements Message
{
    private Square[] board;

    /**
     * Constructor for BoardServerMessage
     * @param board the game board
     */
    public BoardServerMessage(Square[] board)
    {
        this.board = board;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
