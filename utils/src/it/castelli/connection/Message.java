package it.castelli.connection;

import java.io.Serializable;

public abstract class Message implements Serializable
{
    public abstract void onReceive(Connection connection);

}
