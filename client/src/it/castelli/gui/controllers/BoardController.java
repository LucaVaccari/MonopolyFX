package it.castelli.gui.controllers;

import it.castelli.Game;
import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gui.FXMLFileLoader;
import it.castelli.gui.customComponents.SquareComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * FXML controller for the board scene, the main one of the game
 */
public class BoardController
{

	public static final String PROPERTY_VIEW_FXML_PATH = "/FXMLs/propertyView.fxml";
	public static final String STATION_VIEW_FXML_PATH = "/FXMLs/stationView.fxml";
	public static final String COMPANY_VIEW_FXML_PATH = "/FXMLs/companyView.fxml";

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
	private SquareComponent piazzaUniversitaSquare;
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
		// PROPERTIES
		vicoloCortoSquare
				.setContract(Game.getGameManager().getSquare(1).getContract());
		vicoloCortoSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) vicoloCortoSquare.getContract()));

		vicoloStrettoSquare
				.setContract(Game.getGameManager().getSquare(3).getContract());
		vicoloStrettoSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) vicoloStrettoSquare.getContract()));

		bastioniGranSassoSquare
				.setContract(Game.getGameManager().getSquare(6).getContract());
		bastioniGranSassoSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) bastioniGranSassoSquare.getContract()));

		vialeMonterosaSquare
				.setContract(Game.getGameManager().getSquare(8).getContract());
		vialeMonterosaSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) vialeMonterosaSquare.getContract()));

		vialeVesuvioSquare
				.setContract(Game.getGameManager().getSquare(9).getContract());
		vialeVesuvioSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) vialeVesuvioSquare.getContract()));

		viaAccademiaSquare
				.setContract(Game.getGameManager().getSquare(11).getContract());
		viaAccademiaSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) viaAccademiaSquare.getContract()));

		corsoAteneoSquare
				.setContract(Game.getGameManager().getSquare(13).getContract());
		corsoAteneoSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) corsoAteneoSquare.getContract()));

		piazzaUniversitaSquare
				.setContract(Game.getGameManager().getSquare(14).getContract());
		piazzaUniversitaSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) piazzaUniversitaSquare.getContract()));

		viaVerdiSquare
				.setContract(Game.getGameManager().getSquare(16).getContract());
		viaVerdiSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) viaVerdiSquare.getContract()));

		corsoRaffaelloSquare
				.setContract(Game.getGameManager().getSquare(18).getContract());
		corsoRaffaelloSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) corsoRaffaelloSquare.getContract()));

		piazzaDanteSquare
				.setContract(Game.getGameManager().getSquare(19).getContract());
		piazzaDanteSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) piazzaDanteSquare.getContract()));

		viaMarcoPoloSquare
				.setContract(Game.getGameManager().getSquare(21).getContract());
		viaMarcoPoloSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) viaMarcoPoloSquare.getContract()));

		corsoMagellanoSquare
				.setContract(Game.getGameManager().getSquare(23).getContract());
		corsoMagellanoSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) corsoMagellanoSquare.getContract()));

		largoColomboSquare
				.setContract(Game.getGameManager().getSquare(24).getContract());
		largoColomboSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) largoColomboSquare.getContract()));

		vialeCostantinoSquare
				.setContract(Game.getGameManager().getSquare(26).getContract());
		vialeCostantinoSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) vialeCostantinoSquare.getContract()));

		vialeTraianoSquare
				.setContract(Game.getGameManager().getSquare(27).getContract());
		vialeTraianoSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) vialeTraianoSquare.getContract()));

		piazzaGiulioCesareSquare
				.setContract(Game.getGameManager().getSquare(29).getContract());
		piazzaGiulioCesareSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) piazzaGiulioCesareSquare.getContract()));

		viaRomaSquare
				.setContract(Game.getGameManager().getSquare(31).getContract());
		viaRomaSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) viaRomaSquare.getContract()));

		corsoImperoSquare
				.setContract(Game.getGameManager().getSquare(32).getContract());
		corsoImperoSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) corsoImperoSquare.getContract()));

		largoAugustoSquare
				.setContract(Game.getGameManager().getSquare(34).getContract());
		largoAugustoSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) largoAugustoSquare.getContract()));

		vialeDeiGiardiniSquare
				.setContract(Game.getGameManager().getSquare(37).getContract());
		vialeDeiGiardiniSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) vialeDeiGiardiniSquare.getContract()));

		parcoDellaVittoriaSquare
				.setContract(Game.getGameManager().getSquare(39).getContract());
		parcoDellaVittoriaSquare.setOnMouseClicked(event -> showPropertyView(
				(PropertyContract) parcoDellaVittoriaSquare.getContract()));

		// STATIONS
		southStationSquare.setContract(Game.getGameManager().getSquare(5).getContract());
		southStationSquare
				.setOnMouseClicked(event ->
						showStationView((StationContract) southStationSquare.getContract()));

		westStationSquare.setContract(Game.getGameManager().getSquare(15).getContract());
		westStationSquare
				.setOnMouseClicked(event ->
						showStationView((StationContract) westStationSquare.getContract()));

		northStationSquare.setContract(Game.getGameManager().getSquare(25).getContract());
		northStationSquare
				.setOnMouseClicked(event ->
						showStationView((StationContract) northStationSquare.getContract()));

		eastStationSquare.setContract(Game.getGameManager().getSquare(35).getContract());
		eastStationSquare
				.setOnMouseClicked(event ->
						showStationView((StationContract) eastStationSquare.getContract()));

		// COMPANIES
		electricSocietySquare.setContract(Game.getGameManager().getSquare(12).getContract());
		electricSocietySquare
				.setOnMouseClicked(event -> showCompanyView(CompanyViewController.Company.ELECTRIC,
						(CompanyContract) electricSocietySquare.getContract()));

		waterWorksSquare.setContract(Game.getGameManager().getSquare(28).getContract());
		waterWorksSquare
				.setOnMouseClicked(event -> showCompanyView(CompanyViewController.Company.WATER,
						(CompanyContract) waterWorksSquare.getContract()));
	}

	/**
	 * Show a new not resizable stage containing information about a property
	 *
	 * @param contract The contract of the property to show
	 */
	private void showPropertyView(PropertyContract contract)
	{
		if (contract == null)
			return;

		try
		{
			FXMLLoader loader =
					FXMLFileLoader.getLoader(PROPERTY_VIEW_FXML_PATH);
			Parent root = loader.load();
			PropertyViewController controller = loader.getController();
			controller.setContract(contract);
			Stage propertyViewStage = new Stage();
			Scene scene = new Scene(root);
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

	/**
	 * Show a new not resizable stage containing information about a station
	 *
	 * @param contract The contract of the station to show
	 */
	private void showStationView(StationContract contract)
	{
		if (contract == null)
			return;

		try
		{
			FXMLLoader loader = FXMLFileLoader.getLoader(STATION_VIEW_FXML_PATH);
			Parent root = loader.load();
			StationViewController controller = loader.getController();
			controller.setContract(contract);
			Stage propertyViewStage = new Stage();
			Scene scene = new Scene(root);
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

	/**
	 * Show a new not resizable stage containing information about a company
	 *
	 * @param contract The contract of the company to show
	 */
	private void showCompanyView(CompanyViewController.Company company, CompanyContract contract)
	{
		if (contract == null)
			return;

		try
		{
			FXMLLoader loader = FXMLFileLoader.getLoader(COMPANY_VIEW_FXML_PATH);
			Parent root = loader.load();
			CompanyViewController controller = loader.getController();
			controller.setContract(company, contract);
			Stage propertyViewStage = new Stage();
			Scene scene = new Scene(root);
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
