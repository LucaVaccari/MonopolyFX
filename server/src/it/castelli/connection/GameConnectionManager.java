package it.castelli.connection;

import java.util.ArrayList;

public class GameConnectionManager
{
	private ArrayList<Connection> players;
	private ArrayList<Connection> joiningPlayers;

	private void movePlayer(Connection connection)
	{
	}

	public void addJoiningPlayer(Connection connection)
	{
		joiningPlayers.add(connection);
		// TODO: send NewPlayerMessage
	}

	public ArrayList<Connection> getPlayers()
	{
		return players;
	}

	public ArrayList<Connection> getJoiningPlayers()
	{
		return joiningPlayers;
	}

}
