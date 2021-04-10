package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to join a game with the given game code (send only)
 */
public class JoinGameClientMessage implements Message
{
	/**
	 * The game code of the game to join
	 */
	private final int gameCode;

	/**
	 * The player that wants to join
	 */
	private final Player player;

	/**
	 * Constructor for JoinGameClientMessage
	 *
	 * @param gameCode The game code of the game to join
	 * @param name player's name inside the game he's joining
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
