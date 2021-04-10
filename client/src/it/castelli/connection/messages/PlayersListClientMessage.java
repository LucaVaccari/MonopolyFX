package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.controllers.BoardController;
import it.castelli.gui.controllers.LobbyController;
import javafx.application.Platform;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Override the list of players
 */
public class PlayersListClientMessage implements Message
{
	private final CopyOnWriteArrayList<Player> players;

	public PlayersListClientMessage(CopyOnWriteArrayList<Player> players)
	{
		this.players = players;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.getGameManager().clearPlayers();
		for (Player element : players)
		{
			System.out.println(element.getName());
			Game.getGameManager().addPlayer(element);
		}

		Platform.runLater(() -> LobbyController.getInstance().updatePlayerListView());
		Platform.runLater(() -> BoardController.getInstance().updatePlayerListView());
	}
}
