package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;

public class JoinGameServerMessage implements Message
{
	private int gameCode;
	private Player player;

	@Override
	public void onReceive(Connection connection, Player player)
	{
		ConnectionManager.getInstance().joinGame(gameCode, connection, this.player);
	}
}
