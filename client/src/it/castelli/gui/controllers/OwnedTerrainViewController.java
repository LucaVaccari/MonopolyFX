package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.StartGameClientMessage;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.FXMLFileLoader;
import it.castelli.gui.customComponents.SmallTerrainViewComponent;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	@FXML
	private void initialize()
	{
		instance = this;
	}

	public void update()
	{
		ownedTerrainViewFlowPane.getChildren().clear();
		for (Contract contract : Game.getPlayer().getContracts())
			ownedTerrainViewFlowPane.getChildren().add(new SmallTerrainViewComponent(contract));
	}
	public static OwnedTerrainViewController getInstance()
	{
		return instance;
	}
}
