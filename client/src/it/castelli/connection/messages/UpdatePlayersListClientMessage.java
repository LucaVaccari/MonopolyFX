package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.controllers.*;
import javafx.application.Platform;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Message received from the server that updates the player list on the client
 */
public class UpdatePlayersListClientMessage implements Message
{
	/**
	 * The updated player list
	 */
	private final CopyOnWriteArrayList<Player> players;

	/**
	 * Constructor for UpdatePlayersListClientMessage (do not use)
	 *
	 * @param players The updated player list
	 */
	public UpdatePlayersListClientMessage(CopyOnWriteArrayList<Player> players)
	{
		this.players = players;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.getGameManager().clearPlayers();
		for (Player element : players)
		{
			Game.getGameManager().addPlayer(element);
			if (element.betterEquals(Game.getPlayer()))
				Game.setPlayer(element);
		}

		Platform.runLater(() -> {
			LobbyController.getInstance().update();
			BoardController.getInstance().update();

			if (PropertyViewController.getInstance() != null)
				PropertyViewController.getInstance().update();
			if (StationViewController.getInstance() != null)
				StationViewController.getInstance().update();
			if (CompanyViewController.getInstance() != null)
				CompanyViewController.getInstance().update();
		});
	}
}
