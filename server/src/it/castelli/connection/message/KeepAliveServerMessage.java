package it.castelli.connection.message;

import it.castelli.connection.Connection;

public class KeepAliveServerMessage implements Message
{
    @Override
    public void onReceive(Connection connection)
    {
        connection.setKeepAliveFlag(true);
    }
}