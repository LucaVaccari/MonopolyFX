package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;

public class CancelExchangeClientMessage implements Message
{
	private final Exchange exchange;
	private final int gameCode;

	public CancelExchangeClientMessage(Exchange exchange, int gameCode)
	{
		this.exchange = exchange;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//TODO: delete the Exchange and show an error if the player was one of the players in the Exchange
	}
}
