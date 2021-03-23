package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

public class JoinGameServerMessage implements Message
{
    private int code;
    private Player player;

    @Override
    public void onReceive(Connection connection, Player player)
    {
        ConnectionManager.getInstance().joinGame(code, connection, player);
    }
}
