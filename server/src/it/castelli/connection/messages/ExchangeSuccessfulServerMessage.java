package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;

/**
 * Message to close the window of the successfully closed exchange (send only)
 */
public class ExchangeSuccessfulServerMessage implements Message
{
    /**
     * The exchange that closed
     */
    private final Exchange exchange;

    /**
     * Constructor for ExchangeSuccessfulServerMessage
     *
     * @param exchange The exchange that closed
     */
    public ExchangeSuccessfulServerMessage(Exchange exchange)
    {
        this.exchange = exchange;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //do nothing
    }
}
