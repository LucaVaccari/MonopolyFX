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
	 * Does the player accept the offer?
	 */
	private final boolean accept;

	/**
	 * The player
	 */
	private final Player player;

	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for AcceptExchangeServerMessage (do not use)
	 *
	 * @param accept Does the player accept the offer?
	 * @param player The player accepting the exchange
	 * @param gameCode The game code
	 */
	public AcceptExchangeServerMessage(boolean accept, Player player, int gameCode)
	{
		this.accept = accept;
		this.player = player;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		GameManager gameManager = gameConnectionManager.getGameManager();

		Player acceptingPlayer = gameManager.getSamePlayer(this.player);
		Exchange exchange = gameManager.getExchangeFromPlayer(acceptingPlayer);
		Player player1 = gameManager.getSamePlayer(exchange.getPlayer1());
		Player player2 = gameManager.getSamePlayer(exchange.getPlayer2());

		Connection player1Connection = gameConnectionManager.getConnectionFromPlayer(player1);
		Connection player2Connection = gameConnectionManager.getConnectionFromPlayer(player2);

		if (accept)
			exchange.acceptExchange(acceptingPlayer);
		else
			exchange.undoAcceptExchange(acceptingPlayer);

		if (exchange.getAccepted1() && exchange.getAccepted2())
		{
			exchange.endExchange(player1, player2, gameManager);
			player1Connection.send(ServerMessages.EXCHANGE_SUCCESSFUL_MESSAGE_NAME, Serializer.toJson(new ExchangeSuccessfulServerMessage(exchange)));
			player2Connection.send(ServerMessages.EXCHANGE_SUCCESSFUL_MESSAGE_NAME, Serializer.toJson(new ExchangeSuccessfulServerMessage(exchange)));
			gameManager.removeExchange(exchange);
		}
		else
		{
			player1Connection.send(ServerMessages.UPDATE_EXCHANGE_MESSAGE_NAME, Serializer.toJson(new UpdateExchangeServerMessage(exchange)));
			player2Connection.send(ServerMessages.UPDATE_EXCHANGE_MESSAGE_NAME, Serializer.toJson(new UpdateExchangeServerMessage(exchange)));
		}

		gameConnectionManager.updatePlayers();
	}
}
