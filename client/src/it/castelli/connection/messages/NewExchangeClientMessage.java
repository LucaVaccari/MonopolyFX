package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;

public class NewExchangeClientMessage implements Message
{
    private final Exchange exchange;

    public NewExchangeClientMessage(Exchange exchange)
    {
        this.exchange = exchange;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        Game.getGameManager().addExchange(exchange);
        if (exchange.getPlayer1().equals(Game.getPlayer()) || exchange.getPlayer2().equals(Game.getPlayer()))
        {
            //TODO: show the new Exchange
        }
    }
}
