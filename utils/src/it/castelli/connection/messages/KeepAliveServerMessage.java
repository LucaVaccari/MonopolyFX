package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.Message;

public class KeepAliveServerMessage extends Message
{
    @Override
    public void onReceive(Connection connection)
    {
        connection.setKeepAliveFlag(true);
    }
}
