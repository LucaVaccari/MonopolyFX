package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.Round;

public class RoundClientMessage implements Message
{
    private final Round round;

    public RoundClientMessage(Round round)
    {
        this.round = round;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        Game.getGameManager().setCurrentRound(round);
    }
}
