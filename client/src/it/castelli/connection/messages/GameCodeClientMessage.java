package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Platform;

/**
 * Game code info message (receive only)
 */
public class GameCodeClientMessage implements Message
{
	private final int gameCode;

	/**
	 * Constructor for GameCodeClientMessage
	 *
	 * @param gameCode game code
	 */
	public GameCodeClientMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	/**
	 * Inform the Client about the game code
	 *
	 * @param connection connection of the player
	 * @param player     player
	 */
	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.setGameCode(gameCode);
		Platform.runLater(() -> SceneManager.getInstance().showScene(SceneType.LOBBY));
	}
}
