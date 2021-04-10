package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;

/**
 * Message to close the window of the canceled exchange (send only)
 */
public class ExchangeCanceledServerMessage implements Message
{
    /**
     * The exchange that got canceled
     */
    private final Exchange exchange;

    /**
     * Constructor for ExchangeCanceledServerMessage
     *
     * @param exchange The exchange that got canceled
     */
    public ExchangeCanceledServerMessage(Exchange exchange)
    {
        this.exchange = exchange;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //do nothing
    }
}
