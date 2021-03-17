package it.castelli.connection.message;

import it.castelli.connection.Connection;
import it.castelli.serialization.Serializer;

public class KeepAliveClientMessage implements Message
{
    @Override
    public void onReceive(Connection connection)
    {
        String message = Serializer.toJson(new KeepAliveClientMessage());
        String classToBuild = "it.castelli.connection.messages.KeepAliveServerMessage";
        connection.send(classToBuild);
        connection.send(message);
    }
}
