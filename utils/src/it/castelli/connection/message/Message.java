package it.castelli.connection.message;

import it.castelli.connection.Connection;

import java.io.Serializable;

public interface Message extends Serializable
{
    public void onReceive(Connection connection);
}
