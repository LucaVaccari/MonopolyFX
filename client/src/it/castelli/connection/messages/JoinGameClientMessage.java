package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Join game request message (send only)
 */
public class JoinGameClientMessage implements Message
{
	private final int gameCode;
	private final Player player;

	/**
	 * Constructor of JoinGameClientMessage
	 *
	 * @param gameCode game code
	 * @param name player's name inside the game
	 */
	public JoinGameClientMessage(int gameCode, String name)
	{
		this.gameCode = gameCode;
		player = new Player(1500, name);
		Game.setPlayer(player);
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
