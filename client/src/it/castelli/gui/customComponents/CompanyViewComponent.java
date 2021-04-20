package it.castelli.gui.customComponents;

import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gui.FXMLFileLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * GUI component containing a company view
 */
public class CompanyViewComponent extends AnchorPane
{
	public static String COMPANY_VIEW_FXML_PATH = "/FXMLs/companyViewComponent.fxml";


	@FXML
	private ImageView companyImage;
	@FXML
	private Label companyNameLabel;


	public CompanyViewComponent(CompanyContract contract)
	{
		super();
		FXMLLoader loader = FXMLFileLoader.getLoader(COMPANY_VIEW_FXML_PATH);
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
	public void setContract(CompanyContract contract)
	{
		switch (contract.getCompany())
		{
			case ELECTRIC -> companyImage.setImage(new Image("/images/squares/electric_society.png"));
			case WATER -> companyImage.setImage(new Image("/images/squares/water_works.png"));
		}

		companyNameLabel.setText(contract.getName());


	}


}
