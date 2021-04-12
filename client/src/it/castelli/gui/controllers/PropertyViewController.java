package it.castelli.gui.controllers;

import it.castelli.Game;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gui.GUIUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Controller for propertyView FXML
 */
public class PropertyViewController
{
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
	private HBox onlyIfOwnedPane2;
	@FXML
	private Button sellButton;
	@FXML
	private Button mortgageButton;
	@FXML
	private Button buyHouseButton;

	/**
	 * Update the visuals of the PropertyView.
	 *
	 * @param contract The contract of the property
	 */
	public void setContract(PropertyContract contract)
	{
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

		onlyIfOwnedPane1.setVisible(contract.getOwner().toPlayer().betterEquals(Game.getPlayer()));
		onlyIfOwnedPane1.setDisable(!contract.getOwner().toPlayer().betterEquals(Game.getPlayer()));
		onlyIfOwnedPane2.setVisible(contract.getOwner().toPlayer().betterEquals(Game.getPlayer()));
		onlyIfOwnedPane2.setDisable(!contract.getOwner().toPlayer().betterEquals(Game.getPlayer()));

		sellButton.setOnAction(event -> {
			// TODO: sell property
		});

		mortgageButton.setOnAction(event -> {
			// TODO: mortgage property
		});

		buyHouseButton.setOnAction(event -> {
			// TODO. buy a house
		});
	}
}
