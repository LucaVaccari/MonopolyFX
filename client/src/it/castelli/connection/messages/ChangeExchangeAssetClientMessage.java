package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Asset;
import it.castelli.gameLogic.transactions.Exchange;

/**
 * Request to modify the money or the proprieties offered during an exchange (send only)
 */
public class ChangeExchangeAssetClientMessage implements Message
{
    /**
     * The asset containing the money and the contract list to offer
     */
    private final Asset asset;

    /**
     * The game code
     */
    private final int gameCode;

    /**
     * The player
     */
    private final Player player;

    /**
     * Constructor for ChangeExchangeAssetClientMessage
     * @param asset The asset containing the money and the contract list to offer
     * @param gameCode The game code
     * @param player The player
     */
    public ChangeExchangeAssetClientMessage(Asset asset, int gameCode, Exchange exchange, Player player)
    {
        this.asset = asset;
        this.gameCode = gameCode;
        this.player = player;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
