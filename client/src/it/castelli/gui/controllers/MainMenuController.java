package it.castelli.gui.controllers;

import it.castelli.gui.AlertUtil;
import it.castelli.gui.scene.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController
{
	@FXML
	private Button exitButton;
	@FXML
	private Button joinButton;
	@FXML
	private Button createButton;

	@FXML
	private void initialize()
	{
		exitButton.setOnAction(
				event -> SceneManager.getInstance().getPrimaryStage().close());

		joinButton.setOnAction(event -> {
			AlertUtil.showTextInputDialogue("", "Codice di gioco",
					"Inserisci il codice di gioco",
					"Inserisci il codice che l'host " +
							"della partita ti ha fornito:");
			// TODO: process input code
			// TODO: go to lobby
		});

		createButton.setOnAction(event -> {
			// TODO: create game and open lobby
		});
	}
}
