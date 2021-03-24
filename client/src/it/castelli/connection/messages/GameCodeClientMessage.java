package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class GameCodeClientMessage implements Message
{
	private int code;

	public GameCodeClientMessage(int code)
	{
		this.code = code;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.setGameCode(code);
	}
}
