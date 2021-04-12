package it.castelli.gui.controllers;

import it.castelli.Game;
import it.castelli.gameLogic.contracts.StationContract;
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

		onlyIfOwnedPane.setVisible(contract.getOwner().toPlayer().betterEquals(Game.getPlayer()));
		onlyIfOwnedPane.setDisable(!contract.getOwner().toPlayer().betterEquals(Game.getPlayer()));

		sellButton.setOnAction(event -> {
			// TODO: sell property
		});

		mortgageButton.setOnAction(event -> {
			// TODO: mortgage property
		});
	}
}
