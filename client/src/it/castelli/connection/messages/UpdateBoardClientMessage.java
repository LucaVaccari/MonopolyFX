package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.squares.Square;
import it.castelli.gui.controllers.BoardController;
import it.castelli.gui.controllers.CompanyViewController;
import it.castelli.gui.controllers.PropertyViewController;
import it.castelli.gui.controllers.StationViewController;
import javafx.application.Platform;

/**
 * Message received from the server that updates the board (receive only)
 */
public class UpdateBoardClientMessage implements Message
{
	/**
	 * The board
	 */
	private final Square[] board;

	/**
	 * Constructor for UpdateBoardClientMessage (do not use)
	 *
	 * @param board The board
	 */
	public UpdateBoardClientMessage(Square[] board)
	{
		this.board = board;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.getGameManager().setBoard(board);
		Platform.runLater(() -> {
			BoardController.getInstance().update();

			if (PropertyViewController.getInstance() != null)
				PropertyViewController.getInstance().update();
			if (StationViewController.getInstance() != null)
				StationViewController.getInstance().update();
			if (CompanyViewController.getInstance() != null)
				CompanyViewController.getInstance().update();
		});
	}
}
