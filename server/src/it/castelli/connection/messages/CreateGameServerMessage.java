package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

public class CreateGameServerMessage implements Message
{
	private final Player player;

	public CreateGameServerMessage(String playerName)
	{
		this.player = new Player(1500, playerName);
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		int gameCode = ConnectionManager.getInstance().createGame();
		ConnectionManager.getInstance().joinGame(gameCode, connection, this.player);
	}
}
