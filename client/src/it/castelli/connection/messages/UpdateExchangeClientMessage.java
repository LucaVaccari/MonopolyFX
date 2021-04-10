package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;

public class UpdateExchangeClientMessage implements Message
{
    private final Exchange exchange;

    public UpdateExchangeClientMessage(Exchange exchange)
    {
        this.exchange = exchange;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        Game.getGameManager().updateExchange(exchange);
    }
}
