package it.castelli.connection;

import java.util.ArrayList;

public class GameConnectionManager implements Runnable
{
	private ArrayList<Connection> players;
	private ArrayList<Connection> joiningPlayers;

	private void addPlayer(Connection connection)
	{
	}

	private void removePlayer(Connection connection)
	{
	}

	public ArrayList<Connection> getPlayers()
	{
		return players;
	}

	public ArrayList<Connection> getJoiningPlayers()
	{
		return joiningPlayers;
	}

	@Override
	public void run()
	{

	}
}
