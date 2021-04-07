package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class StartGameClientMessage implements Message
{
    private final int gameCode;

    public StartGameClientMessage(int gameCode)
    {
        this.gameCode = gameCode;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
