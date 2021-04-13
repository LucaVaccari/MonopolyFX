package it.castelli;

import it.castelli.connection.Connection;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class ClientMain extends Application
{
	private static Connection connection;

	public static void main(String[] args)
	{
		Socket clientSocket = null;
		try
		{
//			clientSocket = new Socket("82.52.35.104", 1111);
			clientSocket = new Socket("localhost", 1111);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		connection = new Connection(clientSocket);
		launch(args);
	}

	public static Connection getConnection()
	{
		return connection;
	}

	/**
	 * The main entry point for all JavaFX applications.
	 * The start method is called after the init method has returned,
	 * and after the system is ready for the application to begin running.
	 *
	 * <p>
	 * NOTE: This method is called on the JavaFX Application Thread.
	 * </p>
	 *
	 * @param primaryStage the primary stage for this application, onto which
	 *                     the application scene can be set.
	 *                     Applications may create other stages, if needed,
	 *                     but they will not be
	 *                     primary stages.
	 * @throws Exception if something goes wrong
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		SceneManager.getInstance().setPrimaryStage(primaryStage);
		SceneManager.getInstance().showScene(SceneType.MAIN_MENU);
	}
}
