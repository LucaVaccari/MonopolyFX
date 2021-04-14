package it.castelli.connection;

import it.castelli.connection.messages.*;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.squares.CompanySquare;
import it.castelli.gameLogic.squares.PropertySquare;
import it.castelli.gameLogic.squares.Square;
import it.castelli.gameLogic.squares.StationSquare;
import it.castelli.gameLogic.transactions.Auction;
import it.castelli.serialization.Serializer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameConnectionManager
{
	private final CopyOnWriteArrayList<Connection> playerConnections;
	private final GameManager gameManager;
	private final int gameCode;
	private Connection host = null;
	private Timer auctionTimer;

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
			connection.setPlayer(player);

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
			connection.send(ServerMessages.GENERIC_MESSAGE_NAME, Serializer.toJson(new GenericServerMessage(
					"Errore", "Non potete unirvi alla partita, la lobby è piena oppure la partita è già iniziata")));
		}
	}

	public void removePlayer(Connection connection)
	{

		Player playerToRemove = gameManager.getSamePlayer(connection.getReceiver().getPlayer());
		Player currentRoundPlayer = gameManager.getSamePlayer(gameManager.getCurrentRound().getCurrentActivePlayer());
		if (currentRoundPlayer.betterEquals(playerToRemove))
			gameManager.nextRound();

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

		updatePlayers();
	}

	public CopyOnWriteArrayList<Connection> getPlayerConnections()
	{
		return playerConnections;
	}

	public void startGame()
	{
		gameManager.startGame();
		sendAll(ServerMessages.UPDATE_ROUND_MESSAGE_NAME, Serializer
				.toJson(new UpdateRoundServerMessage(gameManager.getCurrentRound())));
		sendAll(ServerMessages.GAME_STARTED_MESSAGE_NAME, Serializer.toJson(new GameStartedServerMessage()));
		updatePlayers();
	}

	public void startAuction(Contract contract, int offer)
	{
		gameManager.startAuction(contract, offer);
		auctionTimer = new Timer();
		TimerTask task = new TimerTask()
		{
			public void run()
			{
				System.out.println("Auction timer finished");
				gameManager.getAuction().endAuction();
				auctionTimer.cancel();

				sendAll(ServerMessages.AUCTION_ENDED_MESSAGE_NAME, Serializer.toJson(new AuctionEndedServerMessage()));
			}
		};

		long delay = 20000L;
		auctionTimer.schedule(task, delay);

		for (Connection connection : playerConnections)
		{
			Auction auction = gameManager.getAuction();
			NewAuctionServerMessage message = new NewAuctionServerMessage(auction.getContract(), auction.getPlayer(),
					auction.getBestOfferProposed());
			connection.send(ServerMessages.NEW_AUCTION_MESSAGE_NAME, Serializer.toJson(message));
		}
	}

	public void auctionOffer(Player player, int offer)
	{
		if (gameManager.getAuction().getBestOfferProposed() < offer)
		{
			auctionTimer.cancel();
			gameManager.getAuction().setBestOfferProposed(offer);
			gameManager.getAuction().setPlayer(player);
			startAuction(gameManager.getAuction().getContract(), offer);
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
		for (Player player : gameManager.getPlayers())
		{
			if (player.getRandomEventType() != null)
			{
				sendAll(ServerMessages.RANDOM_EVENT_MESSAGE_NAME, Serializer
						.toJson(new RandomEventServerMessage(player.getRandomEventType(), player
								.getRandomEventDescription())));
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

		sendAll(ServerMessages.UPDATE_BOARD_MESSAGE_NAME, Serializer
				.toJson(new UpdateBoardServerMessage(gameManager.getBoard())));
		sendAll(ServerMessages.UPDATE_ROUND_MESSAGE_NAME, Serializer
				.toJson(new UpdateRoundServerMessage(gameManager.getCurrentRound())));

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
				getConnectionFromPlayer(player).send(ServerMessages.CONTRACT_ON_SALE_MESSAGE_NAME, Serializer
						.toJson(new ContractOnSaleServerMessage(square.getContract())));
			else
			{
				int playerMoneyBeforeInteract = player.getMoney();
				square.interact(player, gameManager);
				int playerMoneyAfterInteract = player.getMoney();
				int moneyPaid = playerMoneyBeforeInteract - playerMoneyAfterInteract;
				if (moneyPaid != 0)
					sendAll(ServerMessages.PLAYER_PAID_MESSAGE_NAME, Serializer
							.toJson(new PlayerPaidServerMessage(player.getName(), square.getContract().getOwner()
									.getName(), square.getContract().getName(), moneyPaid, !square.getContract()
									.isMortgaged())));
				else
				{
					if (square.getContract().isMortgaged() &&
							!gameManager.getSamePlayer(square.getContract().getOwner().toPlayer()).betterEquals(player))
					{
						sendAll(ServerMessages.GENERIC_MESSAGE_NAME, Serializer.toJson(new GenericServerMessage(
								"Operazione negata", "Il terreno è ipotecato, " + player.getName() + " non deve pagare l'affitto!")));
					}
				}
			}

			player.setPreviousPosition(player.getPosition());
		}
		else
		{
			square.interact(player, gameManager);
		}
	}

	public void resetPlayerProperties(Connection connection)
	{
		for (Connection element : playerConnections)
		{
			if (element.equals(connection))
			{
				Player playerWhoLeft = gameManager.getSamePlayer(connection.getReceiver().getPlayer());
				for (Contract el : playerWhoLeft.getContracts())
				{
					Contract contract = gameManager.getSameContract(el);
					contract.setOwner(null);
				}

				updatePlayers();
			}
		}
	}
}
