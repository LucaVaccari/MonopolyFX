package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

public class StartGameServerMessage implements Message
{
    private final int gameCode;

    public StartGameServerMessage(int gameCode)
    {
        this.gameCode = gameCode;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        ConnectionManager.getInstance().getGames().get(gameCode).startGame();
    }
}
