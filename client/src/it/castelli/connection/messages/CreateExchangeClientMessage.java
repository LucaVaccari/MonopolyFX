package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to create a new exchange between two players (send only)
 */
public class CreateExchangeClientMessage implements Message
{
    /**
     * The first player
     */
    private final Player player1;

    /**
     * The second player
     */
    private final Player player2;

    /**
     * The game code
     */
    private final int gameCode;

    /**
     * Constructor for CreateExchangeClientMessage
     *
     * @param player1 The first player
     * @param player2 The second player
     * @param gameCode The game code
     */
    public CreateExchangeClientMessage(Player player1, Player player2, int gameCode)
    {
        this.player1 = player1;
        this.player2 = player2;
        this.gameCode = gameCode;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
