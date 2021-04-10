package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;

/**
 * Message to start a new exchange (send only)
 */
public class NewExchangeServerMessage implements Message
{
    /**
     * The new exchange
     */
    private final Exchange exchange;

    /**
     * Constructor for NewExchangeServerMessage
     *
     * @param exchange The new exchange
     */
    public NewExchangeServerMessage(Exchange exchange)
    {
        this.exchange = exchange;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //do nothing
    }
}
