package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.AuctionOfferClientMessage;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gameLogic.transactions.Auction;
import it.castelli.gui.customComponents.ChatComponent;
import it.castelli.gui.customComponents.CompanyViewComponent;
import it.castelli.gui.customComponents.PropertyViewComponent;
import it.castelli.gui.customComponents.StationViewComponent;
import it.castelli.serialization.Serializer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

/**
 * Controller for the auction FXML
 */
public class AuctionController
{
	private static AuctionController instance;

	private Auction auction;

	@FXML
	private ChatComponent chat;
	@FXML
	private Label totalMoneyLabel;
	@FXML
	private Label auctionBaseLabel;
	@FXML
	private Label yourOfferLabel;
	@FXML
	private Button offerButton;
	@FXML
	private TextField offerTextField;
	@FXML
	private VBox terrainVBox;
	@FXML
	private Label timerLabel;

	public static AuctionController getInstance()
	{
		return instance;
	}

	@FXML
	public void initialize()
	{
		instance = this;

		offerTextField.setOnAction(this::offer);
		offerButton.setOnAction(this::offer);
		offerButton.setTooltip(new Tooltip("Proponete la Vostra offerta"));
	}

	/**
	 * Update all the GUI elements to show current information
	 */
	public void update()
	{
		auctionBaseLabel.setText("Offerente:"+auction.getPlayer().getName()+" prezzo attuale: " + auction.getBestOfferProposed() + "M");
		totalMoneyLabel.setText("i tuoi soldi: " + Game.getPlayer().getMoney() + "M");
		yourOfferLabel.setText(String.valueOf(auction.getBestOfferProposed()));
	}

	/**
	 * Set the current ongoing auction
	 *
	 * @param auction The ongoing auction
	 */
	public void setAuction(Auction auction)
	{
		this.auction = auction;
		Platform.runLater(() -> {
			update();
			terrainVBox.getChildren().clear();
			if (auction.getContract() instanceof PropertyContract)
				terrainVBox.getChildren().add(new PropertyViewComponent((PropertyContract) auction.getContract()));
			else if (auction.getContract() instanceof CompanyContract)
				terrainVBox.getChildren().add(new CompanyViewComponent((CompanyContract) auction.getContract()));
			else
				terrainVBox.getChildren().add(new StationViewComponent((StationContract) auction.getContract()));
		});
	}

	/**
	 * Callback for sending an offer to the server
	 */
	private void offer(ActionEvent event)
	{
		// Send offer to the server
		try
		{
			int offer = Integer.parseInt(offerTextField.getText());
			if (offer > auction.getBestOfferProposed() && Game.getPlayer().hasMoney(offer))
			{
				ClientMain.getConnection().send(ClientMessages.AUCTION_OFFER_MESSAGE_NAME, Serializer
						.toJson(new AuctionOfferClientMessage(offer, Game.getGameCode())));
				yourOfferLabel.setText("la tua offerta: " + offerTextField.getText() + "M");
			}
			update();
		}
		catch (NumberFormatException ignored)
		{
		}
	}

	/**
	 * Reset all buttons and inputs to their default values
	 */
	public void reset()
	{
		terrainVBox.getChildren().clear();
		offerTextField.setText(String.valueOf(auction.getBestOfferProposed()+1));
	}

	public void setTimer(int timerValue)
	{
		timerLabel.setText(String.valueOf(timerValue));
	}

	/**
	 * Getter for the Auction window chat
	 *
	 * @return The chat of this window
	 */
	public ChatComponent getChat()
	{
		return chat;
	}
}
