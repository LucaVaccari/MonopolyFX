package it.castelli.connection;

import it.castelli.connection.messages.*;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.squares.CompanySquare;
import it.castelli.gameLogic.squares.PropertySquare;
import it.castelli.gameLogic.squares.Square;
import it.castelli.gameLogic.squares.StationSquare;
import it.castelli.gameLogic.transactions.Auction;
import it.castelli.serialization.Serializer;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class representing a game. It manage the lobby, the actual game and all the message between players
 */
public class GameConnectionManager
{
	/**
	 * The list of all the connections of the players connected to this game
	 */
	private final CopyOnWriteArrayList<Connection> playerConnections;

	/**
	 * The game manager handling this game
	 */
	private final GameManager gameManager;

	/**
	 * The code of this game
	 */
	private final int gameCode;

	/**
	 * The connection of the host player (the only one able to start the game)
	 */
	private Connection host = null;

	/**
	 * The time after which the auction ends without further offers
	 */
	private final int AUCTION_DURATION = 10;

	/**
	 * The task that handles the auction on its own thread
	 */
	private AuctionTimerTask auctionTask;

	/**
	 * Constructor for GameConnectionManager
	 *
	 * @param gameCode The code of this game
	 */
	public GameConnectionManager(int gameCode)
	{
		this.gameCode = gameCode;
		this.gameManager = new GameManager();
		this.playerConnections = new CopyOnWriteArrayList<>();
	}

