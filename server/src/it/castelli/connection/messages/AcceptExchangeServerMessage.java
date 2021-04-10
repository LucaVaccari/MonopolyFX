package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.serialization.Serializer;

/**
 * Message from the client that accepts the exchange (receive only)
 */
public class AcceptExchangeServerMessage implements Message
{
	/**
	 * The player
	 */
	private final Player player;

	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for AcceptExchangeServerMessage
	 *
	 * @param player   The player accepting the exchange
	 * @param gameCode The game code
	 */
	public AcceptExchangeServerMessage(Player player, int gameCode)
	{
		this.player = player;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		GameManager gameManager = gameConnectionManager.getGameManager();

		Exchange exchange = gameManager.getExchangeFromPlayer(this.player);
		exchange.acceptExchange(this.player);
		if (exchange.getAccepted1() && exchange.getAccepted2())
		{
			exchange.endExchange();
			gameConnectionManager.sendAll(ServerMessages.EXCHANGE_SUCCESSFUL_MESSAGE_NAME, Serializer.toJson(new ExchangeSuccessfulServerMessage(exchange)));
		} else
		{
			gameConnectionManager.sendAll(ServerMessages.UPDATE_EXCHANGE_MESSAGE_NAME, Serializer.toJson(new UpdateExchangeServerMessage(exchange)));
		}

		gameConnectionManager.updatePlayers();
		gameManager.removeExchange(exchange);
	}
}
