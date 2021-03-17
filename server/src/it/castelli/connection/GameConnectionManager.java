package it.castelli.connection;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameConnectionManager
{
	private CopyOnWriteArrayList<Connection> players;
	private CopyOnWriteArrayList<Connection> joiningPlayers;

	private void movePlayer(Connection connection)
	{
	}

	public void addJoiningPlayer(Connection connection)
	{
		joiningPlayers.add(connection);
		// TODO: send NewPlayerMessage
	}

	public CopyOnWriteArrayList<Connection> getPlayers()
	{
		return players;
	}

	public CopyOnWriteArrayList<Connection> getJoiningPlayers()
	{
		return joiningPlayers;
	}

}
