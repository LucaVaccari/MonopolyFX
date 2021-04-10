package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.squares.Square;

public class UpdateBoardServerMessage implements Message
{
	private Square[] board;

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
