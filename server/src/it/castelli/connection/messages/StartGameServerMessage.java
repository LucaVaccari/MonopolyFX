package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

public class StartGameServerMessage implements Message
{
	private final int gameCode;

	public StartGameServerMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		gameConnectionManager.startGame();
		gameConnectionManager.sendAll(ServerMessages.GAME_STARTED_MESSAGE_NAME,
		                              Serializer.toJson(new GameStartedServerMessage()));
	}
}
