package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.gui.controllers.ExchangeController;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;

/**
 * Message received from the server that starts a new exchange if the player is involved (receive only)
 */
public class NewExchangeClientMessage implements Message
{
    /**
     * The new exchange
     */
    private final Exchange exchange;

    /**
     * Constructor for NewExchangeClientMessage (do not use)
     *
     * @param exchange The new exchange
     */
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
            SceneManager.getInstance().showScene(SceneType.EXCHANGE);
            ExchangeController.getInstance().setExchange(exchange);
        }
    }
}
