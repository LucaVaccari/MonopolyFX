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
import javafx.scene.layout.HBox;

/**
 * Controller for stationView FXML
 */
public class StationViewController
{
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
	 * Update the visuals of the StationView.
	 *
	 * @param contract The contract of the station
	 */
	public void setContract(StationContract contract)
	{
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
			onlyIfOwnedPane.setVisible(isOwnedByMe && isMyRound);
			onlyIfOwnedPane.setDisable(!isOwnedByMe && !isMyRound);
		}
		else
		{
			onlyIfOwnedPane.setVisible(false);
			onlyIfOwnedPane.setDisable(true);
		}

		sellButton.setOnAction(event -> {
			ClientMain.getConnection().send(ClientMessages.SELL_CONTRACT_MESSAGE_NAME, Serializer
					.toJson(new SellContractClientMessage(Game.getGameCode(), contract)));
			SceneManager.getInstance().getStageByType(SceneType.STATION_VIEW).close();
		});

		mortgageButton.setText(contract.isMortgaged() ? "Sciogli ipoteca" : "Ipoteca");
		mortgageButton.setOnAction(event -> {
			if (contract.isMortgaged())
				ClientMain.getConnection().send(ClientMessages.UNMORTGAGE_CONTRACT_MESSAGE_NAME, Serializer
						.toJson(new UnmortgageContractClientMessage(Game.getGameCode(), contract)));
			else
				ClientMain.getConnection().send(ClientMessages.MORTGAGE_CONTRACT_MESSAGE_NAME, Serializer
						.toJson(new MortgageContractClientMessage(Game.getGameCode(), contract)));
			SceneManager.getInstance().getStageByType(SceneType.STATION_VIEW).close();
		});
	}
}
