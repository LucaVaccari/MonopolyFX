package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class CreateGameClientMessage implements Message
{
	private final Player player;

	public CreateGameClientMessage(String playerName)
	{
		this.player = new Player(1500, playerName);
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
