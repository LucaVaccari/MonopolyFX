package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.controllers.BoardController;
import it.castelli.gui.controllers.LobbyController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

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
			Game.getGameManager().addPlayer(element);

		// TODO: refresh the list of players
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				System.out.println("refresh list");
				for (Player element : players)
					System.out.println(element.getName());
				for (Player element : players)
				{
					Label playerLabel = new Label(element.getName() + " " + element.getMoney() + "M");
					playerLabel.setAlignment(Pos.CENTER);
					playerLabel.setPrefSize(BoardController.getInstance().getPlayerListView().getPrefWidth(), BoardController.getInstance().getPlayerListView().getPrefHeight() / 7);
					playerLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
					LobbyController.getInstance().getPlayerListView().getChildren().add(playerLabel);
					BoardController.getInstance().getPlayerListView().getChildren().add(playerLabel);
				}
			}
		});

	}
}
