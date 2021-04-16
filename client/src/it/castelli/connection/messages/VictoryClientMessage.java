package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

/**
 * Message which communicates the player won (receive only)
 */
public class VictoryClientMessage implements Message
{
    @Override
    public void onReceive(Connection connection, Player player)
    {
        //TODO: display win screen
        connection.send(ClientMessages.LEAVE_GAME_MESSAGE_NAME, Serializer.toJson(new LeaveGameClientMessage(Game.getGameCode())));
    }
}
