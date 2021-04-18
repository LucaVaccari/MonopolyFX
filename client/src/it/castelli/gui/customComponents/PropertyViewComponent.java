package it.castelli.gui.customComponents;

import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gui.FXMLFileLoader;
import it.castelli.gui.GUIUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PropertyViewComponent extends AnchorPane
{
	public static String PROPERTY_VIEW_FXML_PATH = "/FXMLs/propertyViewComponent.fxml";

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

	public PropertyViewComponent(PropertyContract contract)
	{
		super();
		FXMLLoader loader = FXMLFileLoader.getLoader(PROPERTY_VIEW_FXML_PATH);
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


	}


}
