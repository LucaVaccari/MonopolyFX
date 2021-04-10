package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;

/**
 * Message to update the given exchange (send only)
 */
public class UpdateExchangeServerMessage implements Message
{
    /**
     * The updated exchange
     */
    private final Exchange exchange;

    /**
     * Constructor for UpdateExchangeServerMessage
     *
     * @param exchange The updated exchange
     */
    public UpdateExchangeServerMessage(Exchange exchange)
    {
        this.exchange = exchange;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
