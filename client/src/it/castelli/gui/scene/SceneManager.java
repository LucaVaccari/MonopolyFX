package it.castelli.gui.scene;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.FXMLFileLoader;
import it.castelli.gui.controllers.*;
import it.castelli.gui.customComponents.PropertyViewComponent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Class for loading and switching JavaFX scenes or opening new windows.
 */
public class SceneManager
{
	public static final String MAIN_MENU_FXML_PATH = "FXMLs/mainMenu.fxml";
	public static final String PAWN_CHOICE_FXML_PATH = "FXMLs/pawnChoice.fxml";
	public static final String LOBBY_FXML_PATH = "FXMLs/lobby.fxml";
	public static final String BOARD_FXML_PATH = "FXMLs/board.fxml";
	public static final String EXCHANGE_FXML_PATH = "FXMLs/exchange.fxml";
	public static final String AUCTION_FXML_PATH = "FXMLs/auction.fxml";
	public static final String OWNED_TERRAIN_FXML_PATH = "FXMLs/ownedTerrainView.fxml";
	private static SceneManager instance;

	/**
	 * List of all scenes that can appear in the game
	 */
	private final HashMap<SceneType, SceneFXWrapper> allScenes = new HashMap<>();
	/**
	 * List of all opened windows
	 */
	private final HashMap<SceneType, Stage> openStages = new HashMap<>();
	/**
	 * The main stage created by JavaFX
	 */
	private Stage primaryStage;

