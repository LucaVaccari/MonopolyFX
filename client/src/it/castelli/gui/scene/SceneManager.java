package it.castelli.gui.scene;

import it.castelli.gui.FXMLFileLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class SceneManager
{
	private static SceneManager instance;
	private final HashMap<SceneType, SceneFXWrapper> allScenes = new HashMap<>();
	private final ArrayList<Stage> openStages = new ArrayList<>();
    private Stage primaryStage;

	private SceneManager()
	{
		allScenes.put(SceneType.MAIN_MENU, new SceneFXWrapper(new Scene(FXMLFileLoader.loadFXML("mainMenu.fxml")), true, "Main Menu"));
		allScenes.put(SceneType.LOBBY, new SceneFXWrapper(new Scene(FXMLFileLoader.loadFXML("lobby.fxml")), true, "Lobby"));
		allScenes.put(SceneType.BOARD, new SceneFXWrapper(new Scene(FXMLFileLoader.loadFXML("board.fxml")), true, "Board"));
		allScenes.put(SceneType.EXCHANGE, new SceneFXWrapper(new Scene(FXMLFileLoader.loadFXML("exchange.fxml")), false, "Exchange"));
		allScenes.put(SceneType.AUCTION, new SceneFXWrapper(new Scene(FXMLFileLoader.loadFXML("auction.fxml")), false, "Auction"));
		allScenes.put(SceneType.PROPERTY_VIEW, new SceneFXWrapper(new Scene(FXMLFileLoader.loadFXML("propertyView.fxml")), false, "Property View"));
		allScenes.put(SceneType.PLAYER_INFO, new SceneFXWrapper(new Scene(FXMLFileLoader.loadFXML("playerInfo.fxml")), false, "Player Info"));
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
	        primaryStage.show();
        }
	    else
        {
			Stage stage = new Stage();
			stage.setScene(scene.getScene());
			stage.show();
			openStages.add(stage);
			stage.setOnCloseRequest(event -> openStages.remove(stage));
        }
    }
}
