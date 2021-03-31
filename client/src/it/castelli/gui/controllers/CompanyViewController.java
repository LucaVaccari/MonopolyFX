package it.castelli.gui.controllers;

import it.castelli.gameLogic.contracts.CompanyContract;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CompanyViewController
{
	/**
	 * Enumeration of companies: Electric company and Water works
	 */
	public enum Company { ELECTRIC, WATER }

	@FXML
	private ImageView companyImage;
	@FXML
	private Label companyNameLabel;

	/**
	 * Update the visuals of the CompanyView.
	 * @param company The company bound to the contract
	 * @param contract The contract of the company
	 */
	public void setContract(Company company, CompanyContract contract)
	{
		switch (company)
		{
			case ELECTRIC -> companyImage.setImage(new Image("/images/squares/electric_society.png"));
			case WATER -> companyImage.setImage(new Image("/images/squares/water_works.png"));
		}

		companyNameLabel.setText(contract.getName());
	}
}
