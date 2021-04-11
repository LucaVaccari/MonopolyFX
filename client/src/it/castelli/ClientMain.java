package it.castelli;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyColor;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.Serializable;

class Test implements Serializable
{
	public Contract bestContractEver;

	public Test(Contract contract)
	{
		this.bestContractEver = contract;
	}
}

public class ClientMain extends Application
{
	private static Connection connection;

	public static void main(String[] args)
	{
		// TEST
		Contract contract = new PropertyContract("SUS",
				1,
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				PropertyColor.BLUE,
				3);
		Test test = new Test(contract);
		String json = Serializer.toJson(test);
		Contract contract2 = ((Test) Serializer.fromJson(json, "it.castelli.Test")).bestContractEver;
		System.out.println(contract2 instanceof PropertyContract);
		// END TEST

//		Socket clientSocket = null;
//		try
//		{
////			clientSocket = new Socket("82.52.35.104", 1111);
//			clientSocket = new Socket("localhost", 1111);
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//		connection = new Connection(clientSocket);
//		launch(args);
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
