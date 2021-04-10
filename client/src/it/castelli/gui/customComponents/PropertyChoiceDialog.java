package it.castelli.gui.customComponents;

import it.castelli.gameLogic.contracts.Contract;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;

public class PropertyChoiceDialog extends Dialog<Contract>
{
	public PropertyChoiceDialog()
	{
		super();

		AnchorPane root = new AnchorPane();

		getDialogPane().setContent(root);
	}
}
