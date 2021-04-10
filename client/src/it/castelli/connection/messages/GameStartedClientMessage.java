package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Platform;

/**
 * Message received from the server that starts the game (receive only)
 */
public class GameStartedClientMessage implements Message
{
	@Override
	public void onReceive(Connection connection, Player player)
	{
		System.out.println("Starting the game");
		Platform.runLater(() -> SceneManager.getInstance().showScene(SceneType.BOARD));
	}
}
