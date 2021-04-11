package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.EndRoundClientMessage;
import it.castelli.connection.messages.LeaveGameClientMessage;
import it.castelli.connection.messages.ThrowDiceClientMessage;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.FXMLFileLoader;
import it.castelli.gui.customComponents.ChatComponent;
import it.castelli.gui.customComponents.PlayerInfoComponent;
import it.castelli.gui.customComponents.SmallTerrainViewComponent;
import it.castelli.gui.customComponents.SquareComponent;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static it.castelli.Game.getGameManager;
import static it.castelli.Game.getPlayer;

/**
 * FXML controller for the board scene, the main one of the game
 */
public class BoardController
{
	public static final String PROPERTY_VIEW_FXML_PATH = "/FXMLs/propertyView.fxml";
	public static final String STATION_VIEW_FXML_PATH = "/FXMLs/stationView.fxml";
	public static final String COMPANY_VIEW_FXML_PATH = "/FXMLs/companyView.fxml";

	private static final int SHOWN_OWNED_PROPERTIES = 9;

	/**
	 * Singleton instance
	 */
	private static BoardController instance;


	@FXML
	private ImageView die1Image;
	@FXML
	private ImageView die2Image;
	@FXML
	private Button throwDiceButton;
	@FXML
	private VBox playerListView;
	@FXML
	private Button endTurnButton;
	@FXML
	private Button exchangeButton;
	@FXML
	private Button leaveGameButton;

	// CHAT
	@FXML
	private ChatComponent chat;

	// BOARD
	@FXML
	private Group goSquare;
	@FXML
	private Group vicoloCortoSquare;
	@FXML
	private Group communityChestSquare1;
	@FXML
	private Group vicoloStrettoSquare;
	@FXML
	private Group patrimonialTaxSquare;
	@FXML
	private Group southStationSquare;
	@FXML
	private Group bastioniGranSassoSquare;
	@FXML
	private Group chanceSquare1;
	@FXML
	private Group vialeMonterosaSquare;
	@FXML
	private Group vialeVesuvioSquare;
	@FXML
	private Group justVisitingSquare;
	@FXML
	private Group viaAccademiaSquare;
	@FXML
	private Group electricSocietySquare;
	@FXML
	private Group corsoAteneoSquare;
	@FXML
	private Group piazzaUniversitaSquare;
	@FXML
	private Group westStationSquare;
	@FXML
	private Group viaVerdiSquare;
	@FXML
	private Group communityChestSquare2;
	@FXML
	private Group corsoRaffaelloSquare;
	@FXML
	private Group piazzaDanteSquare;
	@FXML
	private Group freeParkingSquare;
	@FXML
	private Group viaMarcoPoloSquare;
	@FXML
	private Group chanceSquare2;
	@FXML
	private Group corsoMagellanoSquare;
	@FXML
	private Group largoColomboSquare;
	@FXML
	private Group northStationSquare;
	@FXML
	private Group vialeCostantinoSquare;
	@FXML
	private Group vialeTraianoSquare;
	@FXML
	private Group waterWorksSquare;
	@FXML
	private Group piazzaGiulioCesareSquare;
	@FXML
	private Group goToJailSquare;
	@FXML
	private Group viaRomaSquare;
	@FXML
	private Group corsoImperoSquare;
	@FXML
	private Group communityChestSquare3;
	@FXML
	private Group largoAugustoSquare;
	@FXML
	private Group eastStationSquare;
	@FXML
	private Group chanceSquare3;
	@FXML
	private Group vialeDeiGiardiniSquare;
	@FXML
	private Group luxuryTaxSquare;
	@FXML
	private Group parcoDellaVittoriaSquare;

	// player pawns
	private HashMap<Player, ImageView> playerPawns;

	@FXML
	private FlowPane ownedPropertiesPane;
	@FXML
	private Label moneyLabel;

	public static BoardController getInstance()
	{
		return instance;
	}

