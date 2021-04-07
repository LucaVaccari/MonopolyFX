package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.StartGameClientMessage;
import it.castelli.gui.customComponents.ChatComponent;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for lobby FXML
 */
public class LobbyController
{
	@FXML
	private Button playButton;
	@FXML
	private ChatComponent chat;

	@FXML
	private void initialize()
	{
		// TODO: hide playButton if not host
		playButton.setOnAction(
				event -> {
					SceneManager.getInstance().showScene(SceneType.BOARD);
					ClientMain.getConnection().send(ClientMessages.START_GAME_MESSAGE_NAME,
					                                Serializer.toJson(new StartGameClientMessage(Game.getGameCode())));
				});
	}
}
