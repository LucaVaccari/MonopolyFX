package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.AuctionOfferClientMessage;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.gameLogic.transactions.Auction;
import it.castelli.gui.customComponents.ChatComponent;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller for the auction FXML
 */
public class AuctionController
{
	private static AuctionController instance;

	/**
	 * Temp variable for storing the money to offer
	 */
	private int yourOffer = 0;
	private Auction auction;

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

	public static AuctionController getInstance()
	{
		return instance;
	}

	@FXML
	public void initialize()
	{
		instance = this;

		minusOneButton.setOnAction(event -> changeOffer(-1));
		minusTenButton.setOnAction(event -> changeOffer(-10));
		minusHundredButton.setOnAction(event -> changeOffer(-100));
		plusOneButton.setOnAction(event -> changeOffer(1));
		plusTenButton.setOnAction(event -> changeOffer(10));
		plusHundredButton.setOnAction(event -> changeOffer(100));

		offerButton.setOnAction(event -> {
			// Send offer to the server
			if (yourOffer > auction.getBestOfferProposed())
				ClientMain.getConnection().send(ClientMessages.AUCTION_OFFER_MESSAGE_NAME, Serializer
						.toJson(new AuctionOfferClientMessage(yourOffer, Game.getGameCode())));
		});
	}

	/**
	 * Update all the GUI elements to show current information
	 */
	public void update()
	{
		auctionBaseLabel.setText(auction.getBestOfferProposed() + "M");
		totalMoneyLabel.setText(Game.getPlayer().getMoney() + "M");
	}

	/**
	 * Change the offer by a positive or a negative value. The sum of the current offer and the value should be greater
	 * than the base offer
	 *
	 * @param value The value to be added to the current offer
	 */
	private void changeOffer(int value)
	{
		if (yourOffer + value > Game.getPlayer().getMoney())
		{
			yourOffer += value;
			yourOfferLabel.setText(yourOffer + "M");
		}
	}

	public void setAuction(Auction auction)
	{
		this.auction = auction;
		update();
	}

	public ChatComponent getChat()
	{
		return chat;
	}
}
