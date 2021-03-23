package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

public class CreateGameServerMessage implements Message
{
    @Override
    public void onReceive(Connection connection, Player player)
    {
        int gameCode = ConnectionManager.getInstance().createGame();
        ConnectionManager.getInstance().joinGame(gameCode, connection, player);

        //TODO: send to client the reply with code
    }
}
