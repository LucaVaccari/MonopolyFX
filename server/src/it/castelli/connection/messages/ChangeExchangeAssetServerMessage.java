package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Asset;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.serialization.Serializer;

public class ChangeExchangeAssetServerMessage implements Message
{
	private final Asset asset;
	private final int gameCode;

	private final Player player;

	public ChangeExchangeAssetServerMessage(Asset asset, int gameCode, Player player)
	{
		this.asset = asset;
		this.gameCode = gameCode;
		this.player = player;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		GameManager gameManager = gameConnectionManager.getGameManager();

		Exchange exchange = gameManager.getExchangeFromPlayer(this.player);
		if (!exchange.getAccepted1() && !exchange.getAccepted2())
		{
			exchange.changeAsset(this.player, asset);

			Connection player1Connection = gameConnectionManager.getConnectionFromPlayer(exchange.getPlayer1());
			Connection player2Connection = gameConnectionManager.getConnectionFromPlayer(exchange.getPlayer2());

			player1Connection.send(ServerMessages.UPDATE_EXCHANGE_MESSAGE_NAME, Serializer.toJson(new UpdateExchangeServerMessage(exchange)));
			player2Connection.send(ServerMessages.UPDATE_EXCHANGE_MESSAGE_NAME, Serializer.toJson(new UpdateExchangeServerMessage(exchange)));

		} else
		{
			connection.send(ServerMessages.ERROR_MESSAGE_NAME, Serializer
					.toJson(new ErrorServerMessage("L'altro giocatore ha già accettato, puoi solo accettare o " +
							"rifiutare lo scambio!")));
		}


	}
}
