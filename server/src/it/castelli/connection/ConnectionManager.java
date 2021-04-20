package it.castelli.connection;

import it.castelli.connection.messages.GenericServerMessage;
import it.castelli.connection.messages.ServerMessages;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The class that handles all the games the server is hosting
 */
public class ConnectionManager
{
	/**
	 * The port the server is running on
	 */
	public static final int SERVER_PORT = 1111;

	/**
	 * The instance of this class
	 */
	private static ConnectionManager instance;

	/**
	 * The group of connections of clients that haven't joined a lobby yet
	 */
	private final CopyOnWriteArrayList<Connection> waitingRoom;

	/**
	 * The group of games the server can handle (max 2^31 games simultaneously)
	 */
	private final Hashtable<Integer, GameConnectionManager> games;

	/**
	 * The code of the last game created
	 */
	private int lastGameCode = 0;

	/**
	 * The thread responsible of accepting client connections
	 */
	private Thread connectionReceiverThread;

	/**
	 * The list of game codes that already finished so can be reused
	 */
	private final LinkedList<Integer> reusedGameCodes;

	/**
	 * Constructors for ConnectionManager (for singleton, private)
	 */
	private ConnectionManager()
	{
		waitingRoom = new CopyOnWriteArrayList<>();
		reusedGameCodes = new LinkedList<>();
		games = new Hashtable<>();
	}

	/**
	 * Getter for ConnectionManager, the only static instance
	 *
	 * @return The connection manager
	 */
	public static ConnectionManager getInstance()
	{
		if (instance == null)
			instance = new ConnectionManager();
		return instance;
	}

	/**
	 * Start the connectionReceiverThread, meaning the server is active
	 */
	public void start()
	{
		System.out.println(
				"ConnectionManager is working on port: " + SERVER_PORT);
		connectionReceiverThread = new Thread(new ConnectionReceiver());
		connectionReceiverThread.start();
	}

	/**
	 * Interrupt all the server activities (unused)
	 */
	public void interrupt()
	{
		connectionReceiverThread.interrupt();
	}

	/**
	 * Add the given connection to the waiting room
	 *
	 * @param connection The connection to add
	 */
	public void addToWaitingRoom(Connection connection)
	{
		waitingRoom.add(connection);
		System.out.println("Connection added to the waiting room, now containing " + waitingRoom.size() + " connections");
	}

	/**
	 * Add the given game with the given gameCode to the list of games
	 *
	 * @param gameCode The game code of the game to add
	 * @param game The game to add
	 */
	private void addGame(int gameCode, GameConnectionManager game)
	{
		games.put(gameCode, game);
	}

	/**
	 * Remove the game associated with the given game code from the list of games and add its game code to the reusedGameCodes list
	 *
	 * @param gameCode The game code of the game to remove
	 */
	public void removeGame(int gameCode)
	{
		games.remove(gameCode);
		reusedGameCodes.add(gameCode);
		System.out.println("Removed game with game code: " + gameCode);
	}

	/**
	 * Create a new game using a code from the reusedGameCodes list or generates a new one
	 *
	 * @return The game code of the new game created
	 */
	public int createGame()
	{
		int gameCode;
		if (reusedGameCodes.isEmpty())
			gameCode = lastGameCode++;
		else
			gameCode = reusedGameCodes.poll();

		addGame(gameCode, new GameConnectionManager(gameCode));
		return gameCode;
	}

	/**
	 * Join the game associated with the given game code.
	 * This operation can end with an error sent back to the client if tha game associated with the given game code is not found
	 *
	 * @param gameCode The game code of the game to join
	 * @param connection The connection of the client who wants to join
	 * @param player The player of the client who wants to join
	 */
	public void joinGame(int gameCode, Connection connection, Player player)
	{
		if (games.containsKey(gameCode))
		{
			games.get(gameCode).addPlayer(connection, player);
			waitingRoom.remove(connection);

		}
		else
		{
			connection.send(ServerMessages.GENERIC_MESSAGE_NAME, Serializer
					.toJson(new GenericServerMessage(
							"Errore", "La partita con codice: " + gameCode +
							          " non e' stata trovata!")));
		}
	}

	/**
	 * Remove the given connection from the game associated with the given game code
	 *
	 * @param gameCode The game code of the game to leave
	 * @param connection The connection of the client that wants to leave
	 */
	public void leaveGame(int gameCode, Connection connection)
	{
		if (games.containsKey(gameCode))
		{
			games.get(gameCode).removePlayer(connection);
			addToWaitingRoom(connection);
		}
	}

	/**
	 * Getter for waitingRoom
	 *
	 * @return The group of connections of clients that haven't joined a lobby yet
	 */
	public CopyOnWriteArrayList<Connection> getWaitingRoom()
	{
		return waitingRoom;
	}

	/**
	 * Getter for games
	 *
	 * @return The group of games the server can handle
	 */
	public Hashtable<Integer, GameConnectionManager> getGames()
	{
		return games;
	}

}
