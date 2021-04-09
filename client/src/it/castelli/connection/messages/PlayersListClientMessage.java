package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Override the list of players
 */
public class PlayersListClientMessage implements Message
{
	private final CopyOnWriteArrayList<Player> players;

	public PlayersListClientMessage(CopyOnWriteArrayList<Player> players)
	{
		this.players = players;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{

		Game.getGameManager().clearPlayers();
		for (Player element : players)
			Game.getGameManager().addPlayer(element);

		// TODO: refresh the list of players
	}
}