	/**
	 * Tries to add a player to the player list in this game. This operation does not work if the game is full (max 6
	 * players) or the game has already started
	 *
	 * @param connection The connection of the player to add
	 * @param player     The player to add
	 */
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
					"Errore",
					"Non potete unirvi alla partita, la lobby e' piena oppure la partita e' gia' iniziata")));
		}
	}

	/**
	 * Removes a player from the game, resetting all his in-game properties
	 *
	 * @param connection the connection of the player to remove
	 */
	public void removePlayer(Connection connection)
	{

		if (gameManager.isInGame())
		{
			resetPlayerProperties(connection);
			Player playerToRemove = gameManager.getSamePlayer(connection.getReceiver().getPlayer());
			System.out.println("Player to remove: " + playerToRemove.getName());
			if (gameManager.getCurrentRound().getCurrentActivePlayer() != null)
			{
				Player currentRoundPlayer = gameManager.getSamePlayer(gameManager.getCurrentRound().getCurrentActivePlayer());
				gameManager.removePlayer(playerToRemove);
				System.out.println("Current round player: " + currentRoundPlayer.getName());
				if (currentRoundPlayer.betterEquals(playerToRemove))
					gameManager.nextRound();
			}
		}
		else
		{
			gameManager.removePlayer(connection.getReceiver().getPlayer());
		}

		playerConnections.remove(connection);

		if (playerConnections.isEmpty())
		{
			ConnectionManager.getInstance().removeGame(gameCode);
		}
		else
		{
			if (connection.equals(host))
			{
				host = playerConnections.get(0);
				host.send(ServerMessages.SET_HOST_MESSAGE_NAME, Serializer.toJson(new SetHostServerMessage()));
			}

			sendAll(ServerMessages.UPDATE_PLAYERS_LIST_MESSAGE_NAME, Serializer
					.toJson(new UpdatePlayersListServerMessage(gameManager.getPlayers())));
		}

		updatePlayers();
	}

	/**
	 * Getter for playerConnections
	 *
	 * @return The list of all the connections of the players connected to this game
	 */
	public CopyOnWriteArrayList<Connection> getPlayerConnections()
	{
		return playerConnections;
	}

	/**
	 * Starts the game
	 */
	public void startGame()
	{
		gameManager.startGame();
		sendAll(ServerMessages.UPDATE_ROUND_MESSAGE_NAME, Serializer
				.toJson(new UpdateRoundServerMessage(gameManager.getCurrentRound())));
		sendAll(ServerMessages.GAME_STARTED_MESSAGE_NAME, Serializer.toJson(new GameStartedServerMessage()));
		updatePlayers();
	}

	/**
	 * Starts the auction Used to actually start the auction and also to reset the timer after a new offer is proposed
	 *
	 * @param contract The contract which is being sold in the auction
	 * @param offer    The best offer proposed until now
	 */
	public void startAuction(Contract contract, int offer)
	{
		gameManager.startAuction(contract, offer);

		if (auctionTask != null)
			auctionTask.interrupt();

		auctionTask = new AuctionTimerTask(AUCTION_DURATION, gameCode);
		auctionTask.init();

		Auction auction = gameManager.getAuction();
		sendAll(ServerMessages.NEW_AUCTION_MESSAGE_NAME,
				Serializer.toJson(new NewAuctionServerMessage(auction.getContract(), auction.getPlayer(),
						auction.getBestOfferProposed())));
	}

	/**
	 * Offer the given amount of money in tha auction. If it's the highest, the given player will be the owner of the
	 * contract at the end of the auction
	 *
	 * @param player The player offering money
	 * @param offer  The offer of the player
	 */
	public void auctionOffer(Player player, int offer)
	{
		if (gameManager.getAuction().getBestOfferProposed() < offer)
		{
			startAuction(gameManager.getAuction().getContract(), offer);
			gameManager.getAuction().setBestOfferProposed(offer);
			gameManager.getAuction().setPlayer(player);
		}
	}

	/**
	 * Getter for gameManager
	 *
	 * @return The game manager handling this game
	 */
	public GameManager getGameManager()
	{
		return gameManager;
	}

	/**
	 * Send the given message to all the players in this game
	 *
	 * @param messageName the name of the message (from either ServerMessages or ClientMessages)
	 * @param message     the string from json conversion of the Message class
	 */
	public void sendAll(String messageName, String message)
	{
		for (Connection element : playerConnections)
			element.send(messageName, message);
	}

	/**
	 * Updates all the connected players about in-game changes and others (such as player leaving the game). This
	 * method
	 * send messages to update the player list, the board, the round class. This method is also responsible for
	 * detecting a player winning the game
	 */
	public void updatePlayers()
	{
		//if (false)
		if (gameManager.isInGame() && gameManager.getPlayers().size() == 1)
		{
			sendAll(ServerMessages.VICTORY_MESSAGE_NAME, Serializer.toJson(new VictoryServerMessage()));
			gameManager.endGame();
			ConnectionManager.getInstance().removeGame(gameCode);
		}
		else
		{
			for (Player player : gameManager.getPlayers())
			{
				if (player.getEventType() != null)
				{
					sendAll(ServerMessages.EVENT_MESSAGE_NAME, Serializer
							.toJson(new EventServerMessage(player.getEventType(), player
									.getEventDescription())));
					player.setLastEncounteredEvent(null, null);
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


	}

	/**
	 * Returns the connection corresponding to the given player
	 *
	 * @param player The player to obtain the connection from
	 * @return The connection corresponding to the given player
	 */
	public Connection getConnectionFromPlayer(Player player)
	{
		for (Connection connection : playerConnections)
		{
			if (connection.getReceiver().getPlayer().betterEquals(player))
			{
				return connection;
			}
		}
		return null;
	}

	/**
	 * Makes the player interact with the square he landed on
	 *
	 * @param player The player interacting with the square
	 */
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
								"Affitto gratuito",
								"Il terreno e' ipotecato, " + player.getName() + " non deve pagare l'affitto!")));
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

	/**
	 * Removes all the properties from the player corresponding to the given connection
	 *
	 * @param connection The connection of the player
	 */
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
					contract.setMortgaged(false);
					if (contract instanceof PropertyContract)
						((PropertyContract) contract).resetHouses();
				}

				updatePlayers();
			}
		}
	}
}
