package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameManagerPlayersClientMessage implements Message
{
	private final CopyOnWriteArrayList<Player> players;

	public GameManagerPlayersClientMessage(CopyOnWriteArrayList<Player> players)
	{
		this.players = players;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		for (Player element : players)
			Game.getGameManager().addPlayer(element);
	}
}
