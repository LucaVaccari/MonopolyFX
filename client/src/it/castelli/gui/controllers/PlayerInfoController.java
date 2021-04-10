package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.CreateExchangeClientMessage;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.customComponents.SmallTerrainViewComponent;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

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

	private void setPlayer(Player player)
	{
		playerNameLabel.setText(player.getName());
		playerMoneyLabel.setText(player.getMoney() + "M");
		for (Contract contract : player.getContracts())
			propertyView.getChildren().add(new SmallTerrainViewComponent(contract));

		exchangeButton.setOnAction(event -> ClientMain.getConnection()
				.send(ClientMessages.CREATE_EXCHANGE_MESSAGE_NAME, Serializer
						.toJson(new CreateExchangeClientMessage(Game.getPlayer(), player, Game.getGameCode()))));
	}
}
