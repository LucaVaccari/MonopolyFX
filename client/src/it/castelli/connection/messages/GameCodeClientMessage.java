package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Platform;

public class GameCodeClientMessage implements Message
{
	private final int code;

	public GameCodeClientMessage(int code)
	{
		this.code = code;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.setGameCode(code);
		Platform.runLater(() -> SceneManager.getInstance().showScene(SceneType.LOBBY));
	}
}
