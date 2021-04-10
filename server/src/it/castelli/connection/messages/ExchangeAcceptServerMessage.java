package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class ExchangeAcceptServerMessage implements Message
{
	private final Player player1;
	private final Player player2;
	private final int gameCode;

	public ExchangeAcceptServerMessage(Player player1, Player player2, int gameCode)
	{
		this.player1 = player1;
		this.player2 = player2;
		this.gameCode = gameCode;
	}


	@Override
	public void onReceive(Connection connection, Player player)
	{

	}
}
