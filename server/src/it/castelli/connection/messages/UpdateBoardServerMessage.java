package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.squares.Square;

/**
 * Message to update the board (receive only)
 */
public class UpdateBoardServerMessage implements Message
{
	/**
	 * The board
	 */
	private Square[] board;

	/**
	 * Constructor for UpdateBoardServerMessage (do not use)
	 *
	 * @param board The board
	 */
	public UpdateBoardServerMessage(Square[] board)
	{
		this.board = board;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
