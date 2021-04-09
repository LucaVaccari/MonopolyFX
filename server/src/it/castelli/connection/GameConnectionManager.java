package it.castelli.connection;

import it.castelli.connection.messages.*;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Auction;
import it.castelli.serialization.Serializer;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameConnectionManager
{
	private final CopyOnWriteArrayList<Connection> playerConnections;
	private final GameManager gameManager;
	private final int gameCode;

	public GameConnectionManager(int gameCode)
	{
		this.gameCode = gameCode;
		this.gameManager = new GameManager();
		this.playerConnections = new CopyOnWriteArrayList<>();
	}

	public void addPlayer(Connection connection, Player player)
	{
		if (playerConnections.size() < 6)  //TODO: check gameManager inGame too
		{
			//TODO: host system
			playerConnections.add(connection);
			connection.addPlayer(player);
			gameManager.addPlayer(player);
			connection.send(ServerMessages.GAME_MANAGER_PLAYERS_MESSAGE_NAME,
			                Serializer.toJson(new GameManagerPlayersServerMessage(gameManager.getPlayers())));
		}
		else
		{
			connection.send(ServerMessages.ERROR_MESSAGE_NAME, Serializer.toJson(new ErrorServerMessage(
					"You can't enter this game, lobby is full or the game has already started")));
		}
	}

	public void removePlayer(Connection connection)
	{
		playerConnections.remove(connection);

		//TODO: check for host leaving problem
		if (playerConnections.isEmpty())
			ConnectionManager.getInstance().removeGame(gameCode);
	}

	public CopyOnWriteArrayList<Connection> getPlayerConnections()
	{
		return playerConnections;
	}

	public void startGame()
	{
		//TODO: start game
		gameManager.startGame();
	}

	private void sendAuction()
	{
		for (Connection connection : playerConnections)
		{
			Auction auction = gameManager.getAuction();
			AuctionServerMessage message = new AuctionServerMessage(auction.getContract(), auction.getPlayer(),
			                                                        auction.getBestOfferProposed());
			connection.send(ServerMessages.AUCTION_MESSAGE_NAME, Serializer.toJson(message));
		}
	}

	public GameManager getGameManager()
	{
		return gameManager;
	}

	public void sendAll(String messageName, String message)
	{
		for (Connection element : playerConnections)
		{
			element.send(messageName, message);
		}
	}
}
