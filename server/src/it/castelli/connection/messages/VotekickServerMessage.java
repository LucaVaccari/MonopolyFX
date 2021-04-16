package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;

/**
 * Message that adds a vote to the given player, if the number of votes is high enough the player will be removed (receive only)
 */
public class VotekickServerMessage implements Message
{
    /**
     * The player to kick
     */
    private final Player player;

    /**
     * Is the request to kick the player? (if false this message will remove the vote previously added)
     */
    private final boolean kick;

    /**
     * The game code
     */
    private final int gameCode;

    /**
     * Constructor for VotekickServerMessage (do not use)
     *  @param player The player to kick
     * @param kick Is the request to kick the player? (if false this message will remove the vote previously added)
     * @param gameCode The game code
     */
    public VotekickServerMessage(Player player, boolean kick, int gameCode)
    {
        this.player = player;
        this.kick = kick;
        this.gameCode = gameCode;
    }


    @Override
    public void onReceive(Connection connection, Player player)
    {
        GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
        GameManager gameManager = gameConnectionManager.getGameManager();

        Player playerToKick = gameManager.getSamePlayer(this.player);
        playerToKick.addVote();

        if (playerToKick.getNumberOfVotes() >= gameManager.getPlayers().size() - 1)
        {
            gameConnectionManager.removePlayer(gameConnectionManager.getConnectionFromPlayer(playerToKick));
        }

        gameConnectionManager.updatePlayers();
    }
}
