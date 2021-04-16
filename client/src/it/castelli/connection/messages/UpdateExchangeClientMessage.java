package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.gui.controllers.ExchangeController;

/**
 * Message received from the server that updates the exchange in which the player is involved (receive only)
 */
public class UpdateExchangeClientMessage implements Message
{
    /**
     * The updated exchange
     */
    private final Exchange exchange;

    /**
     * Constructor for UpdateExchangeClientMessage (do not use)
     *
     * @param exchange The updated exchange
     */
    public UpdateExchangeClientMessage(Exchange exchange)
    {
        this.exchange = exchange;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        Game.getGameManager().updateExchange(exchange);
        ExchangeController.getInstance().setExchange(exchange);
        //TODO: update the accepted image
    }
}
