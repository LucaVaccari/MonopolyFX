package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.LeaveGameClientMessage;
import it.castelli.connection.messages.StartGameClientMessage;
import it.castelli.gameLogic.Player;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.customComponents.ChatComponent;
import it.castelli.gui.customComponents.PlayerInfoComponent;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Priority;
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

	@FXML
	private Button backButton;
	@FXML
	private Button playButton;
	@FXML
	private ChatComponent chat;
	@FXML
	private Label codeLabel;
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

		backButton.setTooltip(new Tooltip("Tornate indietro al menu (uscendo dalla partita)"));
		backButton.setOnAction(event -> {
			Game.setHost(false);
			SceneManager.getInstance().showScene(SceneType.MAIN_MENU);
			ClientMain.getConnection().send(ClientMessages.LEAVE_GAME_MESSAGE_NAME,
					Serializer.toJson(new LeaveGameClientMessage(Game.getGameCode())));
		});

		Tooltip.install(codeLabel, new Tooltip("Condividete questo codice con i Vostri amici!"));
		codeLabel.setText("Codice: " + Game.getGameCode());

		playButton.setVisible(false);
		playButton.setDisable(true);

		playButton.setTooltip(new Tooltip("Iniziate la partita (nessun altro potra' piu' entrare)"));
		playButton.setOnAction(
				event -> {
					boolean canStart = true;
					for (Player player : Game.getGameManager().getPlayers())
					{
						if (player.getPawn() == null)
						{
							canStart = false;
							break;
						}
					}
					if (canStart)
						ClientMain.getConnection().send(ClientMessages.START_GAME_MESSAGE_NAME,
								Serializer.toJson(new StartGameClientMessage(
										Game.getGameCode())));
					else
						AlertUtil.showInformationAlert("Aspettate", "Non tutti i giocatori sono pronti",
								"Alcuni giocatori stanno ancora scegliendo la pedina da " +
										"utilizzare in gioco");
				});
		update();
	}

	/**
	 * Update all the visual information
	 */
	public void update()
	{
		updatePlayerListView();
		codeLabel.setText("Codice: " + Game.getGameCode());
		playButton.setDisable(!Game.isHost() || Game.getGameManager().getPlayers().size() <= 1);
		playButton.setVisible(Game.isHost());
	}

	/**
	 * Update the visual list of players
	 */
	public void updatePlayerListView()
	{
		playerListView.getChildren().clear();
		for (Player player : Game.getGameManager().getPlayers())
		{
			PlayerInfoComponent playerInfoComponent = new PlayerInfoComponent(player);
			VBox.setVgrow(playerInfoComponent, Priority.ALWAYS);
			playerListView.getChildren().add(playerInfoComponent);
		}
	}

	public ChatComponent getChat()
	{
		return chat;
	}
}
