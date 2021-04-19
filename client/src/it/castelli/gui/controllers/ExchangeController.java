package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.AcceptExchangeClientMessage;
import it.castelli.connection.messages.ChangeExchangeAssetClientMessage;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.RefuseExchangeClientMessage;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.transactions.Asset;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.gui.customComponents.ChatComponent;
import it.castelli.gui.customComponents.SmallTerrainViewComponent;
import it.castelli.gui.customComponents.TerrainChoiceDialog;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.util.Optional;

/**
 * Controller for exchange FXML
 */
public class ExchangeController
{
	private static final String TICK_IMAGE_PATH = "/images/tick.png";
	private static final String PLACEHOLDER_IMAGE_PATH = "/images/placeholder.png";
	private static ExchangeController instance;
	private Exchange exchange;
	private boolean isPlayer1 = true;

	@FXML
	private Label yourNameLabel;
	@FXML
	private Label yourTotalMoneyLabel;
	@FXML
	private TextField yourOfferTextField;
	@FXML
	private Button addPropertyButton;
	@FXML
	private Button removeProperties;
	@FXML
	private FlowPane yourPropertiesPane;
	@FXML
	private ImageView yourChoiceImage;

	@FXML
	private Label hisNameLabel;
	@FXML
	private Label hisTotalMoneyLabel;
	@FXML
	private Label hisOfferLabel;
	@FXML
	private FlowPane hisPropertiesPane;
	@FXML
	private ImageView hisChoiceImage;

	@FXML
	private Button acceptButton;
	@FXML
	private Button refuseButton;

	@FXML
	private ChatComponent chat;

	public static ExchangeController getInstance()
	{
		return instance;
	}

	@FXML
	public void initialize()
	{
		instance = this;

		yourOfferTextField.setOnKeyTyped(event -> {
			String offerText = yourOfferTextField.getText();
			try
			{
				int yourOffer = Integer.parseInt(offerText);

				// check if the player has enough money
				if (!Game.getPlayer().hasMoney(yourOffer))
				{
					yourOffer = Game.getPlayer().getMoney() - 1;
					yourOfferTextField.setText(String.valueOf(yourOffer));
				}
				// reverse the offer if negative
				if (yourOffer < 0)
				{
					yourOffer = Math.abs(yourOffer);
					yourOfferTextField.setText(String.valueOf(yourOffer));
				}

				if (isPlayer1)
					exchange.getAsset1().setMoney(yourOffer);
				else
					exchange.getAsset2().setMoney(yourOffer);

				ClientMain.getConnection().send(ClientMessages.CHANGE_EXCHANGE_ASSET_MESSAGE_NAME, Serializer
						.toJson(new ChangeExchangeAssetClientMessage(
								isPlayer1 ? exchange.getAsset1() : exchange.getAsset2(), Game.getGameCode(), Game
								.getPlayer())));
			}
			catch (NumberFormatException ignored)
			{
			}
		});

		addPropertyButton.setTooltip(new Tooltip("Aggiungete proprieta' da scambiare con l'altro giocatore"));
		addPropertyButton.setOnAction(event -> {
			Optional<Contract> optionalContract =
					new TerrainChoiceDialog(isPlayer1 ? exchange.getPlayer1() : exchange.getPlayer2()).showAndWait();
			if (optionalContract.isPresent())
			{
				Contract contract = optionalContract.get();
				if (isPlayer1)
					exchange.getAsset1().getContracts().add(contract);
				else
					exchange.getAsset2().getContracts().add(contract);
				ClientMain.getConnection().send(ClientMessages.CHANGE_EXCHANGE_ASSET_MESSAGE_NAME, Serializer
						.toJson(new ChangeExchangeAssetClientMessage(
								isPlayer1 ? exchange.getAsset1() : exchange.getAsset2(), Game.getGameCode(), Game
								.getPlayer())));
			}
		});

		removeProperties.setTooltip(new Tooltip("Rimuovete tutte le proprieta' che volevate scambiare"));
		removeProperties.setOnAction(event -> {
			if (isPlayer1)
				exchange.getAsset1().getContracts().clear();
			else
				exchange.getAsset2().getContracts().clear();

			ClientMain.getConnection().send(ClientMessages.CHANGE_EXCHANGE_ASSET_MESSAGE_NAME, Serializer
					.toJson(new ChangeExchangeAssetClientMessage(
							isPlayer1 ? exchange.getAsset1() : exchange.getAsset2(), Game.getGameCode(), Game
							.getPlayer())));
		});

		acceptButton.setOnAction(event -> ClientMain.getConnection()
				.send(ClientMessages.ACCEPT_EXCHANGE_MESSAGE_NAME, Serializer
						.toJson(new AcceptExchangeClientMessage(!(isPlayer1 ? exchange.getAccepted1() :
								exchange.getAccepted2()), Game.getPlayer(), Game.getGameCode()))));

		refuseButton.setTooltip(new Tooltip("Rifiutate lo scambio (equivale a chiudere la finestra)"));
		refuseButton.setOnAction(event -> ClientMain.getConnection()
				.send(ClientMessages.REFUSE_EXCHANGE_MESSAGE_NAME, Serializer
						.toJson(new RefuseExchangeClientMessage(exchange, Game.getGameCode()))));
	}

