package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.serialization.Serializer;

/**
 * Message from the client that creates a new exchange between two players (receive only)
 */
public class CreateExchangeServerMessage implements Message
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
     * Constructor for CreateExchangeServerMessage (do not use)
     *
     * @param player1 The first player
     * @param player2 The second player
     * @param gameCode The game code
     */
    public CreateExchangeServerMessage(Player player1, Player player2, int gameCode)
    {
        this.player1 = player1;
        this.player2 = player2;
        this.gameCode = gameCode;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
        GameManager gameManager = gameConnectionManager.getGameManager();

        Exchange exchange1 = gameManager.getExchangeFromPlayer(player1);
        Exchange exchange2 = gameManager.getExchangeFromPlayer(player2);

        Connection player1Connection = gameConnectionManager.getConnectionFromPlayer(player1);
        Connection player2Connection = gameConnectionManager.getConnectionFromPlayer(player2);

        if (exchange1 == null && exchange2 == null)
        {
            Exchange newExchange = new Exchange(player1, player2);
            gameManager.addExchange(newExchange);
            player1Connection.send(ServerMessages.NEW_EXCHANGE_MESSAGE_NAME, Serializer.toJson(new NewExchangeServerMessage(newExchange)));
            player2Connection.send(ServerMessages.NEW_EXCHANGE_MESSAGE_NAME, Serializer.toJson(new NewExchangeServerMessage(newExchange)));
        }
        else
        {
            connection.send(ServerMessages.ERROR_MESSAGE_NAME, Serializer.toJson(new ErrorServerMessage("Il giocatore da te selezionato sta effettuando un altro scambio in questo momento")));
        }

    }
}
