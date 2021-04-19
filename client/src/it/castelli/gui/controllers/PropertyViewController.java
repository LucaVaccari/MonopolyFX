package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.*;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gui.GUIUtils;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

/**
 * Controller for propertyView FXML
 */
public class PropertyViewController
{
	private static PropertyViewController instance;

	private int contractID;

	@FXML
	private Label valueLabel;
	@FXML
	private Label titleLabel;
	@FXML
	private Label revenueLabel;
	@FXML
	private Label revenue1houseLabel;
	@FXML
	private Label revenue2housesLabel;
	@FXML
	private Label revenue3housesLabel;
	@FXML
	private Label revenue4housesLabel;
	@FXML
	private Label revenueHotelLabel;
	@FXML
	private Label houseCostLabel;
	@FXML
	private Label mortgageValueLabel;
	@FXML
	private HBox onlyIfOwnedPane1;
	@FXML
	private Label numberOfHousesLabel;
	@FXML
	private Parent onlyIfOwnedPane2;
	@FXML
	private Button sellButton;
	@FXML
	private Button mortgageButton;
	@FXML
	private Button buyHouseButton;
	@FXML
	private Button sellHouseButton;

	public static PropertyViewController getInstance()
	{
		return instance;
	}

	@FXML
	public void initialize()
	{
		instance = this;
	}

	/**
	 * Set the contract id and update the visuals of the PropertyView.
	 *
	 * @param id The id of the contract of the property
	 */
	public void setContract(int id)
	{
		contractID = id;
		update();
	}

	/**
	 * Update the visual information
	 */
	public void update()
	{
		PropertyContract contract = (PropertyContract) Game.getGameManager().getContract(contractID);

		titleLabel.setStyle("-fx-background-color: " + GUIUtils.getPropertyColorsCodes().get(contract.getColor()));

		valueLabel.setText(String.valueOf(contract.getValue()));
		titleLabel.setText(contract.getName());
		revenueLabel.setText(String.valueOf(contract.getRevenues()[0]));
		revenue1houseLabel.setText(String.valueOf(contract.getRevenues()[1]));
		revenue2housesLabel.setText(String.valueOf(contract.getRevenues()[2]));
		revenue3housesLabel.setText(String.valueOf(contract.getRevenues()[3]));
		revenue4housesLabel.setText(String.valueOf(contract.getRevenues()[4]));
		revenueHotelLabel.setText(String.valueOf(contract.getRevenues()[5]));
		houseCostLabel.setText(String.valueOf(contract.getHouseCost()));
		mortgageValueLabel.setText(String.valueOf(contract.getValue() / 2));

		boolean exchanging = Game.getGameManager().getExchangeFromPlayer(Game.getPlayer()) != null;

		if (contract.getOwner() != null && !exchanging)
		{
			boolean isOwnedByMe = contract.getOwner().toPlayer().betterEquals(Game.getPlayer());
			boolean isMyRound = contract.getOwner().toPlayer()
					.betterEquals(Game.getGameManager().getCurrentRound().getCurrentActivePlayer());
			onlyIfOwnedPane1.setVisible(isOwnedByMe);
			onlyIfOwnedPane1.setDisable(!isOwnedByMe);
			onlyIfOwnedPane2.setVisible(isOwnedByMe);
			onlyIfOwnedPane2.setDisable(!isOwnedByMe && !isMyRound);

			numberOfHousesLabel.setText(String.valueOf(contract.getNumberOfHouses()));

			sellButton.setTooltip(new Tooltip("Vendete questa proprieta'"));
			sellButton.setOnAction(event -> {
				ClientMain.getConnection().send(ClientMessages.SELL_CONTRACT_MESSAGE_NAME, Serializer
						.toJson(new SellContractClientMessage(Game.getGameCode(), contract)));
				SceneManager.getInstance().getStageByType(SceneType.PROPERTY_VIEW).close();
			});

			if (contract.isMortgaged())
				mortgageButton.setDisable(!Game.getPlayer().hasMoney(contract.getMortgageValue() * 11 / 10));

			buyHouseButton.setDisable(contract.isMortgaged());
			sellHouseButton.setDisable(contract.isMortgaged());
			mortgageButton.setDisable(contract.getNumberOfHouses() > 0);

			mortgageButton.setTooltip(new Tooltip(contract.isMortgaged() ?
					"Rimuovete l'ipoteca e riottenete il terreno (pagando il 10% in piu' del costo dell'ipoteca)" :
					"Ipotecate il terreno"));
			mortgageButton.setText(contract.isMortgaged() ? "Sciogli ipoteca" : "Ipoteca");
			mortgageButton.setOnAction(event -> {
				if (contract.isMortgaged())
				{
					ClientMain.getConnection().send(ClientMessages.UNMORTGAGE_CONTRACT_MESSAGE_NAME, Serializer
							.toJson(new UnmortgageContractClientMessage(Game.getGameCode(), contract)));
				}
				else
				{
					ClientMain.getConnection().send(ClientMessages.MORTGAGE_CONTRACT_MESSAGE_NAME, Serializer
							.toJson(new MortgageContractClientMessage(Game.getGameCode(), contract)));
				}
				SceneManager.getInstance().getStageByType(SceneType.PROPERTY_VIEW).close();
			});

			buyHouseButton.setTooltip(new Tooltip("Aumentate la rendita del terreno acquistando una casa"));
			buyHouseButton.setOnAction(event -> {
				if (Game.getPlayer().hasMoney(contract.getHouseCost()) && contract.getNumberOfHouses() < 5)
				{
					ClientMain.getConnection().send(ClientMessages.BUY_HOUSES_MESSAGE_NAME, Serializer
							.toJson(new BuyHousesClientMessage(Game.getGameCode(), contract, 1)));
				}
			});

			sellHouseButton.setTooltip(new Tooltip("Vendete una casa (la rendita diminuira')"));
			sellHouseButton.setOnAction(event -> {
				if (contract.getNumberOfHouses() > 0)
				{
					ClientMain.getConnection().send(ClientMessages.SELL_HOUSES_MESSAGE_NAME, Serializer
							.toJson(new SellHousesClientMessage(Game.getGameCode(), contract, 1)));
				}
			});
		}
		else
		{
			onlyIfOwnedPane1.setVisible(false);
			onlyIfOwnedPane1.setDisable(true);
			onlyIfOwnedPane2.setVisible(false);
			onlyIfOwnedPane2.setDisable(false);
		}
	}
}
