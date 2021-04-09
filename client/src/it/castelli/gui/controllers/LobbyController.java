package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.PawnClientMessage;
import it.castelli.connection.messages.StartGameClientMessage;
import it.castelli.gameLogic.Player;
import it.castelli.gui.customComponents.ChatComponent;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Controller for lobby FXML
 */
public class LobbyController
{
	private String pawn;
	public static Button playButton;
	@FXML
	private ChatComponent chat;
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


	@FXML
	private void initialize()
	{
		// TODO: hide playButton if not host
		playButton.setDisable(true);
		playButton.setVisible(false);

		playButton.setOnAction(
				event -> {
					SceneManager.getInstance().showScene(SceneType.BOARD);
					ClientMain.getConnection().send(ClientMessages.START_GAME_MESSAGE_NAME,
					                                Serializer.toJson(new StartGameClientMessage(Game.getGameCode())));
				});

		chooseButton.setOnAction(
				event-> {
					boolean pawnAvailable = true;
					for (Player player : Game.getGameManager().getPlayers())
					{
						if(player.getPawn().equals(pawn))
							pawnAvailable = false;
					}
					if(pawnAvailable)
						ClientMain.getConnection().send(ClientMessages.PAWN_MESSAGE_NAME, Serializer.toJson(new PawnClientMessage(pawn, Game.getGameCode())));
				});
		thimblePawn.setOnMouseClicked(event -> Game.getPlayer().setPawn("thimble.png"));
		dogPawn.setOnMouseClicked(event -> Game.getPlayer().setPawn("dog.png"));
		wagonPawn.setOnMouseClicked(event -> Game.getPlayer().setPawn("wagon.png"));
		carPawn.setOnMouseClicked(event -> Game.getPlayer().setPawn("car.png"));
		shoePawn.setOnMouseClicked(event -> Game.getPlayer().setPawn("shoe.png"));
		boatPawn.setOnMouseClicked(event -> Game.getPlayer().setPawn("boat.png"));
	}
}
