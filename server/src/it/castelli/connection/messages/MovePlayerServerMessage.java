package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.Player;

/**
 * Message from the client that moves the given player n squares forward (receive only)
 */
public class MovePlayerServerMessage implements Message
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
     * Constructor for MovePlayerServerMessage (do not use)
     *
     * @param player The player to move
     * @param squares The number of squares the player has to be moved forward
     * @param gameCode The game code
     */
    public MovePlayerServerMessage(Player player, int squares, int gameCode)
    {
        this.player = player;
        this.squares = squares;
        this.gameCode = gameCode;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
        for (Player element : gameConnectionManager.getGameManager().getPlayers())
        {
            if (element.betterEquals(this.player))
            {
                element.move(squares);
            }
        }
        System.out.println("MovePlayerServerMessage");
        gameConnectionManager.updatePlayers();
    }
}
