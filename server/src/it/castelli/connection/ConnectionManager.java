package it.castelli.connection;

import java.util.ArrayList;
import java.util.HashMap;

public class ConnectionManager
{
	public static final int SERVER_PORT = 1111;
	private ArrayList<Connection> waitingRoom;
	private HashMap<Integer, GameConnectionManager> games;
	private static ConnectionManager instance;
	private Thread connectionReceiverThread;

	private ConnectionManager()
	{
		waitingRoom = new ArrayList<>();
		games = new HashMap<>();
	}

	public void start()
	{
		ConnectionReceiver connectionReceiver = new ConnectionReceiver();
		connectionReceiverThread = new Thread(connectionReceiver);
		connectionReceiverThread.start();
	}

	public void interrupt()
	{
		connectionReceiverThread.interrupt();
	}

	public static ConnectionManager getInstance()
	{
		if (instance == null)
			instance = new ConnectionManager();
		return instance;
	}

	// TODO: implement other methods

	public void addToGame(int code, Connection connection)
	{

	}

	public void addToWaitingRoom(Connection connection)
	{
		waitingRoom.add(connection);
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

	public ArrayList<Connection> getWaitingRoom()
	{
		return waitingRoom;
	}

	public HashMap<Integer, GameConnectionManager> getGames()
	{
		return games;
	}

}
