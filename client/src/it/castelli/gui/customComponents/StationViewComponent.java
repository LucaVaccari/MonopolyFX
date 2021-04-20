package it.castelli.gui.customComponents;

import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gui.FXMLFileLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * GUI component containing a station view
 */
public class StationViewComponent extends AnchorPane
{
	public static String STATION_VIEW_FXML_PATH = "/FXMLs/stationViewComponent.fxml";

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

	public StationViewComponent(StationContract contract)
	{
		super();
		FXMLLoader loader = FXMLFileLoader.getLoader(STATION_VIEW_FXML_PATH);
		loader.setRoot(this);
		loader.setController(this);
		try
		{
			loader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		setContract(contract);
	}

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

	}
}
