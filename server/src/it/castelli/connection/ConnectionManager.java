package it.castelli.connection;

import java.util.ArrayList;
import java.util.HashMap;

public class ConnectionManager
{
	private static ConnectionManager instance;
	public static final int SERVER_PORT = 1111;
	private final ArrayList<Connection> waitingRoom;
	private final HashMap<Integer, GameConnectionManager> games;
	private int lastGameCode = 0;
	private Thread connectionReceiverThread;

	private ConnectionManager()
	{
		waitingRoom = new ArrayList<>();
		games = new HashMap<>();
	}

	public void start()
	{
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
		waitingRoom.add(connection);
	}

	public void addGame(int code, GameConnectionManager game)
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
		addGame(code, new GameConnectionManager());

		return code;
	}

	public void joinGame(int code, Connection connection)
	{
		if (games.containsKey(code))
			games.get(code).addJoiningPlayer(connection);
	}

	public void leaveGame(int code, Connection connection)
	{
		if (games.containsKey(code))
		{
			games.get(code).getJoiningPlayers().remove(connection);
			games.get(code).getPlayers().remove(connection);
		}
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
