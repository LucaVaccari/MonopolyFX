package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

public class PawnServerMessage implements Message
{
    private final String pawnUrl;
    private final int gameCode;

    public PawnServerMessage(String pawnUrl, int gameCode)
    {
        this.pawnUrl = pawnUrl;
        this.gameCode = gameCode;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //TODO: remove comment
        //player.setPawn(pawnUrl);
        GameConnectionManager manager = ConnectionManager.getInstance().getGames().get(gameCode);
        manager.sendAll(ServerMessages.GAME_MANAGER_PLAYERS_MESSAGE_NAME, Serializer.toJson(new GameManagerPlayersServerMessage(manager.getGameManager().getPlayers())));
    }
}
