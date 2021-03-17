package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.serialization.Serializer;

public class KeepAliveClientMessage implements Message
{
    @Override
    public void onReceive(Connection connection)
    {
        String message = Serializer.toJson(new KeepAliveClientMessage());
        String classToBuild = "it.castelli.connection.messages.KeepAliveServerMessage";
        System.out.println("KeepAlive reply sent to server");
        connection.send(classToBuild);
        connection.send(message);
    }
}
