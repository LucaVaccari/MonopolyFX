package it.castelli.gui.controllers;

import it.castelli.gui.customComponents.ChatComponent;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
				event -> SceneManager.getInstance().showScene(SceneType.BOARD));
	}
}
