package it.castelli.connection;

import it.castelli.connection.messages.GameManagerPlayersServerMessage;
import it.castelli.connection.messages.ServerMessages;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameConnectionManager
{
	private final CopyOnWriteArrayList<Connection> players = new CopyOnWriteArrayList<>();
	private final GameManager gameManager = new GameManager();



	public void addPlayer(Connection connection, Player player)
	{
		players.add(connection);
		connection.addPlayer(player);
		gameManager.addPlayer(player);
		connection.send(ServerMessages.GAME_MANAGER_PLAYERS_MESSAGE_NAME);
		connection.send(Serializer.toJson(new GameManagerPlayersServerMessage(gameManager.getPlayers())));

	}

	public CopyOnWriteArrayList<Connection> getPlayers()
	{
		return players;
	}

	public void startGame(){
		//TODO: start game
	}



}
