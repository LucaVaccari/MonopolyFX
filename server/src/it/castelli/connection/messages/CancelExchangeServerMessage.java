package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.serialization.Serializer;

public class CancelExchangeServerMessage implements Message
{
	private final Exchange exchange;
	private final int gameCode;

	public CancelExchangeServerMessage(Exchange exchange, int gameCode)
	{
		this.exchange = exchange;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		GameManager gameManager = gameConnectionManager.getGameManager();

		gameConnectionManager.sendAll(ServerMessages.CANCEL_EXCHANGE_MESSAGE_NAME, Serializer.toJson(new CancelExchangeServerMessage(exchange, gameCode)));

		gameManager.removeExchange(exchange);
	}
}
