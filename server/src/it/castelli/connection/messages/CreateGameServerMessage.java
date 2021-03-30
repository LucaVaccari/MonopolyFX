package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

public class CreateGameServerMessage implements Message
{
	@Override
	public void onReceive(Connection connection, Player player)
	{
		int gameCode = ConnectionManager.getInstance().createGame();
		ConnectionManager.getInstance().joinGame(gameCode, connection, player);
		// Sending game code to the client
		connection.send(ServerMessages.GAME_CODE_MESSAGE_NAME,
		                Serializer.toJson(new GameCodeServerMessage(gameCode)));
	}
}
