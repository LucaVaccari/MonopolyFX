package it.castelli.connection;

import it.castelli.connection.messages.ErrorServerMessage;
import it.castelli.connection.messages.GameCodeServerMessage;
import it.castelli.connection.messages.ServerMessages;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionManager
{
	public static final int SERVER_PORT = 1111;
	private static ConnectionManager instance;
	private final CopyOnWriteArrayList<Connection> waitingRoom;
	private final Hashtable<Integer, GameConnectionManager> games;
	private int lastGameCode = 0;
	private Thread connectionReceiverThread;

	private ConnectionManager()
	{
		waitingRoom = new CopyOnWriteArrayList<>();
		games = new Hashtable<>();
	}

	public static ConnectionManager getInstance()
	{
		if (instance == null)
			instance = new ConnectionManager();
		return instance;
	}

	public void start()
	{
		System.out.println(
				"ConnectionManager is working on port: " + SERVER_PORT);
		connectionReceiverThread = new Thread(new ConnectionReceiver());
		connectionReceiverThread.start();
	}

	public void interrupt()
	{
		connectionReceiverThread.interrupt();
	}

	public void addToWaitingRoom(Connection connection)
	{
		System.out.println("Connection added to the waiting room");
		waitingRoom.add(connection);
		System.out.println("The total number of connection in the waiting is: " + waitingRoom.size());
	}

	private void addGame(int gameCode, GameConnectionManager game)
	{
		games.put(gameCode, game);
	}

	public void removeGame(int gameCode)
	{
		games.remove(gameCode);
		System.out.println("Game with code " + gameCode + " removed.");
	}

	public int createGame()
	{
		int gameCode = lastGameCode++;
		addGame(gameCode, new GameConnectionManager(gameCode));
		return gameCode;
	}

	public void joinGame(int gameCode, Connection connection, Player player)
	{
		if (games.containsKey(gameCode))
		{
			games.get(gameCode).addPlayer(connection, player);
			waitingRoom.remove(connection);

			// Sending game code to the client
			connection.send(ServerMessages.GAME_CODE_MESSAGE_NAME,
					Serializer.toJson(new GameCodeServerMessage(code)));
		}
		else
		{
			connection.send(ServerMessages.ERROR_MESSAGE_NAME, Serializer
					.toJson(new ErrorServerMessage(
							"La partita con codice: " + gameCode +
							" non è stata trovata!")));
		}
	}

	public void leaveGame(int gameCode, Connection connection)
	{
		if (games.containsKey(gameCode))
		{
			games.get(gameCode).removePlayer(connection);
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