	/**
	 * Constructor of SceneManager, which initializes allScenes
	 */
	private SceneManager()
	{
		Parent mainMenuRoot = FXMLFileLoader.loadFXML("/" + MAIN_MENU_FXML_PATH, this);
		if (mainMenuRoot != null)
		{
			Scene mainMenuScene = new Scene(mainMenuRoot);
			mainMenuScene.getStylesheets().add(getClass().getResource("/FXMLs/Style/style.css").toExternalForm());
			allScenes.put(SceneType.MAIN_MENU, new SceneFXWrapper(mainMenuScene, true, "MonopolyFX"));
		}
		else
		{
			AlertUtil.showErrorAlert("Missing resources",
			                         "Cannot find file", "The file " + MAIN_MENU_FXML_PATH + " is missing");
			System.exit(-1);
		}

		Parent pawnChoiceRoot = FXMLFileLoader.loadFXML("/" + PAWN_CHOICE_FXML_PATH, this);
		if (pawnChoiceRoot != null)
		{
			Scene lobbyScene = new Scene(pawnChoiceRoot);
			allScenes.put(SceneType.PAWN_CHOICE,
			              new SceneFXWrapper(lobbyScene, true, "MonopolyFX - Scelta pedina"));
		}
		else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file",
			                         "The file " + PAWN_CHOICE_FXML_PATH + " is missing");
			System.exit(-1);
		}

		Parent lobbyRoot = FXMLFileLoader.loadFXML("/" + LOBBY_FXML_PATH, this);
		if (lobbyRoot != null)
		{
			Scene lobbyScene = new Scene(lobbyRoot);
			allScenes.put(SceneType.LOBBY, new SceneFXWrapper(lobbyScene, true, "MonopolyFX - Lobby"));
		}
		else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file",
			                         "The file " + LOBBY_FXML_PATH + " is missing");
			System.exit(-1);
		}

		Parent boardRoot = FXMLFileLoader.loadFXML("/" + BOARD_FXML_PATH,
		                                           this);
		if (boardRoot != null)
		{
			Scene boardScene = new Scene(boardRoot);
			allScenes
					.put(SceneType.BOARD, new SceneFXWrapper(boardScene, true,
					                                         "MonopolyFX - In Gioco"));
		}
		else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file",
			                         "The file " + BOARD_FXML_PATH +
			                         " is missing");
			System.exit(-1);
		}

		Parent exchangeRoot = FXMLFileLoader.loadFXML("/" + EXCHANGE_FXML_PATH, this);
		if (exchangeRoot != null)
		{
			Scene exchangeScene = new Scene(exchangeRoot);
			allScenes.put(SceneType.EXCHANGE,
			              new SceneFXWrapper(exchangeScene, false, "Scambio"));
		}
		else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file",
			                         "The file " + EXCHANGE_FXML_PATH + " is missing");
			System.exit(-1);
		}

		Parent auctionRoot =
				FXMLFileLoader.loadFXML("/" + AUCTION_FXML_PATH, this);
		if (auctionRoot != null)
		{
			Scene auctionScene = new Scene(auctionRoot);
			allScenes.put(SceneType.AUCTION,
			              new SceneFXWrapper(auctionScene, false, "Asta"));
		}
		else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file",
			                         "The file " + AUCTION_FXML_PATH + " is missing");
			System.exit(-1);
		}

		Parent propertyViewRoot = FXMLFileLoader.loadFXML("/" + OWNED_TERRAIN_FXML_PATH, this);
		if (propertyViewRoot != null)
		{
			Scene propertyViewScene = new Scene(propertyViewRoot);
			allScenes.put(SceneType.OWNED_TERRAIN, new SceneFXWrapper(propertyViewScene, false,
			                                                          "I Vostri possedimenti"));
		}
		else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file",
			                         "The file " + OWNED_TERRAIN_FXML_PATH + " is missing");
			System.exit(-1);
		}
	}

	/**
	 * Getter for the singleton instance
	 *
	 * @return The only instance of this class
	 */
	public static SceneManager getInstance()
	{
		if (instance == null)
			instance = new SceneManager();
		return instance;
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
			FXMLLoader loader = FXMLFileLoader.getLoader(BoardController.COMPANY_VIEW_FXML_PATH);
			Parent root = loader.load();
			CompanyViewController controller = loader.getController();
			controller.setContract(contract);
			Stage companyViewStage = new Stage();
			Scene scene = new Scene(root);
			companyViewStage.setScene(scene);
			companyViewStage.initModality(Modality.APPLICATION_MODAL);
			companyViewStage.setAlwaysOnTop(true);
			companyViewStage.setResizable(false);
			companyViewStage.show();

			openStages.put(SceneType.COMPANY_VIEW, companyViewStage);
			companyViewStage.setOnCloseRequest(event -> openStages.remove(SceneType.COMPANY_VIEW));
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
			FXMLLoader loader = FXMLFileLoader.getLoader(BoardController.STATION_VIEW_FXML_PATH);
			Parent root = loader.load();
			StationViewController controller = loader.getController();
			controller.setContract(contract);
			Stage stationViewStage = new Stage();
			Scene scene = new Scene(root);
			stationViewStage.setScene(scene);
			stationViewStage.initModality(Modality.APPLICATION_MODAL);
			stationViewStage.setAlwaysOnTop(true);
			stationViewStage.setResizable(false);
			stationViewStage.show();

			openStages.put(SceneType.STATION_VIEW, stationViewStage);
			stationViewStage.setOnCloseRequest(event -> openStages.remove(SceneType.STATION_VIEW));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
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

		//			FXMLLoader loader = FXMLFileLoader.getLoader(BoardController.PROPERTY_VIEW_FXML_PATH);
		Parent root = new PropertyViewComponent(contract);
//			PropertyViewController controller = loader.getController();
//			controller.setContract(contract);
		Stage propertyViewStage = new Stage();
		Scene scene = new Scene(root);
		propertyViewStage.setScene(scene);
		propertyViewStage.initModality(Modality.APPLICATION_MODAL);
		propertyViewStage.setAlwaysOnTop(true);
		propertyViewStage.setResizable(false);
		propertyViewStage.show();

		openStages.put(SceneType.PROPERTY_VIEW, propertyViewStage);
		propertyViewStage.setOnCloseRequest(event -> openStages.remove(SceneType.PROPERTY_VIEW));
	}

	public Stage getPrimaryStage()
	{
		return primaryStage;
	}

	/**
	 * Setter for the primary stage (should not be used except in the start()
	 * method)
	 *
	 * @param primaryStage The primary stage generated by JavaFX
	 */
	public void setPrimaryStage(Stage primaryStage)
	{
		primaryStage.setOnCloseRequest(event -> System.exit(0));
		this.primaryStage = primaryStage;
	}

	public void showPlayerInfoView(Player player)
	{
		if (player == null)
			return;

		try
		{
			FXMLLoader loader = FXMLFileLoader.getLoader(BoardController.PLAYER_INFO_FXML_PATH);
			Parent root = loader.load();
			PlayerInfoController controller = loader.getController();
			controller.setPlayer(player);
			Stage playerInfoStage = new Stage();
			Scene scene = new Scene(root);
			playerInfoStage.setScene(scene);
			playerInfoStage.initModality(Modality.APPLICATION_MODAL);
			playerInfoStage.setAlwaysOnTop(true);
			playerInfoStage.setResizable(false);
			openStages.put(SceneType.PLAYER_INFO, playerInfoStage);
			playerInfoStage.setOnCloseRequest(event -> openStages.remove(SceneType.PLAYER_INFO));
			playerInfoStage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Show a specified scene, or open a new window (if necessary)
	 *
	 * @param sceneType The scene to open
	 */
	public void showScene(SceneType sceneType)
	{
		SceneFXWrapper scene = allScenes.get(sceneType);
		if (scene.isShownInPrimaryStage())
		{
			primaryStage.setScene(scene.getScene());
			primaryStage.setTitle(scene.getWindowTitle());
			primaryStage.setResizable(false);
			primaryStage.show();
		}
		else
		{
			if (openStages.containsKey(sceneType))
				openStages.get(sceneType).close();

			Stage stage = new Stage();

			if (sceneType == SceneType.EXCHANGE || sceneType == SceneType.AUCTION)
				stage.initModality(Modality.APPLICATION_MODAL);

			stage.setScene(scene.getScene());
			stage.setTitle(scene.getWindowTitle());
			stage.setResizable(false);
			stage.show();

			openStages.put(sceneType, stage);
			stage.setOnCloseRequest(event -> openStages.remove(sceneType));
		}
	}

	/**
	 * Get a stage by providing a sceneType
	 *
	 * @param sceneType The type of the scene contained in the stage
	 */
	public Stage getStageByType(SceneType sceneType)
	{
		return openStages.get(sceneType);
	}
}
