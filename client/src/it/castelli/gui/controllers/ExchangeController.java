package it.castelli.gui.controllers;

import it.castelli.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Controller for exchange FXML
 */
public class ExchangeController
{
	@FXML
	private Label yourTotalMoneyLabel;
	@FXML
	private Label yourOfferLabel;
	@FXML
	private ImageView yourChoiceImage;
	@FXML
	private Label hisTotalMoneyLabel;
	@FXML
	private Label hisOfferLabel;
	@FXML
	private ImageView hisChoiceImage;
	@FXML
	private Button acceptButton;
	@FXML
	private Button cancelButton;

	@FXML
	public void initialize()
	{
		if (Game.getPlayer() != null)
		{
			yourTotalMoneyLabel.setText(Game.getPlayer().getMoney() + "M");
		}

		// TODO: find a way to change your offer

		acceptButton.setOnAction(event -> {
			// TODO: send accept to the server
		});

		cancelButton.setOnAction(event -> {
			// TODO: send cancel to the server
		});
	}
}
