package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.serialization.Serializer;

/**
 * Message from the client that refuses the exchange offer (receive only)
 */
public class RefuseExchangeServerMessage implements Message
{
	/**
	 * The exchange to refuse
	 */
	private final Exchange exchange;

	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for RefuseExchangeServerMessage (do not use)
	 *
	 * @param exchange The exchange to refuse
	 * @param gameCode The game code
	 */
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

		gameConnectionManager.getConnectionFromPlayer(exchange.getPlayer2())
				.send(ServerMessages.EXCHANGE_CANCELED_MESSAGE_NAME, Serializer
						.toJson(new ExchangeCanceledServerMessage(exchange)));

		gameConnectionManager.getConnectionFromPlayer(exchange.getPlayer1())
				.send(ServerMessages.EXCHANGE_CANCELED_MESSAGE_NAME, Serializer
						.toJson(new ExchangeCanceledServerMessage(exchange)));

		gameManager.removeExchange(exchange);
	}
}
