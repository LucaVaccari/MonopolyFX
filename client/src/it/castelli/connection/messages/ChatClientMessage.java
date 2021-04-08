package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class ChatClientMessage implements Message
{
    private final int gameCode;
    private String message;

    public ChatClientMessage(int gameCode, Player senderPlayer, String messageBody)
    {
        this.gameCode = gameCode;
        message = senderPlayer.getName() + ": " + messageBody;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //TODO: print the content of the message
    }
}
