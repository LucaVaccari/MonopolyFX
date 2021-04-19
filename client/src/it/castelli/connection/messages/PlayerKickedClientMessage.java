package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

/**
 * Message that informs about a kicked player (receive only)
 */
public class PlayerKickedClientMessage implements Message
{
    /**
     * The player who got kicked
     */
    private final Player player;

    /**
     * Constructor for PlayerKickedClientMessage (do not use)
     *
     * @param player The player who got kicked
     */
    public PlayerKickedClientMessage(Player player)
    {
        this.player = player;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        if (Game.getPlayer().betterEquals(player))
        {
            connection.send(ClientMessages.LEAVE_GAME_MESSAGE_NAME, Serializer.toJson(new LeaveGameClientMessage(Game.getGameCode())));
            //TODO: return to main menu
        }
        else
            //TODO: show alert
        ;
    }
}
