package it.castelli.gui.customComponents;

import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gui.FXMLFileLoader;
import it.castelli.gui.GUIUtils;
import it.castelli.gui.controllers.BoardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SmallTerrainViewComponent extends AnchorPane
{
	public static String SMALL_TERRAIN_VIEW_FXML_PATH = "/FXMLs/smallTerrainView.fxml";

	@FXML
	private Label terrainNameLabel;
	@FXML
	private Label terrainValueLabel;

	public SmallTerrainViewComponent()
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
	}

	public void setContract(Contract contract)
	{
		terrainNameLabel.setText(contract.getName());
		terrainValueLabel.setText(String.valueOf(contract.getRevenue()));

		if (contract instanceof PropertyContract)
			terrainNameLabel.setStyle("-fx-background-color: " + GUIUtils.getPropertyColorsCodes()
					.get(((PropertyContract) contract).getColor()));
		else
			terrainNameLabel.setStyle("-fx-background-color: #ffffff"); // set to white if station or company

		if (contract instanceof PropertyContract)
			setOnMouseClicked(event -> BoardController.getInstance().showTerrainView((PropertyContract) contract));
		else if (contract instanceof StationContract)
			setOnMouseClicked(event -> BoardController.getInstance().showTerrainView((StationContract) contract));
		else if (contract instanceof CompanyContract)
			setOnMouseClicked(event -> BoardController.getInstance().showTerrainView((CompanyContract) contract));
	}
}
