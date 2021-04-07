package it.castelli.gui.controllers;

import it.castelli.gameLogic.contracts.StationContract;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
	}
}
