package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

public class ChatServerMessage implements Message
{
    private final int gameCode;
    private String message;

    public ChatServerMessage(int gameCode, Player senderPlayer, String messageBody)
    {
        this.gameCode = gameCode;
        message = messageBody;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //send the message to all the users
        Message messageObj = new ChatServerMessage(gameCode, player, message);
        ConnectionManager.getInstance().getGames().get(gameCode).sendAll(ServerMessages.CHAT_MESSAGE_NAME, Serializer.toJson(messageObj));
    }
}
