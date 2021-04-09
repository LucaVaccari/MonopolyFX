package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class PawnClientMessage implements Message
{
    private final String pawnUrl;
    private final int gameCode;

    public PawnClientMessage(String pawnUrl, int gameCode)
    {
        this.pawnUrl = pawnUrl;
        this.gameCode = gameCode;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
