package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Asset;
import it.castelli.gameLogic.transactions.Exchange;

public class ChangeExchangeAssetClientMessage implements Message
{
    private final Asset asset;
    private final int gameCode;
    private final Exchange exchange;
    private final Player player;

    public ChangeExchangeAssetClientMessage(Asset asset, int gameCode, Exchange exchange, Player player)
    {
        this.asset = asset;
        this.gameCode = gameCode;
        this.exchange = exchange;
        this.player = player;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
