package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.gui.AlertUtil;

public class ExchangeCanceledClientMessage implements Message
{
    private final Exchange exchange;

    public ExchangeCanceledClientMessage(Exchange exchange)
    {
        this.exchange = exchange;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        if (exchange.getPlayer1().equals(Game.getPlayer()) || exchange.getPlayer2().equals(Game.getPlayer()))
        {
            Game.getGameManager().removeExchange(exchange);
            AlertUtil.showInformationAlert("Fallimento", "Scambio rifiutato",
                    "Lo scambio è stato rifiutato.");
            // TODO: close the exchange window
        }
    }
}
