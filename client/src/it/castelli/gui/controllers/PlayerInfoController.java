package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.CreateExchangeClientMessage;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.customComponents.SmallTerrainViewComponent;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Controller for playerInfo FXML
 */
public class PlayerInfoController
{
	@FXML
	private Label playerNameLabel;
	@FXML
	private Label playerMoneyLabel;
	@FXML
	private Button exchangeButton;
	@FXML
	private FlowPane propertyView;

	public void setPlayer(Player player)
	{
		playerNameLabel.setText(player.getName());
		playerMoneyLabel.setText(player.getMoney() + "M");

		for (Contract contract : player.getContracts())
			propertyView.getChildren().add(new SmallTerrainViewComponent(contract));

		if (player.betterEquals(Game.getPlayer()))
		{
			exchangeButton.setDisable(true);
			exchangeButton.setVisible(false);
		}

		exchangeButton.setTooltip(new Tooltip("Effettuate uno scambio con questo giocatore"));
		exchangeButton.setOnAction(event -> {
			ClientMain.getConnection()
					.send(ClientMessages.CREATE_EXCHANGE_MESSAGE_NAME, Serializer
							.toJson(new CreateExchangeClientMessage(Game.getPlayer(), player, Game.getGameCode())));
			Stage thisStage = SceneManager.getInstance().getStageByType(SceneType.PLAYER_INFO);
			if (thisStage != null)
				thisStage.close();
		});
	}
}
