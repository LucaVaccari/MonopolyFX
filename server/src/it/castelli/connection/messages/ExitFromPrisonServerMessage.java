package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;

/**
 * Request to exit prison (receive only)
 */
public class ExitFromPrisonServerMessage implements Message
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
     * Constructor for ExitFromPrisonServerMessage (do not use)
     *
     *  @param player The player that wants to exit from prison
     * @param gameCode The game code
     * @param pay Does the player pay to exit?
     */
    public ExitFromPrisonServerMessage(Player player, int gameCode, boolean pay)
    {
        this.player = player;
        this.gameCode = gameCode;
        this.pay = pay;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        GameConnectionManager  gameConnectionManager= ConnectionManager.getInstance().getGames().get(gameCode);
        GameManager gameManager = gameConnectionManager.getGameManager();

        Player playerToFree = gameManager.getSamePlayer(this.player);
        playerToFree.setInPrison(false);
        if (this.pay)
            playerToFree.removeMoney(50);
        playerToFree.setPosition(10, false);

        gameConnectionManager.updatePlayers();
    }
}
