package it.castelli;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.contracts.PropertyColor;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.squares.PropertySquare;
import it.castelli.gameLogic.squares.Square;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.application.Application;
import javafx.stage.Stage;

class Test
{
	private final Square square;

	public Test(Square square)
	{
		this.square = square;
	}

	public Square getSquare()
	{
		return square;
	}
}

public class ClientMain extends Application
{
	private static Connection connection;

	public static void main(String[] args)
	{
		Square square = new PropertySquare(new PropertyContract("SUS",
				1,
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				PropertyColor.BLUE,
				5));
		Test test = new Test(square);
		String s = Serializer.toJson(test);
		System.out.println(s);
		Test newTest = (Test) Serializer.fromJson(s, "it.castelli.Test");

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