	@FXML
	private void initialize()
	{
		instance = this;

		// squares callbacks and contracts
		{
			// PROPERTIES
			SquareComponent vicoloCortoSquareComponent = (SquareComponent) vicoloCortoSquare.getChildren().get(0);
			vicoloCortoSquareComponent.setContract(getGameManager().getSquare(1).getContract());
			vicoloCortoSquare.setOnMouseClicked(
					event -> showTerrainView((PropertyContract) vicoloCortoSquareComponent.getContract()));

			SquareComponent vicoloStrettoSquareComponent = (SquareComponent) vicoloStrettoSquare.getChildren().get(0);
			vicoloStrettoSquareComponent.setContract(getGameManager().getSquare(3).getContract());
			vicoloStrettoSquare
					.setOnMouseClicked(
							event -> showTerrainView((PropertyContract) vicoloStrettoSquareComponent.getContract()));

			SquareComponent bastioniGranSassoSquareComponent =
					(SquareComponent) bastioniGranSassoSquare.getChildren().get(0);
			bastioniGranSassoSquareComponent.setContract(getGameManager().getSquare(6).getContract());
			bastioniGranSassoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) bastioniGranSassoSquareComponent.getContract()));

			SquareComponent vialeMonterosaSquareComponent =
					(SquareComponent) vialeMonterosaSquare.getChildren().get(0);
			vialeMonterosaSquareComponent.setContract(getGameManager().getSquare(8).getContract());
			vialeMonterosaSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeMonterosaSquareComponent.getContract()));

			SquareComponent vialeVesuvioSquareComponent = (SquareComponent) vialeVesuvioSquare.getChildren().get(0);
			vialeVesuvioSquareComponent.setContract(getGameManager().getSquare(9).getContract());
			vialeVesuvioSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeVesuvioSquareComponent.getContract()));

			SquareComponent viaAccademiaSquareComponent = (SquareComponent) viaAccademiaSquare.getChildren().get(0);
			viaAccademiaSquareComponent.setContract(getGameManager().getSquare(11).getContract());
			viaAccademiaSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) viaAccademiaSquareComponent.getContract()));

			SquareComponent corsoAteneoSquareComponent = (SquareComponent) corsoAteneoSquare.getChildren().get(0);
			corsoAteneoSquareComponent.setContract(getGameManager().getSquare(13).getContract());
			corsoAteneoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) corsoAteneoSquareComponent.getContract()));

			SquareComponent piazzaUniversitaSquareComponent =
					(SquareComponent) piazzaUniversitaSquare.getChildren().get(0);
			piazzaUniversitaSquareComponent.setContract(getGameManager().getSquare(14).getContract());
			piazzaUniversitaSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) piazzaUniversitaSquareComponent.getContract()));

			SquareComponent viaVerdiSquareComponent = (SquareComponent) viaVerdiSquare.getChildren().get(0);
			viaVerdiSquareComponent.setContract(getGameManager().getSquare(16).getContract());
			viaVerdiSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) viaVerdiSquareComponent.getContract()));

			SquareComponent corsoRaffaelloSquareComponent =
					(SquareComponent) corsoRaffaelloSquare.getChildren().get(0);
			corsoRaffaelloSquareComponent.setContract(getGameManager().getSquare(18).getContract());
			corsoRaffaelloSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) corsoRaffaelloSquareComponent.getContract()));

			SquareComponent piazzaDanteSquareComponent = (SquareComponent) piazzaDanteSquare.getChildren().get(0);
			piazzaDanteSquareComponent.setContract(getGameManager().getSquare(19).getContract());
			piazzaDanteSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) piazzaDanteSquareComponent.getContract()));

			SquareComponent viaMarcoPoloSquareComponent = (SquareComponent) viaMarcoPoloSquare.getChildren().get(0);
			viaMarcoPoloSquareComponent.setContract(getGameManager().getSquare(21).getContract());
			viaMarcoPoloSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) viaMarcoPoloSquareComponent.getContract()));

			SquareComponent corsoMagellanoSquareComponent =
					(SquareComponent) corsoMagellanoSquare.getChildren().get(0);
			corsoMagellanoSquareComponent.setContract(getGameManager().getSquare(23).getContract());
			corsoMagellanoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) corsoMagellanoSquareComponent.getContract()));

			SquareComponent largoColomboSquareComponent = (SquareComponent) largoColomboSquare.getChildren().get(0);
			largoColomboSquareComponent.setContract(getGameManager().getSquare(24).getContract());
			largoColomboSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) largoColomboSquareComponent.getContract()));

			SquareComponent vialeCostantinoSquareComponent =
					(SquareComponent) vialeCostantinoSquare.getChildren().get(0);
			vialeCostantinoSquareComponent.setContract(getGameManager().getSquare(26).getContract());
			vialeCostantinoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeCostantinoSquareComponent.getContract()));

			SquareComponent vialeTraianoSquareComponent = (SquareComponent) vialeTraianoSquare.getChildren().get(0);
			vialeTraianoSquareComponent.setContract(getGameManager().getSquare(27).getContract());
			vialeTraianoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeTraianoSquareComponent.getContract()));

			SquareComponent piazzaGiulioCesareSquareComponent =
					(SquareComponent) piazzaGiulioCesareSquare.getChildren().get(0);
			piazzaGiulioCesareSquareComponent.setContract(getGameManager().getSquare(29).getContract());
			piazzaGiulioCesareSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) piazzaGiulioCesareSquareComponent.getContract()));

			SquareComponent viaRomaSquareComponent = (SquareComponent) viaRomaSquare.getChildren().get(0);
			viaRomaSquareComponent.setContract(getGameManager().getSquare(31).getContract());
			viaRomaSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) viaRomaSquareComponent.getContract()));

			SquareComponent corsoImperoSquareComponent = (SquareComponent) corsoImperoSquare.getChildren().get(0);
			corsoImperoSquareComponent.setContract(getGameManager().getSquare(32).getContract());
			corsoImperoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) corsoImperoSquareComponent.getContract()));

			SquareComponent largoAugustoSquareComponent = (SquareComponent) largoAugustoSquare.getChildren().get(0);
			largoAugustoSquareComponent.setContract(getGameManager().getSquare(34).getContract());
			largoAugustoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) largoAugustoSquareComponent.getContract()));

			SquareComponent vialeDeiGiardiniSquareComponent =
					(SquareComponent) vialeDeiGiardiniSquare.getChildren().get(0);
			vialeDeiGiardiniSquareComponent.setContract(getGameManager().getSquare(37).getContract());
			vialeDeiGiardiniSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeDeiGiardiniSquareComponent.getContract()));

			SquareComponent parcoDellaVittoriaSquareComponent =
					(SquareComponent) parcoDellaVittoriaSquare.getChildren().get(0);
			parcoDellaVittoriaSquareComponent.setContract(getGameManager().getSquare(39).getContract());
			parcoDellaVittoriaSquare.setOnMouseClicked(
					event -> showTerrainView((PropertyContract) parcoDellaVittoriaSquareComponent.getContract()));

			// STATIONS
			SquareComponent southStationSquareComponent = (SquareComponent) southStationSquare.getChildren().get(0);
			southStationSquareComponent.setContract(getGameManager().getSquare(5).getContract());
			southStationSquare.setOnMouseClicked(
					event -> showTerrainView((StationContract) southStationSquareComponent.getContract()));

			SquareComponent westStationSquareComponent = (SquareComponent) westStationSquare.getChildren().get(0);
			westStationSquareComponent.setContract(getGameManager().getSquare(15).getContract());
			westStationSquare.setOnMouseClicked(
					event -> showTerrainView((StationContract) westStationSquareComponent.getContract()));

			SquareComponent northStationSquareComponent = (SquareComponent) northStationSquare.getChildren().get(0);
			northStationSquareComponent.setContract(getGameManager().getSquare(25).getContract());
			northStationSquare.setOnMouseClicked(
					event -> showTerrainView((StationContract) northStationSquareComponent.getContract()));

			SquareComponent eastStationSquareComponent = (SquareComponent) eastStationSquare.getChildren().get(0);
			eastStationSquareComponent.setContract(getGameManager().getSquare(35).getContract());
			eastStationSquare.setOnMouseClicked(
					event -> showTerrainView((StationContract) eastStationSquareComponent.getContract()));

			// COMPANIES
			SquareComponent electricSocietySquareComponent =
					(SquareComponent) electricSocietySquare.getChildren().get(0);
			electricSocietySquareComponent.setContract(getGameManager().getSquare(12).getContract());
			electricSocietySquare.setOnMouseClicked(
					event -> showTerrainView((CompanyContract) electricSocietySquareComponent.getContract()));

			SquareComponent waterWorksSquareComponent = (SquareComponent) waterWorksSquare.getChildren().get(0);
			waterWorksSquareComponent.setContract(getGameManager().getSquare(28).getContract());
			waterWorksSquare.setOnMouseClicked(
					event -> showTerrainView((CompanyContract) waterWorksSquareComponent.getContract()));
		}

		// add SmallTerrainViewComponents to the owned properties
		Label showOtherProperties = (Label) ownedPropertiesPane.getChildren().get(0);
		ownedPropertiesPane.getChildren().clear();
		for (int i = 0; i < SHOWN_OWNED_PROPERTIES; i++)
		{
			SmallTerrainViewComponent terrainView = new SmallTerrainViewComponent(null);
			terrainView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			ownedPropertiesPane.getChildren().add(terrainView);
		}
		ownedPropertiesPane.getChildren().add(showOtherProperties);

		calculateOwnedTerrains();

		ownedPropertiesPane.getChildren().get(SHOWN_OWNED_PROPERTIES)
				.setOnMouseClicked(event -> SceneManager.getInstance().showScene(
						SceneType.OWNED_TERRAIN));

		// button callback
		throwDiceButton.setOnAction(event -> ClientMain.getConnection().send(ClientMessages.THROW_DICE_MESSAGE_NAME,
		                                                                     Serializer
				                                                                     .toJson(new ThrowDiceClientMessage(
						                                                                     Game.getGameCode()))));

		endTurnButton.setOnAction(event -> {
			if (Game.getGameManager().getCurrentRound().isDiceThrown())
				if (Game.getPlayer().hasMoney(0))
					ClientMain.getConnection().send(ClientMessages.END_ROUND_MESSAGE_NAME,
					                                Serializer.toJson(new EndRoundClientMessage(Game.getGameCode())));
				else
					AlertUtil.showInformationAlert("Debito", "Sei in debito",
					                               "Salda il debito prima di finire il turno. Se finisci le " +
					                               "risorse perderai la partita.");
			else
				AlertUtil.showInformationAlert("Tira!", "Devi tirare i dadi",
				                               "Non puoi finire il turno senza tirare prima i dadi.");
		});

		leaveGameButton.setOnAction(event -> {
			ClientMain.getConnection().send(ClientMessages.LEAVE_GAME_MESSAGE_NAME,
			                                Serializer.toJson(new LeaveGameClientMessage(Game.getGameCode())));
			SceneManager.getInstance().showScene(SceneType.MAIN_MENU);
		});

		updatePlayerListView();
		updateMoneyLabel();
	}

	/**
	 * Show a new not resizable stage containing information about a property
	 *
	 * @param contract The contract of the property to show
	 */
	public static void showTerrainView(PropertyContract contract)
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
	public static void showTerrainView(StationContract contract)
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
	public static void showTerrainView(CompanyContract contract)
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

	/**
	 * Calculate and show the owned terrains in the pane under the board
	 */
	private void calculateOwnedTerrains()
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
	}

	/**
	 * Update the label showing the money of the player
	 */
	public void updateMoneyLabel()
	{
		if (getPlayer() != null)
			moneyLabel.setText(getPlayer().getMoney() + "M");
	}

	/**
	 * Update the list view of all players
	 */
	public void updatePlayerListView()
	{
		playerListView.getChildren().clear();

		for (Player player : Game.getGameManager().getPlayers())
		{
			System.out.println("Updating player view for " + player.getName());
			//player.setPawnPath(pawnPath);
			PlayerInfoComponent playerInfoComponent = new PlayerInfoComponent(player);
			playerInfoComponent.setMaxHeight(Double.MAX_VALUE);
			playerInfoComponent.setMaxWidth(Double.MAX_VALUE);
			VBox.setVgrow(playerInfoComponent, Priority.ALWAYS);
			playerListView.getChildren().add(playerInfoComponent);
		}
	}

	public ImageView getDie1Image()
	{
		return die1Image;
	}

	public ImageView getDie2Image()
	{
		return die2Image;
	}

	public ChatComponent getChat()
	{
		return chat;
	}
}
