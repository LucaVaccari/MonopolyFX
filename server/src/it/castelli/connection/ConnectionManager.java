package it.castelli.connection;

import java.util.ArrayList;
import java.util.HashMap;

public class ConnectionManager
{
	public static final int SERVER_PORT = 1111;
	private ArrayList<Connection> waitingRoom;
	private HashMap<Integer, GameConnectionManager> games;

	public ConnectionManager()
	{
		waitingRoom = new ArrayList<>();
		games = new HashMap<>();

		ConnectionReceiver connectionReceiver = new ConnectionReceiver();
		Thread connectionReceiverThread = new Thread(connectionReceiver);
		connectionReceiverThread.start();
	}


	public void moveToGame(int code, Connection connection)
	{
	}

	public void moveToWaitingRoom(Connection connection)
	{
	}

	public void addGame(GameConnectionManager game)
	{
	}

	public void removeGame(int code)
	{
	}

	public int createGame()
	{
		return 0;
	}

	public void joinGame(int code, Connection connection)
	{
	}

	public void leaveGame(int code, Connection connection)
	{
	}
}
