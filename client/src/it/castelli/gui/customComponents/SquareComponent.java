package it.castelli.gui.customComponents;

import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.FXMLFileLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * GUI component containing a Square of the board
 */
public class SquareComponent extends ImageView
{
	public static final String SQUARE_FXML_PATH = "/FXMLs/square.fxml";
	/**
	 * The contract bound to the square (if the square has non contract, this is null)
	 */
	private Contract contract;

	/**
	 * Constructor of SquareComponent
	 */
	public SquareComponent()
	{
		super();
		FXMLLoader loader = FXMLFileLoader.getLoader(SQUARE_FXML_PATH);
		loader.setController(this);
		loader.setRoot(this);
		try
		{
			loader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Getter for contract
	 *
	 * @return The contract bound to the square (if the square has non contract, this is null)
	 */
	public Contract getContract()
	{
		return contract;
	}

	/**
	 * Setter for contract
	 *
	 * @param contract The contract bound to the square (if the square has non contract, this is null)
	 */
	public void setContract(Contract contract)
	{
		this.contract = contract;
	}
}