	/**
	 * Update all the user interface to the most recent information
	 */
	public void update()
	{
		Player you, him;
		if (isPlayer1)
		{
			you = exchange.getPlayer1();
			him = exchange.getPlayer2();
		}
		else
		{
			you = exchange.getPlayer2();
			him = exchange.getPlayer1();
		}
		Asset yourAsset, hisAsset;
		if (isPlayer1)
		{
			yourAsset = exchange.getAsset1();
			hisAsset = exchange.getAsset2();
		}
		else
		{
			yourAsset = exchange.getAsset2();
			hisAsset = exchange.getAsset1();
		}

		yourNameLabel.setText(you.getName());
		yourTotalMoneyLabel.setText(you.getMoney() + "M");
		yourPropertiesPane.getChildren().clear();
		for (Contract contract : yourAsset.getContracts())
			yourPropertiesPane.getChildren().add(new SmallTerrainViewComponent(contract));

		hisNameLabel.setText(him.getName());
		hisOfferLabel.setText(hisAsset.getMoney() + "M");
		hisTotalMoneyLabel.setText(him.getMoney() + "M");
		hisPropertiesPane.getChildren().clear();
		for (Contract contract : hisAsset.getContracts())
			hisPropertiesPane.getChildren().add(new SmallTerrainViewComponent(contract));

		SceneManager.getInstance().getStageByType(SceneType.EXCHANGE).setOnCloseRequest(
				event -> ClientMain.getConnection().send(ClientMessages.REFUSE_EXCHANGE_MESSAGE_NAME, Serializer
						.toJson(new RefuseExchangeClientMessage(exchange, Game.getGameCode()))));

		if ((isPlayer1 ? exchange.getAccepted1() : exchange.getAccepted2()))
		{
			acceptButton.setTooltip(new Tooltip("Annullate l'offerta per cambiare l'accordo (non annulla lo scambio"));
			acceptButton.setText("Annulla offerta");
			yourChoiceImage.setImage(new Image(String.valueOf(getClass().getResource(TICK_IMAGE_PATH))));
		}
		else
		{
			acceptButton
					.setTooltip(new Tooltip("Accettate l'offerta (se anche l'altro accettera', lo scambio si " +
							"concludera'"));
			acceptButton.setText("Accetta");
			yourChoiceImage.setImage(new Image(String.valueOf(getClass().getResource(PLACEHOLDER_IMAGE_PATH))));
		}


		if ((isPlayer1 ? exchange.getAccepted2() : exchange.getAccepted1()))
			hisChoiceImage.setImage(new Image(String.valueOf(getClass().getResource(TICK_IMAGE_PATH))));
		else
			hisChoiceImage.setImage(new Image(String.valueOf(getClass().getResource(PLACEHOLDER_IMAGE_PATH))));
	}

	/**
	 * Set the exchange instance of the ongoing exchange
	 *
	 * @param exchange The exchange
	 */
	public void setExchange(Exchange exchange)
	{
		this.exchange = exchange;
		isPlayer1 = Game.getPlayer().betterEquals(exchange.getPlayer1());
		Platform.runLater(this::update);
	}

	/**
	 * Reset all buttons and inputs to their default values
	 */
	public void reset()
	{
		yourOfferTextField.setText("0");
		hisOfferLabel.setText("0");
		yourPropertiesPane.getChildren().clear();
		hisPropertiesPane.getChildren().clear();
		yourChoiceImage.setImage(new Image(String.valueOf(getClass().getResource(PLACEHOLDER_IMAGE_PATH))));
		hisChoiceImage.setImage(new Image(String.valueOf(getClass().getResource(PLACEHOLDER_IMAGE_PATH))));
		acceptButton.setText("Accetta");
	}

	/**
	 * Getter for the exchange window chat
	 *
	 * @return The exchange chat
	 */
	public ChatComponent getChat()
	{
		return chat;
	}
}
