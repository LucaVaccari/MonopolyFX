package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.serialization.Serializer;

public class RefuseExchangeServerMessage implements Message
{
	private final Exchange exchange;
	private final int gameCode;

	public RefuseExchangeServerMessage(Exchange exchange, int gameCode)
	{
		this.exchange = exchange;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		GameManager gameManager = gameConnectionManager.getGameManager();

		Connection connection1;
		if (exchange.getPlayer1().equals(player))
			connection1 = gameConnectionManager.getConnectionFromPlayer(exchange.getPlayer2());
		else
			connection1 = gameConnectionManager.getConnectionFromPlayer(exchange.getPlayer1());

		connection1.send(ServerMessages.EXCHANGE_CANCELED_MESSAGE_NAME, Serializer.toJson(new ExchangeCanceledServerMessage(exchange)));

		gameManager.removeExchange(exchange);
	}
}
