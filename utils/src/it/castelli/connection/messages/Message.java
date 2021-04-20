package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

import java.io.Serializable;

/**
 * Interface for the messages classes
 */
public interface Message extends Serializable
{
	public void onReceive(Connection connection, Player player);
}
