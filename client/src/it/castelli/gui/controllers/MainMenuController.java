package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.CreateGameClientMessage;
import it.castelli.connection.messages.JoinGameClientMessage;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.scene.SceneManager;
import it.castelli.serialization.Serializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Optional;

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

	private String playerName = "";

	@FXML
	private void initialize()
	{
		exitButton.setOnAction(event -> SceneManager.getInstance().getPrimaryStage().close());
		//TODO: actually close the program

		joinButton.setOnAction(this::onJoinButtonPressed);

		createButton.setOnAction(event -> {
			playerName = askPlayerName();
			if (playerName == null) return;
			ClientMain.getConnection().send(ClientMessages.CREATE_GAME_MESSAGE_NAME,
					Serializer.toJson(new CreateGameClientMessage(playerName)));
		});
	}

	private void onJoinButtonPressed(ActionEvent event)
	{
		int matchCode = askGameCode();
		if (matchCode == -1) return;
		playerName = askPlayerName();
		if (playerName == null) return;
		ClientMain.getConnection().send(ClientMessages.JOIN_GAME_MESSAGE_NAME,
				Serializer.toJson(new JoinGameClientMessage(matchCode, playerName)));
	}

	private String askPlayerName()
	{
		Optional<String> nameResult = AlertUtil.showTextInputDialogue(playerName, "Nome",
				"Inserite il nome da usare in gioco", "Nome:");
		if (nameResult.isPresent())
		{
			String name = nameResult.get().strip();
			if (name.length() == 0)
			{
				AlertUtil.showErrorAlert("Nome invalido", "Nome vuoto", "Il nome non può essere vuoto");
				return askPlayerName();
			}

			return name;
		}
		return null;
	}

	private int askGameCode()
	{
		Optional<String> gameCodeResult = AlertUtil.showTextInputDialogue("", "Codice di gioco",
				"Inserite il codice che l'host della partita Vi ha fornito",
				"Codice:");
		if (gameCodeResult.isPresent())
		{
			try
			{
				int matchCode = Integer.parseInt(gameCodeResult.get());
				if (matchCode < 0)
				{
					AlertUtil.showErrorAlert("Errore", "Inserire un codice valido",
							"Il codice inserito deve essere un intero maggiore di 0");
					return askGameCode();
				}
				return matchCode;
			}
			catch (NumberFormatException e)
			{
				AlertUtil.showErrorAlert("Errore", "Inserire un codice valido",
						"Il codice inserito non e' un numero.");
				return askGameCode();
			}
		}
		return -1;
	}
}
