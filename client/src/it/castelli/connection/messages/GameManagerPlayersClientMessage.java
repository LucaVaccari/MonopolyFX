package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Players in the game info message (receive only)
 */
public class GameManagerPlayersClientMessage implements Message
{
	private final CopyOnWriteArrayList<Player> players;

	/**
	 * Constructor for GameManagerPlayersClientMessage
	 * @param players players of the game
	 */
	public GameManagerPlayersClientMessage(CopyOnWriteArrayList<Player> players)
	{
		this.players = players;
	}

	/**
	 * Replace Client players list with Server players list
	 * @param connection connection of the player
	 * @param player player
	 */
	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.getGameManager().clearPlayers();
		for (Player element : players)
			Game.getGameManager().addPlayer(element);
	}
}
