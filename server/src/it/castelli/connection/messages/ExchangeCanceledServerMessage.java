package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;

public class ExchangeCanceledServerMessage implements Message
{
    private final Exchange exchange;

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
