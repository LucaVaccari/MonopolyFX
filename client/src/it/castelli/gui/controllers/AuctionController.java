package it.castelli.gui.controllers;

import it.castelli.Game;
import it.castelli.gui.customComponents.ChatComponent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller for the auction FXML
 */
public class AuctionController
{
	/**
	 * Temp variable for storing the money to offer
	 */
	private int yourOffer = 0;

	@FXML
	private ChatComponent chat;
	@FXML
	private Label totalMoneyLabel;
	@FXML
	private Label auctionBaseLabel;
	@FXML
	private Button minusOneButton;
	@FXML
	private Button minusTenButton;
	@FXML
	private Button minusHundredButton;
	@FXML
	private Label yourOfferLabel;
	@FXML
	private Button offerButton;
	@FXML
	private Button plusOneButton;
	@FXML
	private Button plusTenButton;
	@FXML
	private Button plusHundredButton;

	@FXML
	public void initialize()
	{
		if (Game.getPlayer() != null)
		{
			totalMoneyLabel.setText(Game.getPlayer().getMoney() + "M");
		}
		minusOneButton.setOnAction(event -> changeOffer(-1));
		minusTenButton.setOnAction(event -> changeOffer(-10));
		minusHundredButton.setOnAction(event -> changeOffer(-100));
		plusOneButton.setOnAction(event -> changeOffer(1));
		plusTenButton.setOnAction(event -> changeOffer(10));
		plusHundredButton.setOnAction(event -> changeOffer(100));

		offerButton.setOnAction(event -> {
			// TODO: send offer to server
		});
	}

	/**
	 * Update all the GUI elements to show current information
	 */
	public void update()
	{
		// TODO: set auction base label
	}

	/**
	 * Change the offer by a positive or a negative value. The sum of the current offer and the value should be
	 * greater than the base offer
	 *
	 * @param value The value to be added to the current offer
	 */
	private void changeOffer(int value)
	{
		if (yourOffer + value > 0) // TODO: cannot go under auction base or over your money
		{
			yourOffer += value;
			yourOfferLabel.setText(yourOffer + "M");
		}
	}
}
