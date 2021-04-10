package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Platform;

public class StartGameClientMessage implements Message
{
	private final int gameCode;

	public StartGameClientMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		System.out.println("Starting the game");
		Platform.runLater(() -> SceneManager.getInstance().showScene(SceneType.BOARD));
	}
}
