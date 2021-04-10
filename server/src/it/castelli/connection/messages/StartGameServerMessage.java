package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

/**
 * Message from the client that starts the game (receive only)
 */
public class StartGameServerMessage implements Message
{
	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for StartGameServerMessage (do not use)
	 *
	 * @param gameCode The game code
	 */
	public StartGameServerMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		gameConnectionManager.startGame();
		gameConnectionManager.sendAll(ServerMessages.GAME_STARTED_MESSAGE_NAME,
				Serializer.toJson(new GameStartedServerMessage()));
	}
}
