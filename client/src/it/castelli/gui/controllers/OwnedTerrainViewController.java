package it.castelli.gui.controllers;

import it.castelli.Game;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.customComponents.SmallTerrainViewComponent;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

/**
 * Controller for ownedTerrainView FXML
 */
public class OwnedTerrainViewController
{
	/**
	 * Singleton instance
	 */
	private static OwnedTerrainViewController instance;
	@FXML
	private FlowPane ownedTerrainViewFlowPane;

	public static OwnedTerrainViewController getInstance()
	{
		return instance;
	}

	@FXML
	private void initialize()
	{
		instance = this;
	}

	public void update()
	{
		ownedTerrainViewFlowPane.getChildren().clear();
		for (Contract contract : Game.getPlayer().getContracts())
		{
			ownedTerrainViewFlowPane.getChildren().add(new SmallTerrainViewComponent(contract));
		}
	}
}
