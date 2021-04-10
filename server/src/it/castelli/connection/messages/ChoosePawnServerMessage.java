package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

/**
 * Message from the client that sets the player's pawn with the chosen one (receive only)
 */
public class ChoosePawnServerMessage implements Message
{
	/**
	 * The pawn URL that represent the image of the pawn
	 */
	private final String pawnURL;

	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for ChoosePawnServerMessage (do not use)
	 *
	 * @param pawnURL The pawn URL that represent the image of the pawn
	 * @param gameCode The game code
	 */
	public ChoosePawnServerMessage(String pawnURL, int gameCode)
	{
		this.pawnURL = pawnURL;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		player.setPawnPath(pawnURL);
		GameConnectionManager manager = ConnectionManager.getInstance().getGames().get(gameCode);
		manager.sendAll(ServerMessages.UPDATE_PLAYERS_LIST_MESSAGE_NAME,
		                Serializer.toJson(new UpdatePlayersListServerMessage(manager.getGameManager().getPlayers())));
	}
}
