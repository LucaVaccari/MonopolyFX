package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;

public class EndTurnServerMessage implements Message
{
	private final int gameCode;

	public EndTurnServerMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		GameManager gameManager = ConnectionManager.getInstance().getGames().get(gameCode).getGameManager();
		gameManager.nextTurn();
	}
}
