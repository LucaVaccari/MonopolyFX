package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.Round;

public class RoundServerMessage implements Message
{
    private final Round round;

    public RoundServerMessage(Round round)
    {
        this.round = round;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
