package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.PawnClientMessage;
import it.castelli.connection.messages.StartGameClientMessage;
import it.castelli.gameLogic.Player;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.customComponents.ChatComponent;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Controller for lobby FXML
 */
public class LobbyController
{
	/**
	 * Singleton instance
	 */
	private static LobbyController instance;

	private String pawn;

	@FXML
	private Button playButton;
	@FXML
	private ChatComponent chat;
	@FXML
	private VBox playerListView;
	@FXML
	private Button chooseButton;
	@FXML
	private ImageView thimblePawn;
	@FXML
	private ImageView dogPawn;
	@FXML
	private ImageView wagonPawn;
	@FXML
	private ImageView carPawn;
	@FXML
	private ImageView shoePawn;
	@FXML
	private ImageView boatPawn;

	public static LobbyController getInstance()
	{
		return instance;
	}


	@FXML
	private void initialize()
	{
		instance = this;
		//see the player list view
		for (int i = 0; i < Game.getGameManager().getPlayers().size(); i++)
		{
			Label player = new Label(Game.getGameManager().getPlayers().get(i).getName() + " " +
			                         Game.getGameManager().getPlayers().get(i).getMoney() + "M");
			player.setAlignment(Pos.CENTER);
			player.setPrefSize(playerListView.getPrefWidth(), playerListView.getPrefHeight() / 7);
			player.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			playerListView.getChildren().add(player);
		}

		playButton.setVisible(false);
		playButton.setDisable(true);

		playButton.setOnAction(
				event -> {
					ClientMain.getConnection().send(ClientMessages.START_GAME_MESSAGE_NAME,
					                                Serializer.toJson(new StartGameClientMessage(Game.getGameCode())));
				});

		chooseButton.setOnAction(
				event -> {
					boolean pawnAvailable = true;
					for (Player player : Game.getGameManager().getPlayers())
					{
						if (player.getPawn().equals(pawn))
						{
							pawnAvailable = false;
							break;
						}
					}
					if (pawnAvailable)
						ClientMain.getConnection().send(ClientMessages.PAWN_MESSAGE_NAME, Serializer
								.toJson(new PawnClientMessage(pawn, Game.getGameCode())));
					else
						AlertUtil.showInformationAlert("Pedina", "Pedina già selezionata",
						                               "Non è possibile selezionare la pedina scelta in quanto è già" +
						                               " stata selezionata da un altro giocatore.");
				});
		thimblePawn.setOnMouseClicked(event -> pawn = "thimble.png");
		dogPawn.setOnMouseClicked(event -> {pawn = "dog.png";});
		wagonPawn.setOnMouseClicked(event -> pawn = "wagon.png");
		carPawn.setOnMouseClicked(event -> pawn = "car.png");
		shoePawn.setOnMouseClicked(event -> pawn = "shoe.png");
		boatPawn.setOnMouseClicked(event -> pawn = "boat.png");
	}

	public void showPlayButton()
	{
		playButton.setDisable(false);
		playButton.setVisible(true);
	}

	public VBox getPlayerListView()
	{
		return playerListView;
	}
}
