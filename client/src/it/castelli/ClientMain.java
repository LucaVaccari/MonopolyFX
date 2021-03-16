package it.castelli;

import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientMain extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		SceneManager.getInstance().setPrimaryStage(primaryStage);
		SceneManager.getInstance().showScene(SceneType.LOBBY);
	}
}
