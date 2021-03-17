package it.castelli.connection.messages;

import it.castelli.connection.Connection;

public class KeepAliveClientMessage implements Message
{
    @Override
    public void onReceive(Connection connection)
    {
        // TODO: generate a KeepAliveServerMessage and send it

    }
}
