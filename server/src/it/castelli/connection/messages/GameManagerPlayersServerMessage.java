package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameManagerPlayersServerMessage implements Message
{
    private CopyOnWriteArrayList<Player> players;

    public GameManagerPlayersServerMessage(CopyOnWriteArrayList<Player> players)
    {
        this.players = players;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //do nothing
    }
}
