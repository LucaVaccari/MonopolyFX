package it.castelli.gui.scene;

import it.castelli.gui.AlertUtil;
import it.castelli.gui.FXMLFileLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class SceneManager
{
	public static final String MAIN_MENU_FXML_PATH = "mainMenu.fxml";
	public static final String LOBBY_FXML_PATH = "lobby.fxml";
	public static final String BOARD_FXML_PATH = "board.fxml";
	public static final String EXCHANGE_FXML_PATH = "exchange.fxml";
	public static final String AUCTION_FXML_PATH = "auction.fxml";
	public static final String PROPERTY_VIEW_FXML_PATH = "propertyView.fxml";
	public static final String PLAYER_INFO_FXML_PATH = "playerInfo.fxml";
	private static SceneManager instance;

	private final HashMap<SceneType, SceneFXWrapper> allScenes = new HashMap<>();
	private final ArrayList<Stage> openStages = new ArrayList<>();
	private Stage primaryStage;

	private SceneManager()
	{
		Parent mainMenuRoot = FXMLFileLoader.loadFXML("/" + MAIN_MENU_FXML_PATH, this);
		if (mainMenuRoot != null)
		{
			Scene mainMenuScene = new Scene(mainMenuRoot);
			allScenes.put(SceneType.MAIN_MENU, new SceneFXWrapper(mainMenuScene, true, "MonopolyFX"));
		} else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file", "The file " + MAIN_MENU_FXML_PATH + " is missing");
			System.exit(-1);
		}

		Parent lobbyRoot = FXMLFileLoader.loadFXML("/" + LOBBY_FXML_PATH, this);
		if (lobbyRoot != null)
		{
			Scene lobbyScene = new Scene(lobbyRoot);
			allScenes.put(SceneType.LOBBY, new SceneFXWrapper(lobbyScene, true, "MonopolyFX - Lobby"));
		} else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file", "The file " + LOBBY_FXML_PATH + " is missing");
			System.exit(-1);
		}

		Parent boardRoot = FXMLFileLoader.loadFXML("/" + BOARD_FXML_PATH, this);
		if (boardRoot != null)
		{
			Scene boardScene = new Scene(boardRoot);
			allScenes.put(SceneType.BOARD, new SceneFXWrapper(boardScene, true, "MonopolyFX - In gioco"));
		} else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file", "The file " + BOARD_FXML_PATH + " is missing");
			System.exit(-1);
		}

		Parent exchangeRoot = FXMLFileLoader.loadFXML("/" + EXCHANGE_FXML_PATH, this);
		if (exchangeRoot != null)
		{
			Scene exchangeScene = new Scene(exchangeRoot);
			allScenes.put(SceneType.EXCHANGE, new SceneFXWrapper(exchangeScene, false, "Scambio"));
		} else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file", "The file " + EXCHANGE_FXML_PATH + " is missing");
			System.exit(-1);
		}

		Parent auctionRoot = FXMLFileLoader.loadFXML("/" + AUCTION_FXML_PATH, this);
		if (auctionRoot != null)
		{
			Scene auctionScene = new Scene(auctionRoot);
			allScenes.put(SceneType.AUCTION, new SceneFXWrapper(auctionScene, false, "Asta"));
		} else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file", "The file " + AUCTION_FXML_PATH + " is missing");
			System.exit(-1);
		}

		Parent propertyViewRoot = FXMLFileLoader.loadFXML("/" + PROPERTY_VIEW_FXML_PATH, this);
		if (propertyViewRoot != null)
		{
			Scene propertyViewScene = new Scene(propertyViewRoot);
			allScenes.put(SceneType.PROPERTY_VIEW, new SceneFXWrapper(propertyViewScene, false, "Proprietà"));
		} else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file", "The file " + PROPERTY_VIEW_FXML_PATH + " is missing");
			System.exit(-1);
		}

		Parent playerInfoRoot = FXMLFileLoader.loadFXML("/" + PLAYER_INFO_FXML_PATH, this);
		if (playerInfoRoot != null)
		{
			Scene playerInfoScene = new Scene(playerInfoRoot);
			allScenes.put(SceneType.PLAYER_INFO, new SceneFXWrapper(playerInfoScene, false, "Giocatore"));
		} else
		{
			AlertUtil.showErrorAlert("Missing resources", "Cannot find file", "The file " + PLAYER_INFO_FXML_PATH + " is missing");
			System.exit(-1);
		}
	}

	public static SceneManager getInstance()
	{
		if (instance == null)
			instance = new SceneManager();
		return instance;
	}

	public void setPrimaryStage(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
	}

	public void showScene(SceneType sceneType)
	{
		SceneFXWrapper scene = allScenes.get(sceneType);
		if (scene.isShowInPrimaryStage())
		{
			primaryStage.setScene(scene.getScene());
			primaryStage.setTitle(scene.getWindowTitle());
			primaryStage.show();
		} else
		{
			Stage stage = new Stage();
			stage.setScene(scene.getScene());
			stage.setTitle(scene.getWindowTitle());
			stage.show();
			openStages.add(stage);
			stage.setOnCloseRequest(event -> openStages.remove(stage));
		}
	}
}
