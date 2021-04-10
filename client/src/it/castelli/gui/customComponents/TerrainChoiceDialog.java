package it.castelli.gui.customComponents;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.scene.SceneManager;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class TerrainChoiceDialog extends Dialog<Contract>
{
	public TerrainChoiceDialog(Player player)
	{
		super();
		initOwner(SceneManager.getInstance().getPrimaryStage());

		FlowPane flowPane = new FlowPane();
		ScrollPane scrollPane = new ScrollPane(flowPane);
		AnchorPane root = new AnchorPane(scrollPane);

		root.setPrefSize(500, 300);

		AnchorPane.setBottomAnchor(scrollPane, 10.0);
		AnchorPane.setLeftAnchor(scrollPane, 10.0);
		AnchorPane.setTopAnchor(scrollPane, 10.0);
		AnchorPane.setRightAnchor(scrollPane, 10.0);


		for (Contract contract : player.getContracts())
		{
			SmallTerrainViewComponent terrainView = new SmallTerrainViewComponent(contract);
			terrainView.setOnMouseClicked(event -> {
				super.setResult(contract);
				super.close();
			});
			flowPane.getChildren().add(terrainView);
		}

		getDialogPane().setContent(root);

		getDialogPane().getButtonTypes().add(ButtonType.APPLY);
		Node closeButton = getDialogPane().lookupButton(ButtonType.APPLY);
		closeButton.managedProperty().bind(closeButton.visibleProperty());
		closeButton.setVisible(false);
	}
}
