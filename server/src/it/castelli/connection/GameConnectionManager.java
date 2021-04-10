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
		if (playerConnections.size() < 6 && !gameManager.isInGame())
		{
			if (this.host == null)
			{
				this.host = connection;
				host.send(ServerMessages.SET_HOST_MESSAGE_NAME, Serializer.toJson(new SetHostServerMessage()));
			}

			//adding the player
			playerConnections.add(connection);
			connection.addPlayer(player);

			// Sending game code to the client
			connection.send(ServerMessages.GAME_CODE_MESSAGE_NAME,
					Serializer.toJson(new GameCodeServerMessage(gameCode)));

			//Updating other players player list
			gameManager.addPlayer(player);
			sendAll(ServerMessages.UPDATE_PLAYERS_LIST_MESSAGE_NAME,
					Serializer.toJson(new UpdatePlayersListServerMessage(gameManager.getPlayers())));
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
		gameManager.removePlayer(connection.getReceiver().getPlayer());

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
				host.send(ServerMessages.SET_HOST_MESSAGE_NAME, Serializer.toJson(new SetHostServerMessage()));
			}

			sendAll(ServerMessages.UPDATE_PLAYERS_LIST_MESSAGE_NAME, Serializer
					.toJson(new UpdatePlayersListServerMessage(gameManager.getPlayers())));
		}
	}

	public CopyOnWriteArrayList<Connection> getPlayerConnections()
	{
		return playerConnections;
	}

	public void startGame()
	{
		gameManager.startGame();
	}

	private void startAuction()
	{
		for (Connection connection : playerConnections)
		{
			Auction auction = gameManager.getAuction();
			NewAuctionServerMessage message = new NewAuctionServerMessage(auction.getContract(), auction.getPlayer(),
					auction.getBestOfferProposed());
			connection.send(ServerMessages.NEW_AUCTION_MESSAGE_NAME, Serializer.toJson(message));
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

	public void updatePlayers()
	{
		sendAll(ServerMessages.UPDATE_PLAYERS_LIST_MESSAGE_NAME, Serializer
				.toJson(new UpdatePlayersListServerMessage(gameManager.getPlayers())));
		sendAll(ServerMessages.UPDATE_BOARD_MESSAGE_NAME, Serializer.toJson(new UpdateBoardServerMessage(gameManager.getBoard())));
	}

	public Connection getConnectionFromPlayer(Player player)
	{
		for (Connection connection : playerConnections)
		{
			if (connection.getReceiver().getPlayer().equals(player))
			{
				return connection;
			}
		}
		return null;
	}


}
