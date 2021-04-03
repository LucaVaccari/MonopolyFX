package it.castelli.gui.controllers;

import it.castelli.gameLogic.contracts.CompanyContract;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller for companyView FXML
 */
public class CompanyViewController
{
	@FXML
	private ImageView companyImage;
	@FXML
	private Label companyNameLabel;

	/**
	 * Update the visuals of the CompanyView.
	 *
	 * @param contract The contract of the company
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
