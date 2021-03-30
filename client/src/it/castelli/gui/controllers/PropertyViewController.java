package it.castelli.gui.controllers;

import it.castelli.gameLogic.contracts.PropertyContract;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PropertyViewController
{
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

	public void setContract(PropertyContract contract)
	{
		switch (contract.getColor())
		{
			case BROWN -> titleLabel.setStyle("-fx-background-color: #965336;");
			case LIGHT_BLUE -> titleLabel.setStyle("-fx-background-color: #ace2fc;");
			case MAGENTA -> titleLabel.setStyle("-fx-background-color: #dd3997;");
			case ORANGE -> titleLabel.setStyle("-fx-background-color: #f9951c;");
			case RED -> titleLabel.setStyle("-fx-background-color: #ee1b23;");
			case YELLOW -> titleLabel.setStyle("-fx-background-color: #ffef06;");
			case GREEN -> titleLabel.setStyle("-fx-background-color: #1fb25a;");
			case BLUE -> titleLabel.setStyle("-fx-background-color: #0072bd;");
		}


		titleLabel.setText(contract.getName());
		revenueLabel.setText(String.valueOf(contract.getRevenues()[0]));
		revenue1houseLabel.setText(String.valueOf(contract.getRevenues()[1]));
		revenue2housesLabel.setText(String.valueOf(contract.getRevenues()[2]));
		revenue3housesLabel.setText(String.valueOf(contract.getRevenues()[3]));
		revenue4housesLabel.setText(String.valueOf(contract.getRevenues()[4]));
		revenueHotelLabel.setText(String.valueOf(contract.getRevenues()[5]));
		houseCostLabel.setText(String.valueOf(contract.getHouseCost()));
	}
}
