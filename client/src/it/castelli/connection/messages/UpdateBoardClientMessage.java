package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.squares.Square;

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
	 * Constructor for UpdateBoardClientMessage
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
	}
}
