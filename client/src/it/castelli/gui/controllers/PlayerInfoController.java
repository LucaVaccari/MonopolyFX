package it.castelli.gui.controllers;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.customComponents.SmallTerrainViewComponent;
import javafx.fxml.FXML;
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
	private FlowPane propertyView;

	private void setPlayer(Player player)
	{
		playerNameLabel.setText(player.getName());
		playerMoneyLabel.setText(player.getMoney() + "M");
		for (Contract contract : player.getContracts())
			propertyView.getChildren().add(new SmallTerrainViewComponent(contract));
	}
}
