package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.controllers.BoardController;
import it.castelli.gui.controllers.LobbyController;
import it.castelli.gui.scene.SceneManager;
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
		for (Player element : players){
			System.out.println(element.getName());
			Game.getGameManager().addPlayer(element);
		}

		// TODO: refresh the list of players
		Platform.runLater(()->
			{
				BoardController.getInstance().getPlayerListView().getChildren().clear();
				LobbyController.getInstance().getPlayerListView().getChildren().clear();
				System.out.println("refresh list porco3");
				for (Player element : players)
					System.out.println(element.getName());
				for (Player element : players)
				{
					Label playerLabel = new Label(element.getName() + " " + element.getMoney() + "Morco3");
					playerLabel.setAlignment(Pos.CENTER);
					playerLabel.setPrefSize(LobbyController.getInstance().getPlayerListView().getPrefWidth(), LobbyController.getInstance().getPlayerListView().getPrefHeight() / 7);
					playerLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
					// per luca: puoi capire perchè non me lo fa fare in entrambe le finestre perfavore?
					// una mia soluzione è vedere il client in che scena è,ma non so dove prendere questa informazione per l'if
					if(LobbyController.getInstance() != null)
						LobbyController.getInstance().getPlayerListView().getChildren().add(playerLabel);
					if(BoardController.getInstance() != null)
						BoardController.getInstance().getPlayerListView().getChildren().add(playerLabel);
				}
			});

	}
}
