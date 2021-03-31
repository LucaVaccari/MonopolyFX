package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.squares.Square;

/**
 * The board info message (receive only)
 */
public class BoardClientMessage implements Message
{
    private Square[] board;

    /**
     * Constructor for BoardClientMessage
     * @param board the game board
     */
    public BoardClientMessage(Square[] board)
    {
        this.board = board;
    }

    /**
     * Replace the current board with the given one
     * @param connection connection of the player
     * @param player player
     */
    @Override
    public void onReceive(Connection connection, Player player)
    {
        Game.getGameManager().setBoard(board);
    }
}
