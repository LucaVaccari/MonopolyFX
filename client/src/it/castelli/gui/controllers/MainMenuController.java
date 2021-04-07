package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.CreateGameClientMessage;
import it.castelli.connection.messages.JoinGameClientMessage;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.scene.SceneManager;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for mainMenu FXML
 */
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
			                                "Inserisci il codice di gioco", "Inserisci il codice che l'host " +
			                                                                "della partita ti ha fornito:");
			// TODO: input code and name
			int matchCode = 0;
			String playerName = "Pippo";
			ClientMain.getConnection().send(ClientMessages.JOIN_GAME_MESSAGE_NAME,
			                                Serializer.toJson(new JoinGameClientMessage(matchCode, playerName)));
			// TODO: go to lobby
		});

		createButton.setOnAction(event -> {
			// TODO: create game and open lobby
			// TODO: input player name
			String playerName = "Pippo";
			ClientMain.getConnection()
					.send(ClientMessages.CREATE_GAME_MESSAGE_NAME, Serializer.toJson(new CreateGameClientMessage(playerName)));
		});
	}
}
