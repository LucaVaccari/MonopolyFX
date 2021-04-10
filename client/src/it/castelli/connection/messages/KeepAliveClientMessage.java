package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

/**
 * Keep Alive message
 */
public class KeepAliveClientMessage implements Message
{

	@Override
	public void onReceive(Connection connection, Player player)
	{
		connection.send(ClientMessages.KEEP_ALIVE_MESSAGE_NAME,
				Serializer.toJson(new KeepAliveClientMessage()));
	}
}
