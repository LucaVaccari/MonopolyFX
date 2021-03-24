package it.castelli.gui.controllers;

import it.castelli.Game;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gui.FXMLFileLoader;
import it.castelli.gui.customComponents.SquareComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BoardController
{

	public static final String PROPERTY_VIEW_FXML_PATH = "/FXMLs/propertyView.fxml";

	@FXML
	private SquareComponent goSquare;
	@FXML
	private SquareComponent vicoloCortoSquare;
	@FXML
	private SquareComponent communityChestSquare1;
	@FXML
	private SquareComponent vicoloStrettoSquare;
	@FXML
	private SquareComponent patrimonialTaxSquare;
	@FXML
	private SquareComponent southStationSquare;
	@FXML
	private SquareComponent bastioniGranSassoSquare;
	@FXML
	private SquareComponent chanceSquare1;
	@FXML
	private SquareComponent vialeMonterosaSquare;
	@FXML
	private SquareComponent vialeVesuvioSquare;
	@FXML
	private SquareComponent justVisitingSquare;
	@FXML
	private SquareComponent viaAccademiaSquare;
	@FXML
	private SquareComponent electricSocietySquare;
	@FXML
	private SquareComponent corsoAteneoSquare;
	@FXML
	private SquareComponent piazzaUnivertisaSquare;
	@FXML
	private SquareComponent westStationSquare;
	@FXML
	private SquareComponent viaVerdiSquare;
	@FXML
	private SquareComponent communityChestSquare2;
	@FXML
	private SquareComponent corsoRaffaelloSquare;
	@FXML
	private SquareComponent piazzaDanteSquare;
	@FXML
	private SquareComponent freeParkingSquare;
	@FXML
	private SquareComponent viaMarcoPoloSquare;
	@FXML
	private SquareComponent chanceSquare2;
	@FXML
	private SquareComponent corsoMagellanoSquare;
	@FXML
	private SquareComponent largoColomboSquare;
	@FXML
	private SquareComponent northStationSquare;
	@FXML
	private SquareComponent vialeCostantinoSquare;
	@FXML
	private SquareComponent vialeTraianoSquare;
	@FXML
	private SquareComponent waterWorksSquare;
	@FXML
	private SquareComponent piazzaGiulioCesareSquare;
	@FXML
	private SquareComponent goToJailSquare;
	@FXML
	private SquareComponent viaRomaSquare;
	@FXML
	private SquareComponent corsoImperoSquare;
	@FXML
	private SquareComponent communityChestSquare3;
	@FXML
	private SquareComponent largoAugustoSquare;
	@FXML
	private SquareComponent eastStationSquare;
	@FXML
	private SquareComponent chanceSquare3;
	@FXML
	private SquareComponent vialeDeiGiardiniSquare;
	@FXML
	private SquareComponent luxuryTaxSquare;
	@FXML
	private SquareComponent parcoDellaVittoriaSquare;

	@FXML
	private void initialize()
	{
		// TODO: show station view
		// TODO: show society view

		// PROPERTIES
		vicoloCortoSquare.setContract(Game.getGameManager().getSquare(1).getContract());
		vicoloCortoSquare.setOnMouseClicked(event -> showPropertyView((PropertyContract) vicoloCortoSquare.getContract()));

		vicoloStrettoSquare.setContract(Game.getGameManager().getSquare(3).getContract());
		vicoloStrettoSquare.setOnMouseClicked(event -> showPropertyView((PropertyContract) vicoloStrettoSquare.getContract()));

		bastioniGranSassoSquare.setContract(Game.getGameManager().getSquare(6).getContract());
		bastioniGranSassoSquare.setOnMouseClicked(event -> showPropertyView((PropertyContract) bastioniGranSassoSquare.getContract()));

		vialeMonterosaSquare.setContract(Game.getGameManager().getSquare(8).getContract());
		vialeMonterosaSquare.setOnMouseClicked(event -> showPropertyView((PropertyContract) vialeMonterosaSquare.getContract()));

		vialeVesuvioSquare.setContract(Game.getGameManager().getSquare(9).getContract());
		vialeVesuvioSquare.setOnMouseClicked(event -> showPropertyView((PropertyContract) vialeVesuvioSquare.getContract()));
	}

	/**
	 * Show a new not resizable stage containing information about a property
	 * @param contract The contract of the property to show
	 */
	private void showPropertyView(PropertyContract contract)
	{
		FXMLLoader loader = FXMLFileLoader.getLoader(PROPERTY_VIEW_FXML_PATH);
		PropertyViewController controller = loader.getController();
		controller.setContract(contract);
		try
		{
			Stage propertyViewStage = new Stage();
			Scene scene = new Scene(loader.load());
			propertyViewStage.setScene(scene);
			propertyViewStage.initModality(Modality.APPLICATION_MODAL);
			propertyViewStage.setAlwaysOnTop(true);
			propertyViewStage.setResizable(false);
			propertyViewStage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
