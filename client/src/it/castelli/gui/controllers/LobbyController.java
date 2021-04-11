package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.StartGameClientMessage;
import it.castelli.gameLogic.Player;
import it.castelli.gui.customComponents.ChatComponent;
import it.castelli.gui.customComponents.PlayerInfoComponent;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Controller for lobby FXML
 */
public class LobbyController
{
	/**
	 * Singleton instance
	 */
	private static LobbyController instance;

	private String pawnPath;

	@FXML
	private Button playButton;
	@FXML
	private ChatComponent chat;
	@FXML
	private VBox playerListView;

	public static LobbyController getInstance()
	{
		return instance;
	}

	@FXML
	private void initialize()
	{
		instance = this;

		playButton.setVisible(false);
		playButton.setDisable(true);

		playButton.setOnAction(
				event -> {
					ClientMain.getConnection().send(ClientMessages.START_GAME_MESSAGE_NAME,
							Serializer.toJson(new StartGameClientMessage(Game.getGameCode())));
				});

	}

	public void showPlayButton()
	{
		playButton.setDisable(false);
		playButton.setVisible(true);
	}

	public void updatePlayerListView()
	{
		playerListView.getChildren().clear();
		for (Player player : Game.getGameManager().getPlayers())
		{
			//player.setPawnPath(pawnPath);
			PlayerInfoComponent playerInfoComponent = new PlayerInfoComponent(player);
			playerListView.getChildren().add(playerInfoComponent);
		}
	}

	public ChatComponent getChat()
	{
		return chat;
	}
}
