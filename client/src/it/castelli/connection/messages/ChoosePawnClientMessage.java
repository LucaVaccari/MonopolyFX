package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Request to set the player's pawn with the chosen one (send only)
 */
public class ChoosePawnClientMessage implements Message
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
	 * Constructor for ChoosePawnClientMessage
	 *
	 * @param pawnURL The pawn URL that represent the image of the pawn
	 * @param gameCode The game code
	 */
	public ChoosePawnClientMessage(String pawnURL, int gameCode)
	{
		this.pawnURL = pawnURL;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
