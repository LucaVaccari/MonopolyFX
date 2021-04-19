package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to create a new game (send only)
 */
public class CreateGameClientMessage implements Message
{
	/**
	 * The new player created
	 */
	private final Player player;

	/**
	 * Constructor for CreateGameClientMessage
	 *
	 * @param playerName the new player name
	 */
	public CreateGameClientMessage(String playerName)
	{
		this.player = new Player(playerName);
		Game.setPlayer(player);
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
