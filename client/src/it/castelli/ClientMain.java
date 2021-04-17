package it.castelli;

import it.castelli.connection.Connection;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class ClientMain extends Application
{
	private static Connection connection;
	private final static HashMap<String, String> configs = new HashMap<>();
	private static String serverIp;
	private static int serverPort;
	private final static String configPath = "data/config.cfg";

	public static void main(String[] args)
	{
		ClientMain.readConfigFile(configPath);

		serverIp = configs.get("server.ip");
		serverPort = Integer.parseInt(configs.get("server.port"));

		Socket clientSocket = null;
		try
		{
 			clientSocket = new Socket(serverIp, serverPort);

		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Impossibile connettersi al server");
		}
		connection = new Connection(clientSocket);
		launch(args);
	}

	public static Connection getConnection()
	{
		return connection;
	}

	/**
	 * The main entry point for all JavaFX applications. The start method is called after the init method has returned,
	 * and after the system is ready for the application to begin running.
	 *
	 * <p>
	 * NOTE: This method is called on the JavaFX Application Thread.
	 * </p>
	 *
	 * @param primaryStage the primary stage for this application, onto which the application scene can be set.
	 *                     Applications may create other stages, if needed, but they will not be primary stages.
	 */
	@Override
	public void start(Stage primaryStage)
	{
		SceneManager.getInstance().setPrimaryStage(primaryStage);
		SceneManager.getInstance().showScene(SceneType.MAIN_MENU);
	}

	/**
	 * Method to read the config file and set its content in a map of properties
	 *
	 * @param pathName the path of the config file
	 */
	public static void readConfigFile(String pathName) {
		try {
			Scanner myReader = new Scanner(new File(pathName));
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine().toLowerCase();
				String[] tokens = data.strip().split("=");
				configs.put(tokens[0], tokens[1]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred reading the config file");
			e.printStackTrace();
		}
	}
}