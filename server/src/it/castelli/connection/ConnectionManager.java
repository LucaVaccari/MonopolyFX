package it.castelli.connection;

import it.castelli.gameLogic.Player;

import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionManager
{
	private static ConnectionManager instance;
	public static final int SERVER_PORT = 1111;
	private final CopyOnWriteArrayList<Connection> waitingRoom;
	private final Hashtable<Integer, GameConnectionManager> games;
	private int lastGameCode = 0;
	private Thread connectionReceiverThread;

	private ConnectionManager()
	{
		waitingRoom = new CopyOnWriteArrayList<>();
		games = new Hashtable<>();
	}

	public void start()
	{
		System.out.println("ConnectionManager is working on port: " + SERVER_PORT);
		connectionReceiverThread = new Thread(new ConnectionReceiver());
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

	public void addToWaitingRoom(Connection connection)
	{
		System.out.println("Connection added to the waiting room");
		waitingRoom.add(connection);
	}

	private void addGame(int code, GameConnectionManager game)
	{
		games.put(code, game);
	}

	public void removeGame(int code)
	{
		games.remove(code);
	}

	public int createGame()
	{
		int code = lastGameCode++;
		addGame(code, new GameConnectionManager(code));
		return code;
	}

	public void joinGame(int code, Connection connection, Player player)
	{
		if (games.containsKey(code))
		{
			games.get(code).addPlayer(connection, player);
			waitingRoom.remove(connection);
		}
		else
		{
			//TODO: Send error message
		}
	}

	public void leaveGame(int code, Connection connection)
	{
		if (games.containsKey(code))
		{
			games.get(code).removePlayer(connection);
		}
	}

	public CopyOnWriteArrayList<Connection> getWaitingRoom()
	{
		return waitingRoom;
	}

	public Hashtable<Integer, GameConnectionManager> getGames()
	{
		return games;
	}

}
