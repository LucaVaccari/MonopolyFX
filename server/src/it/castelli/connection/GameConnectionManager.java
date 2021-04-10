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
	private Connection host = null;

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
			if (this.host == null)
			{
				this.host = connection;
				host.send(ServerMessages.HOST_MESSAGE_NAME, Serializer.toJson(new HostServerMessage()));
			}
			playerConnections.add(connection);
			connection.addPlayer(player);
			gameManager.addPlayer(player);
			connection.send(ServerMessages.PLAYERS_LIST_MESSAGE_NAME,
			                Serializer.toJson(new PlayersListServerMessage(gameManager.getPlayers())));
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

		if (playerConnections.isEmpty())
		{
			ConnectionManager.getInstance().removeGame(gameCode);
			System.out.println("Removed game with code " + gameCode);
		}
		else
		{
			System.out.println("Still " + playerConnections.size() + " players in the game with code " + gameCode);
			if (connection == host)
			{
				host = playerConnections.get(0);
				host.send(ServerMessages.HOST_MESSAGE_NAME, Serializer.toJson(new HostServerMessage()));
			}

		}
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
			element.send(messageName, message);
	}
}
