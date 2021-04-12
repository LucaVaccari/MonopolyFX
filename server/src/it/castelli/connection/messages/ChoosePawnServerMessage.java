package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.Pawn;
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
	private final Pawn pawn;

	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for ChoosePawnServerMessage (do not use)
	 *
	 * @param pawn The pawn URL that represent the image of the pawn
	 * @param gameCode The game code
	 */
	public ChoosePawnServerMessage(Pawn pawn, int gameCode)
	{
		this.pawn = pawn;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		player.setPawn(pawn);
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		gameConnectionManager.sendAll(ServerMessages.UPDATE_PLAYERS_LIST_MESSAGE_NAME,
		                Serializer.toJson(new UpdatePlayersListServerMessage(gameConnectionManager.getGameManager().getPlayers())));
	}

}
