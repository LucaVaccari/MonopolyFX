package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.Message;
import it.castelli.serialization.Serializer;

public class KeepAliveClientMessage extends Message
{
    @Override
    public void onReceive(Connection connection)
    {
        // TODO: generate a KeepAliveServerMessage and send it
        byte[] answer = Serializer.serialize(new KeepAliveServerMessage());

    }
}
