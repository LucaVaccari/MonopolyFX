package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.EndRoundClientMessage;
import it.castelli.connection.messages.LeaveGameClientMessage;
import it.castelli.connection.messages.ThrowDiceClientMessage;
import it.castelli.gameLogic.Pawn;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.FXMLFileLoader;
import it.castelli.gui.GUIUtils;
import it.castelli.gui.customComponents.ChatComponent;
import it.castelli.gui.customComponents.PlayerInfoComponent;
import it.castelli.gui.customComponents.SmallTerrainViewComponent;
import it.castelli.gui.customComponents.SquareComponent;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

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
	private Button endRoundButton;
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

	/**
	 * Array of all the Group squares on the board, containing a SquareComponent (image) and a FlowPane (pawn parent)
	 */
	private final Group[] squares = new Group[40];

	// player pawns
	/**
	 * Maps the ImageView shown on the board with the corresponding piece
	 */
	private final HashMap<Pawn, ImageView> playerPawns = new HashMap<>();

	@FXML
	private FlowPane ownedPropertiesPane;
	@FXML
	private Label moneyLabel;

	/**
	 * Singleton instance getter
	 *
	 * @return The singleton instance
	 */
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
			squares[1] = vicoloCortoSquare;

			SquareComponent vicoloStrettoSquareComponent = (SquareComponent) vicoloStrettoSquare.getChildren().get(0);
			vicoloStrettoSquareComponent.setContract(getGameManager().getSquare(3).getContract());
			vicoloStrettoSquare
					.setOnMouseClicked(
							event -> showTerrainView((PropertyContract) vicoloStrettoSquareComponent.getContract()));
			squares[3] = vicoloStrettoSquare;

			SquareComponent bastioniGranSassoSquareComponent =
					(SquareComponent) bastioniGranSassoSquare.getChildren().get(0);
			bastioniGranSassoSquareComponent.setContract(getGameManager().getSquare(6).getContract());
			bastioniGranSassoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) bastioniGranSassoSquareComponent.getContract()));
			squares[6] = bastioniGranSassoSquare;

			SquareComponent vialeMonterosaSquareComponent =
					(SquareComponent) vialeMonterosaSquare.getChildren().get(0);
			vialeMonterosaSquareComponent.setContract(getGameManager().getSquare(8).getContract());
			vialeMonterosaSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeMonterosaSquareComponent.getContract()));
			squares[8] = vialeMonterosaSquare;

			SquareComponent vialeVesuvioSquareComponent = (SquareComponent) vialeVesuvioSquare.getChildren().get(0);
			vialeVesuvioSquareComponent.setContract(getGameManager().getSquare(9).getContract());
			vialeVesuvioSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeVesuvioSquareComponent.getContract()));
			squares[9] = vialeVesuvioSquare;

			SquareComponent viaAccademiaSquareComponent = (SquareComponent) viaAccademiaSquare.getChildren().get(0);
			viaAccademiaSquareComponent.setContract(getGameManager().getSquare(11).getContract());
			viaAccademiaSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) viaAccademiaSquareComponent.getContract()));
			squares[11] = viaAccademiaSquare;

			SquareComponent corsoAteneoSquareComponent = (SquareComponent) corsoAteneoSquare.getChildren().get(0);
			corsoAteneoSquareComponent.setContract(getGameManager().getSquare(13).getContract());
			corsoAteneoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) corsoAteneoSquareComponent.getContract()));
			squares[13] = corsoAteneoSquare;

			SquareComponent piazzaUniversitaSquareComponent =
					(SquareComponent) piazzaUniversitaSquare.getChildren().get(0);
			piazzaUniversitaSquareComponent.setContract(getGameManager().getSquare(14).getContract());
			piazzaUniversitaSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) piazzaUniversitaSquareComponent.getContract()));
			squares[14] = piazzaUniversitaSquare;

			SquareComponent viaVerdiSquareComponent = (SquareComponent) viaVerdiSquare.getChildren().get(0);
			viaVerdiSquareComponent.setContract(getGameManager().getSquare(16).getContract());
			viaVerdiSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) viaVerdiSquareComponent.getContract()));
			squares[16] = viaVerdiSquare;

			SquareComponent corsoRaffaelloSquareComponent =
					(SquareComponent) corsoRaffaelloSquare.getChildren().get(0);
			corsoRaffaelloSquareComponent.setContract(getGameManager().getSquare(18).getContract());
			corsoRaffaelloSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) corsoRaffaelloSquareComponent.getContract()));
			squares[18] = corsoRaffaelloSquare;

			SquareComponent piazzaDanteSquareComponent = (SquareComponent) piazzaDanteSquare.getChildren().get(0);
			piazzaDanteSquareComponent.setContract(getGameManager().getSquare(19).getContract());
			piazzaDanteSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) piazzaDanteSquareComponent.getContract()));
			squares[19] = piazzaDanteSquare;

			SquareComponent viaMarcoPoloSquareComponent = (SquareComponent) viaMarcoPoloSquare.getChildren().get(0);
			viaMarcoPoloSquareComponent.setContract(getGameManager().getSquare(21).getContract());
			viaMarcoPoloSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) viaMarcoPoloSquareComponent.getContract()));
			squares[21] = viaMarcoPoloSquare;

			SquareComponent corsoMagellanoSquareComponent =
					(SquareComponent) corsoMagellanoSquare.getChildren().get(0);
			corsoMagellanoSquareComponent.setContract(getGameManager().getSquare(23).getContract());
			corsoMagellanoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) corsoMagellanoSquareComponent.getContract()));
			squares[23] = corsoMagellanoSquare;

			SquareComponent largoColomboSquareComponent = (SquareComponent) largoColomboSquare.getChildren().get(0);
			largoColomboSquareComponent.setContract(getGameManager().getSquare(24).getContract());
			largoColomboSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) largoColomboSquareComponent.getContract()));
			squares[24] = largoColomboSquare;

			SquareComponent vialeCostantinoSquareComponent =
					(SquareComponent) vialeCostantinoSquare.getChildren().get(0);
			vialeCostantinoSquareComponent.setContract(getGameManager().getSquare(26).getContract());
			vialeCostantinoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeCostantinoSquareComponent.getContract()));
			squares[26] = vialeCostantinoSquare;

			SquareComponent vialeTraianoSquareComponent = (SquareComponent) vialeTraianoSquare.getChildren().get(0);
			vialeTraianoSquareComponent.setContract(getGameManager().getSquare(27).getContract());
			vialeTraianoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeTraianoSquareComponent.getContract()));
			squares[27] = vialeTraianoSquare;

			SquareComponent piazzaGiulioCesareSquareComponent =
					(SquareComponent) piazzaGiulioCesareSquare.getChildren().get(0);
			piazzaGiulioCesareSquareComponent.setContract(getGameManager().getSquare(29).getContract());
			piazzaGiulioCesareSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) piazzaGiulioCesareSquareComponent.getContract()));
			squares[29] = piazzaGiulioCesareSquare;

			SquareComponent viaRomaSquareComponent = (SquareComponent) viaRomaSquare.getChildren().get(0);
			viaRomaSquareComponent.setContract(getGameManager().getSquare(31).getContract());
			viaRomaSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) viaRomaSquareComponent.getContract()));
			squares[31] = viaRomaSquare;

			SquareComponent corsoImperoSquareComponent = (SquareComponent) corsoImperoSquare.getChildren().get(0);
			corsoImperoSquareComponent.setContract(getGameManager().getSquare(32).getContract());
			corsoImperoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) corsoImperoSquareComponent.getContract()));
			squares[32] = corsoImperoSquare;

			SquareComponent largoAugustoSquareComponent = (SquareComponent) largoAugustoSquare.getChildren().get(0);
			largoAugustoSquareComponent.setContract(getGameManager().getSquare(34).getContract());
			largoAugustoSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) largoAugustoSquareComponent.getContract()));
			squares[34] = largoAugustoSquare;

			SquareComponent vialeDeiGiardiniSquareComponent =
					(SquareComponent) vialeDeiGiardiniSquare.getChildren().get(0);
			vialeDeiGiardiniSquareComponent.setContract(getGameManager().getSquare(37).getContract());
			vialeDeiGiardiniSquare.setOnMouseClicked(event -> showTerrainView(
					(PropertyContract) vialeDeiGiardiniSquareComponent.getContract()));
			squares[37] = vialeDeiGiardiniSquare;

			SquareComponent parcoDellaVittoriaSquareComponent =
					(SquareComponent) parcoDellaVittoriaSquare.getChildren().get(0);
			parcoDellaVittoriaSquareComponent.setContract(getGameManager().getSquare(39).getContract());
			parcoDellaVittoriaSquare.setOnMouseClicked(
					event -> showTerrainView((PropertyContract) parcoDellaVittoriaSquareComponent.getContract()));
			squares[39] = parcoDellaVittoriaSquare;

			// STATIONS
			SquareComponent southStationSquareComponent = (SquareComponent) southStationSquare.getChildren().get(0);
			southStationSquareComponent.setContract(getGameManager().getSquare(5).getContract());
			southStationSquare.setOnMouseClicked(
					event -> showTerrainView((StationContract) southStationSquareComponent.getContract()));
			squares[5] = southStationSquare;

			SquareComponent westStationSquareComponent = (SquareComponent) westStationSquare.getChildren().get(0);
			westStationSquareComponent.setContract(getGameManager().getSquare(15).getContract());
			westStationSquare.setOnMouseClicked(
					event -> showTerrainView((StationContract) westStationSquareComponent.getContract()));
			squares[15] = westStationSquare;

			SquareComponent northStationSquareComponent = (SquareComponent) northStationSquare.getChildren().get(0);
			northStationSquareComponent.setContract(getGameManager().getSquare(25).getContract());
			northStationSquare.setOnMouseClicked(
					event -> showTerrainView((StationContract) northStationSquareComponent.getContract()));
			squares[25] = northStationSquare;

			SquareComponent eastStationSquareComponent = (SquareComponent) eastStationSquare.getChildren().get(0);
			eastStationSquareComponent.setContract(getGameManager().getSquare(35).getContract());
			eastStationSquare.setOnMouseClicked(
					event -> showTerrainView((StationContract) eastStationSquareComponent.getContract()));
			squares[35] = eastStationSquare;

			// COMPANIES
			SquareComponent electricSocietySquareComponent =
					(SquareComponent) electricSocietySquare.getChildren().get(0);
			electricSocietySquareComponent.setContract(getGameManager().getSquare(12).getContract());
			electricSocietySquare.setOnMouseClicked(
					event -> showTerrainView((CompanyContract) electricSocietySquareComponent.getContract()));
			squares[12] = electricSocietySquare;

			SquareComponent waterWorksSquareComponent = (SquareComponent) waterWorksSquare.getChildren().get(0);
			waterWorksSquareComponent.setContract(getGameManager().getSquare(28).getContract());
			waterWorksSquare.setOnMouseClicked(
					event -> showTerrainView((CompanyContract) waterWorksSquareComponent.getContract()));
			squares[28] = waterWorksSquare;

			// CHANCES
			squares[7] = chanceSquare1;
			squares[22] = chanceSquare2;
			squares[36] = chanceSquare3;

			// COMMUNITY CHESTS
			squares[2] = communityChestSquare1;
			squares[17] = communityChestSquare2;
			squares[33] = communityChestSquare3;

			// TAXES
			squares[4] = patrimonialTaxSquare;
			squares[38] = luxuryTaxSquare;

			// CORNERS
			squares[0] = goSquare;
			squares[10] = justVisitingSquare;
			squares[20] = freeParkingSquare;
			squares[30] = goToJailSquare;
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

		ownedPropertiesPane.getChildren().get(SHOWN_OWNED_PROPERTIES)
				.setOnMouseClicked(event -> SceneManager.getInstance().showScene(
						SceneType.OWNED_TERRAIN));

		// button callback
		throwDiceButton.setOnAction(event -> ClientMain.getConnection().send(ClientMessages.THROW_DICE_MESSAGE_NAME,
				Serializer.toJson(new ThrowDiceClientMessage(Game.getGameCode()))));

		endRoundButton.setOnAction(event -> {
			if (Game.getGameManager().getCurrentRound().isDiceThrown())
				if (Game.getPlayer().hasMoney(0))
					ClientMain.getConnection().send(ClientMessages.END_ROUND_MESSAGE_NAME,
							Serializer.toJson(new EndRoundClientMessage(Game.getGameCode())));
				else
					AlertUtil.showInformationAlert("Debito", "Siete in debito",
							"Saldate il debito prima di finire il turno. Se finite le " +
									"risorse perderete la partita.");
			else
				AlertUtil.showInformationAlert("Tirate!", "Dovete tirare i dadi",
						"Non potete finire il turno senza tirare prima i dadi.");
		});

		leaveGameButton.setOnAction(event -> {
			Optional<ButtonType> confirm =
					AlertUtil.showConfirmationAlert("Conferma", "Volete davvero uscire?", "Siete veramente sicuri?");
			if (confirm.isPresent())
			{
				if (confirm.get().equals(ButtonType.OK))
				{
					ClientMain.getConnection().send(ClientMessages.LEAVE_GAME_MESSAGE_NAME,
							Serializer.toJson(new LeaveGameClientMessage(Game.getGameCode())));

					SceneManager.getInstance().showScene(SceneType.MAIN_MENU);
				}
			}
		});
	}

	/**
	 * Should be called when the Board scene is loaded by the SceneManager
	 */
	public void onSceneLoaded()
	{
		// player pawns
		playerPawns.clear();

		for (Player player : Game.getGameManager().getPlayers())
		{
			String imagePath = GUIUtils.getPawnPaths().get(player.getPawn());
			Image pawnImage = new Image(String.valueOf(getClass().getResource(imagePath)));
			ImageView pawnImageView = new ImageView(pawnImage);
			pawnImageView.setPreserveRatio(true);
			playerPawns.put(player.getPawn(), pawnImageView);
		}
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

			if (getPlayer().getContracts().size() > 0)
			{
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
			else
			{
				ownedTerrains[i].setVisible(false);
				ownedTerrains[i].setDisable(true);
			}
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
			//player.setPawnPath(pawnPath);
			PlayerInfoComponent playerInfoComponent = new PlayerInfoComponent(player);
			playerInfoComponent.setMaxHeight(Double.MAX_VALUE);
			playerInfoComponent.setMaxWidth(Double.MAX_VALUE);
			VBox.setVgrow(playerInfoComponent, Priority.ALWAYS);
			playerListView.getChildren().add(playerInfoComponent);
		}
	}

	/**
	 * //	 * Update the view of player pawns on the board //
	 */
	public void updateRound()
	{
		boolean yourTurn = getPlayer().betterEquals(Game.getGameManager().getCurrentRound().getCurrentActivePlayer());

		// player list view
		ObservableList<Node> list = playerListView.getChildren();
		for (Node element : list)
		{
			PlayerInfoComponent player = (PlayerInfoComponent) element;
			if (player.getPlayer().betterEquals(Game.getGameManager().getCurrentRound().getCurrentActivePlayer()))
			{
				player.getPlayerNameLabel().setStyle("-fx-background-color: #cc000f");
				return;
			}
		}

		// hide buttons
		endRoundButton.setVisible(yourTurn);
		endRoundButton.setDisable(!yourTurn);
	}

	/**
	 * Update the view of player pawns on the board
	 */
	public void updatePawnsOnBoard()
	{
		for (Group square : squares)
		{
			FlowPane flowPane = (FlowPane) square.getChildren().get(1);
			flowPane.getChildren().clear();
		}

		for (Player player : Game.getGameManager().getPlayers())
		{
			FlowPane flowPane = (FlowPane) squares[player.getPosition()].getChildren().get(1);
			flowPane.setVgap(5);
			flowPane.setHgap(5);
			ObservableList<Node> children = flowPane.getChildren();
			ImageView pawnImageView = playerPawns.get(player.getPawn());
			if (pawnImageView == null)
				System.out.println("BoardController::updatePawnsOnBoard - Pawn image is null");
			else
			{
				children.add(pawnImageView);
			}
		}
		for (Player player : Game.getGameManager().getPlayers())
		{
			FlowPane flowPane = (FlowPane) squares[player.getPosition()].getChildren().get(1);
			ObservableList<Node> children = flowPane.getChildren();
			ImageView pawnImageView = playerPawns.get(player.getPawn());
			int size = switch (children.size())
					{
						case 1 -> 30;
						case 2, 3, 4 -> 20;
						case 5, 6 -> 15;
						default -> 10;
					};
			pawnImageView.setFitHeight(size);
			pawnImageView.setFitWidth(size);
		}
	}

	/**
	 * Update everything (ownedTerrains, moneyLabel, playerListView, pawnsOnBoard)
	 */
	public void update()
	{
		calculateOwnedTerrains();
		updateMoneyLabel();
		updatePlayerListView();
		updatePawnsOnBoard();
		updateRound();
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

	public Button getThrowDiceButton()
	{
		return throwDiceButton;
	}
}
