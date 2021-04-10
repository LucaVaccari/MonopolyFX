package it.castelli.gui.scene;

import it.castelli.gui.AlertUtil;
import it.castelli.gui.FXMLFileLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
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
	public static final String PLAYER_INFO_FXML_PATH = "FXMLs/playerInfo.fxml";
	private static SceneManager instance;

	/**
	 * List of all scenes that can appear in the game
	 */
	private final HashMap<SceneType, SceneFXWrapper> allScenes = new HashMap<>();
	/**
	 * List of all opened windows
	 */
	private final ArrayList<Stage> openStages = new ArrayList<>();
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
			mainMenuScene.getStylesheets().add(getClass().getResource(
					"/FXMLs/Style/style.css").toExternalForm());
			allScenes.put(SceneType.MAIN_MENU, new SceneFXWrapper(mainMenuScene, true, "MonopolyFX"));
		} else
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
		} else
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
		} else
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
							"MonopolyFX - " +
									"In" +
									" " +
									"gioco"));
		} else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file",
					"The file " + BOARD_FXML_PATH +
							" is missing");
			System.exit(-1);
		}

		Parent exchangeRoot =
				FXMLFileLoader.loadFXML("/" + EXCHANGE_FXML_PATH, this);
		if (exchangeRoot != null)
		{
			Scene exchangeScene = new Scene(exchangeRoot);
			allScenes.put(SceneType.EXCHANGE,
					new SceneFXWrapper(exchangeScene, false, "Scambio"));
		} else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file",
					"The file " + EXCHANGE_FXML_PATH +
							" is missing");
			System.exit(-1);
		}

		Parent auctionRoot =
				FXMLFileLoader.loadFXML("/" + AUCTION_FXML_PATH, this);
		if (auctionRoot != null)
		{
			Scene auctionScene = new Scene(auctionRoot);
			allScenes.put(SceneType.AUCTION,
					new SceneFXWrapper(auctionScene, false, "Asta"));
		} else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file",
					"The file " + AUCTION_FXML_PATH +
							" is missing");
			System.exit(-1);
		}

		Parent propertyViewRoot = FXMLFileLoader.loadFXML("/" + OWNED_TERRAIN_FXML_PATH, this);
		if (propertyViewRoot != null)
		{
			Scene propertyViewScene = new Scene(propertyViewRoot);
			allScenes.put(SceneType.OWNED_TERRAIN, new SceneFXWrapper(propertyViewScene, false,
					"I tuoi possedimenti"));
		} else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file",
					"The file " + OWNED_TERRAIN_FXML_PATH +
							" is missing");
			System.exit(-1);
		}

		Parent playerInfoRoot =
				FXMLFileLoader.loadFXML("/" + PLAYER_INFO_FXML_PATH, this);
		if (playerInfoRoot != null)
		{
			Scene playerInfoScene = new Scene(playerInfoRoot);
			allScenes
					.put(SceneType.PLAYER_INFO,
							new SceneFXWrapper(playerInfoScene, false,
									"Giocatore"));
		} else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file",
					"The file " + PLAYER_INFO_FXML_PATH +
							" is missing");
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
		} else
		{
			Stage stage = new Stage();
			stage.setScene(scene.getScene());
			stage.setTitle(scene.getWindowTitle());
			stage.setResizable(false);
			stage.show();
			openStages.add(stage);
			stage.setOnCloseRequest(event -> openStages.remove(stage));
		}
	}
}
