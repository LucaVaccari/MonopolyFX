package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.MortgageContractClientMessage;
import it.castelli.connection.messages.SellContractClientMessage;
import it.castelli.connection.messages.UnmortgageContractClientMessage;
import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

/**
 * Controller for stationView FXML
 */
public class StationViewController
{
	/**
	 * The id of the contract
	 */
	private int contractId;

	@FXML
	private Label titleLabel;
	@FXML
	private Label valueLabel;
	@FXML
	private Label revenue1StationLabel;
	@FXML
	private Label revenue2StationsLabel;
	@FXML
	private Label revenue3StationsLabel;
	@FXML
	private Label revenue4StationsLabel;
	@FXML
	private Label mortgageValue;
	@FXML
	private HBox onlyIfOwnedPane;
	@FXML
	private Button sellButton;
	@FXML
	private Button mortgageButton;

	/**
	 * Set the contract and update the visuals of the StationView.
	 *
	 * @param id The id of the contract of the station
	 */
	public void setContract(int id)
	{
		contractId = id;
		update();
	}

	/**
	 * Update the visual information
	 */
	public void update()
	{
		StationContract contract = (StationContract) Game.getGameManager().getContract(contractId);

		titleLabel.setText(contract.getName());
		valueLabel.setText(String.valueOf(contract.getValue()));
		revenue1StationLabel.setText(
				String.valueOf(contract.getRevenueFromNumberOfStations(1)));
		revenue2StationsLabel.setText(
				String.valueOf(contract.getRevenueFromNumberOfStations(2)));
		revenue3StationsLabel.setText(
				String.valueOf(contract.getRevenueFromNumberOfStations(3)));
		revenue4StationsLabel.setText(
				String.valueOf(contract.getRevenueFromNumberOfStations(4)));
		mortgageValue.setText(String.valueOf(contract.getValue() / 2));

		if (contract.getOwner() != null)
		{
			boolean isOwnedByMe = contract.getOwner().toPlayer().betterEquals(Game.getPlayer());
			boolean isMyRound = contract.getOwner().toPlayer()
					.betterEquals(Game.getGameManager().getCurrentRound().getCurrentActivePlayer());
			onlyIfOwnedPane.setVisible(isOwnedByMe);
			onlyIfOwnedPane.setDisable(!isOwnedByMe && !isMyRound);
		}
		else
		{
			onlyIfOwnedPane.setVisible(false);
			onlyIfOwnedPane.setDisable(true);
		}

		sellButton.setTooltip(new Tooltip("Vendete questa stazione"));
		sellButton.setOnAction(event -> {
			ClientMain.getConnection().send(ClientMessages.SELL_CONTRACT_MESSAGE_NAME, Serializer
					.toJson(new SellContractClientMessage(Game.getGameCode(), contract)));
			SceneManager.getInstance().getStageByType(SceneType.STATION_VIEW).close();
		});

		if (contract.isMortgaged())
			mortgageButton.setDisable(!Game.getPlayer().hasMoney(contract.getMortgageValue() * 11 / 10));

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
			SceneManager.getInstance().getStageByType(SceneType.STATION_VIEW).close();
		});
	}
}
