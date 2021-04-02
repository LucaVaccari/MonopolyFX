package it.castelli.gui.controllers;

import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gui.FXMLFileLoader;
import it.castelli.gui.customComponents.SmallTerrainViewComponent;
import it.castelli.gui.customComponents.SquareComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static it.castelli.Game.getGameManager;
import static it.castelli.Game.getPlayer;

/**
 * FXML controller for the board scene, the main one of the game
 */
public class BoardController
{

	public static final String PROPERTY_VIEW_FXML_PATH =
			"/FXMLs/propertyView.fxml";
	public static final String STATION_VIEW_FXML_PATH =
			"/FXMLs/stationView.fxml";
	public static final String COMPANY_VIEW_FXML_PATH =
			"/FXMLs/companyView.fxml";

	private static final int SHOWN_OWNED_PROPERTIES = 9;

	private static BoardController instance;

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
	private FlowPane ownedPropertiesPane;
	@FXML
	private Label moneyLabel;

	@FXML
	private void initialize()
	{
		// squares callbacks and contracts
		{
			// PROPERTIES
			vicoloCortoSquare.setContract(getGameManager().getSquare(1).getContract());
			vicoloCortoSquare
					.setOnMouseClicked(event -> showTerrainView((PropertyContract) vicoloCortoSquare.getContract()));

			vicoloStrettoSquare.setContract(getGameManager().getSquare(3).getContract());
			vicoloStrettoSquare
					.setOnMouseClicked(event -> showTerrainView((PropertyContract) vicoloStrettoSquare.getContract()));

			bastioniGranSassoSquare.setContract(getGameManager().getSquare(6).getContract());
			bastioniGranSassoSquare
					.setOnMouseClicked(event -> showTerrainView((PropertyContract) bastioniGranSassoSquare
							.getContract()));

			vialeMonterosaSquare.setContract(getGameManager().getSquare(8).getContract());
			vialeMonterosaSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeMonterosaSquare.getContract()));

