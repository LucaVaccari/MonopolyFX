package it.castelli.gui.customComponents;

import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.FXMLFileLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;

public class SquareComponent extends ImageView
{
	public static final String SQUARE_FXML_PATH = "/FXMLs/square.fxml";
	private Contract contract;

	public SquareComponent()
	{
		super();
		FXMLLoader loader = FXMLFileLoader.getLoader(SQUARE_FXML_PATH);
		loader.setController(this);
		loader.setRoot(this);
	}

	@FXML
	public void initialize()
	{

	}

	public Contract getContract()
	{
		return contract;
	}

	public void setContract(Contract contract)
	{
		this.contract = contract;
	}
}
