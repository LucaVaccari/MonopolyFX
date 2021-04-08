package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class EndTurnClientMessage implements Message
{
    private final int gameCode;

    public EndTurnClientMessage(int gameCode)
    {
        this.gameCode = gameCode;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
