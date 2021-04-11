package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Pawn;
import it.castelli.gameLogic.Player;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Platform;

/**
 * Request to set the player's pawn with the chosen one (send only)
 */
public class ChoosePawnClientMessage implements Message
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
	 * Constructor for ChoosePawnClientMessage
	 *
	 * @param pawn  The pawn URL that represent the image of the pawn
	 * @param gameCode The game code
	 */
	public ChoosePawnClientMessage(Pawn pawn, int gameCode)
	{
		this.pawn = pawn;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
