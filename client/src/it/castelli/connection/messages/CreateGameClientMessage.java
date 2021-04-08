package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * The create game request message (send only)
 */
public class CreateGameClientMessage implements Message
{
	private final Player player;

	public CreateGameClientMessage(String playerName)
	{
		this.player = new Player(1500, playerName);
		Game.setPlayer(player);
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing

	}
}
