package it.castelli.connection;

import it.castelli.connection.messages.ErrorServerMessage;
import it.castelli.connection.messages.GameManagerPlayersServerMessage;
import it.castelli.connection.messages.ServerMessages;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.serialization.Serializer;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameConnectionManager
{
	private final CopyOnWriteArrayList<Connection> players;
	private final GameManager gameManager;
	private final int gameCode;
	private boolean inGame = false;

	public GameConnectionManager(int gameCode)
	{
		this.gameCode = gameCode;
		this.gameManager = new GameManager();
		this.players = new CopyOnWriteArrayList<>();
	}

	public void addPlayer(Connection connection, Player player)
	{
		if (players.size() < 6 || !inGame)
		{
			players.add(connection);
			connection.addPlayer(player);
			gameManager.addPlayer(player);
			connection.send(ServerMessages.GAME_MANAGER_PLAYERS_MESSAGE_NAME, Serializer.toJson(new GameManagerPlayersServerMessage(gameManager.getPlayers())));
		}
		else
		{
			connection.send(ServerMessages.ERROR_MESSAGE_NAME, Serializer.toJson(new ErrorServerMessage("You can't enter this game, lobby is full or the game has already started")));
		}
	}

	public void removePlayer(Connection connection)
	{
		players.remove(connection);

		if (players.isEmpty())
			ConnectionManager.getInstance().removeGame(gameCode);
	}

	public CopyOnWriteArrayList<Connection> getPlayers()
	{
		return players;
	}

	public void startGame()
	{
		//TODO: start game
		inGame = true;
	}

	public void startAuction(Contract contract)
	{
		gameManager.startAuction(contract);
		//TODO: send auction to players
	}

	public void offer(Player player, int offer)
	{
		gameManager.auctionOffer(player, offer);
		//TODO: send auction to players
	}


}
