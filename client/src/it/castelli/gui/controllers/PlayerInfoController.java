package it.castelli.gui.controllers;

import it.castelli.gameLogic.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for playerInfo FXML
 */
public class PlayerInfoController
{
	@FXML
	private Label playerNameLabel;
	@FXML
	private Label playerMoneyLabel;

	public void setPlayer(Player player)
	{
		playerNameLabel.setText(player.getName());
		playerMoneyLabel.setText(player.getMoney() + "M");
		// TODO: show player properties
	}
}
