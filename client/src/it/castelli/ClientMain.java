package it.castelli;

import it.castelli.connection.Connection;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientMain extends Application
{
	public static void main(String[] args)
	{
		Socket clientSocket = null;
		try
		{
			clientSocket = new Socket(InetAddress.getLocalHost(), 1111);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Connection connection = new Connection(clientSocket);
		//launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		SceneManager.getInstance().setPrimaryStage(primaryStage);
		SceneManager.getInstance().showScene(SceneType.LOBBY);
	}
}
