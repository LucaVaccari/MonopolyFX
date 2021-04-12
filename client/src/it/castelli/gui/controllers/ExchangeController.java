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
import it.castelli.gui.AlertUtil;
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
	private static final String CROSS_IMAGE_PATH = "/images/cross.png";
	private static ExchangeController instance;
	private Player you, him;
	private Asset yourAsset, hisAsset;
	private Exchange exchange;

	@FXML
	private Label yourNameLabel;
	@FXML
	private Label yourTotalMoneyLabel;
	@FXML
	private TextField yourOfferTextField;
	@FXML
	private Button addPropertyButton;
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
//
		yourOfferTextField.setOnAction(event -> {
			try
			{
				if (exchange == null)
				{
					AlertUtil.showInformationAlert("Errore", "Lo scambio non è ancora stato inizializzato", "");
					return;
				}
				int yourOffer = Integer.parseInt(yourOfferTextField.getText());
				yourAsset.setMoney(yourOffer);
				ClientMain.getConnection().send(ClientMessages.CHANGE_EXCHANGE_ASSET_MESSAGE_NAME, Serializer
						.toJson(new ChangeExchangeAssetClientMessage(
								yourAsset, Game.getGameCode(), Game.getPlayer())));
			}
			catch (NumberFormatException ignored)
			{
			}
		});
//
		addPropertyButton.setOnAction(event -> {
			if (exchange == null)
			{
				AlertUtil.showInformationAlert("Errore", "Lo scambio non è ancora stato inizializzato", "");
				return;
			}
			Optional<Contract> optionalContract = new TerrainChoiceDialog(you).showAndWait();
			if (optionalContract.isPresent())
			{
				yourAsset.getContracts().add(optionalContract.get());
				ClientMain.getConnection().send(ClientMessages.CHANGE_EXCHANGE_ASSET_MESSAGE_NAME, Serializer
						.toJson(new ChangeExchangeAssetClientMessage(
								yourAsset, Game.getGameCode(), Game.getPlayer())));
			}
		});
//
		acceptButton.setOnAction(event -> {
			if (exchange == null)
			{
				AlertUtil.showInformationAlert("Errore", "Lo scambio non è ancora stato inizializzato", "");
				return;
			}
			ClientMain.getConnection().send(ClientMessages.ACCEPT_EXCHANGE_MESSAGE_NAME, Serializer
					.toJson(new AcceptExchangeClientMessage(true, Game.getPlayer(), Game.getGameCode())));
			yourChoiceImage.setImage(new Image(String.valueOf(getClass().getResource(TICK_IMAGE_PATH))));
		});

		refuseButton.setOnAction(event -> {
			if (exchange == null)
			{
				AlertUtil.showInformationAlert("Errore", "Lo scambio non è ancora stato inizializzato", "");
				return;
			}
			ClientMain.getConnection().send(ClientMessages.ACCEPT_EXCHANGE_MESSAGE_NAME, Serializer
					.toJson(new AcceptExchangeClientMessage(false, Game.getPlayer(), Game.getGameCode())));
			yourChoiceImage.setImage(new Image(String.valueOf(getClass().getResource(CROSS_IMAGE_PATH))));
		});
//
		//TODO:Luca questa cosa che hai fatto non fa partire il programma
//		SceneManager.getInstance().getStageByType(SceneType.EXCHANGE).setOnCloseRequest(
//				event -> ClientMain.getConnection().send(ClientMessages.REFUSE_EXCHANGE_MESSAGE_NAME, Serializer
//						.toJson(new RefuseExchangeClientMessage(exchange, Game.getGameCode()))));
	}

	/**
	 * Update all the user interface to the most recent information
	 */
	public void update()
	{
		yourNameLabel.setText(you.getName());
		yourOfferTextField.setText(yourAsset.getMoney() + "M");
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
	}

	/**
	 * Set the exchange instance of the ongoing exchange
	 *
	 * @param exchange The exchange
	 */
	public void setExchange(Exchange exchange)
	{
		this.exchange = exchange;
		if (Game.getPlayer().equals(exchange.getPlayer1()))
		{
			you = exchange.getPlayer1();
			him = exchange.getPlayer2();
			yourAsset = exchange.getAsset1();
			hisAsset = exchange.getAsset2();
		}
		else
		{
			you = exchange.getPlayer2();
			him = exchange.getPlayer1();
			yourAsset = exchange.getAsset2();
			hisAsset = exchange.getAsset1();
		}
		Platform.runLater(this::update);
	}

	public ChatComponent getChat()
	{
		return chat;
	}
}
