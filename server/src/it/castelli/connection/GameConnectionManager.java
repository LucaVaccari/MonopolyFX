package it.castelli.connection;

import it.castelli.connection.messages.*;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.squares.CompanySquare;
import it.castelli.gameLogic.squares.PropertySquare;
import it.castelli.gameLogic.squares.Square;
import it.castelli.gameLogic.squares.StationSquare;
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
		} else
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
		} else
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
		sendAll(ServerMessages.UPDATE_ROUND_MESSAGE_NAME, Serializer.toJson(new UpdateRoundServerMessage(gameManager.getCurrentRound())));
		sendAll(ServerMessages.GAME_STARTED_MESSAGE_NAME, Serializer.toJson(new GameStartedServerMessage()));
		updatePlayers();
	}

	public void startAuction()
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
		System.out.println("Updating players");
		for (Player player : gameManager.getPlayers())
		{
			if (player.getRandomEventType() != null)
			{
            	sendAll(ServerMessages.RANDOM_EVENT_MESSAGE_NAME, Serializer.toJson(new RandomEventServerMessage(player.getRandomEventType(), player.getRandomEventDescription())));
            	player.setLastRandomEvent(null, null);
			}

			if (player.hasSomethingChanged())
			{
				if (player.getPreviousPosition() != player.getPosition())
				{
					interactWithSquare(player);
					updatePlayers();
				}
				player.setSomethingChanged(false);
			}
		}

		sendAll(ServerMessages.UPDATE_PLAYERS_LIST_MESSAGE_NAME, Serializer
				.toJson(new UpdatePlayersListServerMessage(gameManager.getPlayers())));

		sendAll(ServerMessages.UPDATE_BOARD_MESSAGE_NAME, Serializer.toJson(new UpdateBoardServerMessage(gameManager.getBoard())));
		sendAll(ServerMessages.UPDATE_ROUND_MESSAGE_NAME, Serializer.toJson(new UpdateRoundServerMessage(gameManager.getCurrentRound())));

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

	private void interactWithSquare(Player player)
	{
		Square square = gameManager.getSquare(player.getPosition());
		player.setPreviousPosition(player.getPosition());
		if (square instanceof PropertySquare || square instanceof StationSquare || square instanceof CompanySquare)
		{
			if (square.getContract().getOwner() == null)
				getConnectionFromPlayer(player).send(ServerMessages.CONTRACT_ON_SALE_MESSAGE_NAME, Serializer.toJson(new ContractOnSaleServerMessage(square.getContract())));
			else
				square.interact(player, gameManager);

			player.setPreviousPosition(player.getPosition());
		}
		else
		{
			square.interact(player, gameManager);
		}
	}
}
