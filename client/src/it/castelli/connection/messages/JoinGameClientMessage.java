package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class JoinGameClientMessage implements Message
{
	private final int code;
	private final Player player;

	public JoinGameClientMessage(int code, String name)
	{
		this.code = code;
		player = new Player(1500, name);
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
