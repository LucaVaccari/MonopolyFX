package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.squares.Square;

public class BoardClientMessage implements Message
{
	private final Square[] board;

	public BoardClientMessage(Square[] board)
	{
		this.board = board;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.getGameManager().setBoard(board);
	}
}
