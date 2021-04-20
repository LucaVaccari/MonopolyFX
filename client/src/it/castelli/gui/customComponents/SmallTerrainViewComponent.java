package it.castelli.gui.customComponents;

import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gui.FXMLFileLoader;
import it.castelli.gui.GUIUtils;
import it.castelli.gui.scene.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * GUI component containing a small terrain view
 */
public class SmallTerrainViewComponent extends AnchorPane
{
	public static String SMALL_TERRAIN_VIEW_FXML_PATH = "/FXMLs/smallTerrainView.fxml";

	@FXML
	private Label terrainNameLabel;
	@FXML
	private Label terrainValueLabel;

	/**
	 * Constructor of SmallTerrainViewComponent
	 */
	public SmallTerrainViewComponent(Contract contract)
	{
		super();
		FXMLLoader loader = FXMLFileLoader.getLoader(SMALL_TERRAIN_VIEW_FXML_PATH);
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
	 * Setter for contract of terrain
	 *
	 * @param contract the contract of the terrain
	 */
	public void setContract(Contract contract)
	{
		if (contract == null)
			return;

		Tooltip.install(this, new Tooltip(contract.getName()));

		terrainNameLabel.setText(contract.getName());
		terrainValueLabel.setText(String.valueOf(contract.getValue()));

		if (contract instanceof PropertyContract)
		{
			terrainNameLabel.setStyle("-fx-background-color: " + GUIUtils.getPropertyColorsCodes()
					.get(((PropertyContract) contract).getColor()));
		}
		else
			terrainNameLabel.setStyle("-fx-background-color: #ffffff"); // set to white if station or company

		if (contract instanceof PropertyContract)
			setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView((PropertyContract) contract));
		else if (contract instanceof StationContract)
			setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView((StationContract) contract));
		else if (contract instanceof CompanyContract)
			setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView((CompanyContract) contract));
	}
}