			vialeVesuvioSquare.setContract(getGameManager().getSquare(9).getContract());
			vialeVesuvioSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeVesuvioSquare.getContract()));

			viaAccademiaSquare.setContract(getGameManager().getSquare(11).getContract());
			viaAccademiaSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) viaAccademiaSquare.getContract()));

			corsoAteneoSquare.setContract(getGameManager().getSquare(13).getContract());
			corsoAteneoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) corsoAteneoSquare.getContract()));

			piazzaUniversitaSquare
					.setContract(
							getGameManager().getSquare(14).getContract());
			piazzaUniversitaSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) piazzaUniversitaSquare.getContract()));

			viaVerdiSquare
					.setContract(
							getGameManager().getSquare(16).getContract());
			viaVerdiSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) viaVerdiSquare.getContract()));

			corsoRaffaelloSquare
					.setContract(
							getGameManager().getSquare(18).getContract());
			corsoRaffaelloSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) corsoRaffaelloSquare.getContract()));

			piazzaDanteSquare
					.setContract(
							getGameManager().getSquare(19).getContract());
			piazzaDanteSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) piazzaDanteSquare.getContract()));

			viaMarcoPoloSquare
					.setContract(
							getGameManager().getSquare(21).getContract());
			viaMarcoPoloSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) viaMarcoPoloSquare.getContract()));

			corsoMagellanoSquare
					.setContract(
							getGameManager().getSquare(23).getContract());
			corsoMagellanoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) corsoMagellanoSquare.getContract()));

			largoColomboSquare
					.setContract(
							getGameManager().getSquare(24).getContract());
			largoColomboSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) largoColomboSquare.getContract()));

			vialeCostantinoSquare
					.setContract(
							getGameManager().getSquare(26).getContract());
			vialeCostantinoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeCostantinoSquare.getContract()));

			vialeTraianoSquare
					.setContract(
							getGameManager().getSquare(27).getContract());
			vialeTraianoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeTraianoSquare.getContract()));

			piazzaGiulioCesareSquare
					.setContract(
							getGameManager().getSquare(29).getContract());
			piazzaGiulioCesareSquare
					.setOnMouseClicked(event -> showTerrainView(
							(PropertyContract) piazzaGiulioCesareSquare
									.getContract()));

			viaRomaSquare
					.setContract(
							getGameManager().getSquare(31).getContract());
			viaRomaSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) viaRomaSquare.getContract()));

			corsoImperoSquare
					.setContract(
							getGameManager().getSquare(32).getContract());
			corsoImperoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) corsoImperoSquare.getContract()));

			largoAugustoSquare
					.setContract(
							getGameManager().getSquare(34).getContract());
			largoAugustoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) largoAugustoSquare.getContract()));

			vialeDeiGiardiniSquare
					.setContract(
							getGameManager().getSquare(37).getContract());
			vialeDeiGiardiniSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeDeiGiardiniSquare.getContract()));

			parcoDellaVittoriaSquare
					.setContract(
							getGameManager().getSquare(39).getContract());
			parcoDellaVittoriaSquare
					.setOnMouseClicked(event -> showTerrainView(
							(PropertyContract) parcoDellaVittoriaSquare
									.getContract()));

			// STATIONS
			southStationSquare.setContract(getGameManager().getSquare(5).getContract());
			southStationSquare
					.setOnMouseClicked(event -> showTerrainView((StationContract) southStationSquare.getContract()));

			westStationSquare.setContract(getGameManager().getSquare(15).getContract());
			westStationSquare
					.setOnMouseClicked(event -> showTerrainView((StationContract) westStationSquare.getContract()));

			northStationSquare.setContract(getGameManager().getSquare(25).getContract());
			northStationSquare
					.setOnMouseClicked(event -> showTerrainView((StationContract) northStationSquare.getContract()));

			eastStationSquare.setContract(getGameManager().getSquare(35).getContract());
			eastStationSquare
					.setOnMouseClicked(event -> showTerrainView((StationContract) eastStationSquare.getContract()));

			// COMPANIES
			electricSocietySquare.setContract(getGameManager().getSquare(12).getContract());
			electricSocietySquare
					.setOnMouseClicked(event -> showTerrainView((CompanyContract) electricSocietySquare.getContract()));

			waterWorksSquare.setContract(getGameManager().getSquare(28).getContract());
			waterWorksSquare
					.setOnMouseClicked(event -> showTerrainView((CompanyContract) waterWorksSquare.getContract()));
		}

		// add SmallTerrainViewComponents to the owned properties
		Label showOtherProperties = (Label) ownedPropertiesPane.getChildren().get(0);
		ownedPropertiesPane.getChildren().clear();
		for (int i = 0; i < SHOWN_OWNED_PROPERTIES; i++)
		{
			SmallTerrainViewComponent terrainView = new SmallTerrainViewComponent();
			terrainView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			ownedPropertiesPane.getChildren().add(terrainView);
		}
		ownedPropertiesPane.getChildren().add(showOtherProperties);

		calculateOwnedProperties();
	}

	/**
	 * Show a new not resizable stage containing information about a property
	 *
	 * @param contract The contract of the property to show
	 */
	public void showTerrainView(PropertyContract contract)
	{
		if (contract == null)
			return;

		try
		{
			FXMLLoader loader = FXMLFileLoader.getLoader(PROPERTY_VIEW_FXML_PATH);
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
	public void showTerrainView(StationContract contract)
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
	public void showTerrainView(CompanyContract contract)
	{
		if (contract == null)
			return;

		try
		{
			FXMLLoader loader = FXMLFileLoader.getLoader(COMPANY_VIEW_FXML_PATH);
			Parent root = loader.load();
			CompanyViewController controller = loader.getController();
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
	// Calculate the properties owned by the player to show under the board

	private void calculateOwnedProperties()
	{
		SmallTerrainViewComponent[] ownedTerrains = new SmallTerrainViewComponent[SHOWN_OWNED_PROPERTIES];
		ArrayList<Contract> mostProductiveContracts = new ArrayList<>(SHOWN_OWNED_PROPERTIES);

		for (int i = 0; i < SHOWN_OWNED_PROPERTIES; i++)
		{
			Node terrainView = ownedPropertiesPane.getChildren().get(i);
			if (terrainView instanceof SmallTerrainViewComponent)
				ownedTerrains[i] = (SmallTerrainViewComponent) terrainView;
			else
				continue;

			if (getPlayer() == null)
			{
				ownedTerrains[i].setVisible(false);
				ownedTerrains[i].setDisable(true);
				continue;
			}

			Contract mostProductiveContract = getPlayer().getContracts().get(0);
			for (Contract contract : getPlayer().getContracts())
			{
				if (contract.getRevenue() > mostProductiveContract.getRevenue() &&
						!mostProductiveContracts.contains(mostProductiveContract))
					mostProductiveContract = contract;
			}

			if (mostProductiveContract != null)
			{
				ownedTerrains[i].setDisable(false);
				ownedTerrains[i].setVisible(true);
				ownedTerrains[i].setContract(mostProductiveContract);
			}
			else
			{
				ownedTerrains[i].setVisible(false);
				ownedTerrains[i].setDisable(true);
			}
			mostProductiveContracts.add(mostProductiveContract);
		}

		// TODO: when clicking "...", show a new window with all the properties
	}

	/**
	 * Update the label showing the money of the player
	 */
	public void updateMoneyLabel()
	{
		if (getPlayer() != null)
			moneyLabel.setText(getPlayer().getMoney() + "M");
	}

	public static BoardController getInstance()
	{
		return instance;
	}
}
