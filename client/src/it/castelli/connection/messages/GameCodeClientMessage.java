package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.controllers.AuctionController;
import it.castelli.gui.controllers.BoardController;
import it.castelli.gui.controllers.ExchangeController;
import it.castelli.gui.controllers.LobbyController;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Platform;

/**
 * Message containing the game Code (receive only)
 */
public class GameCodeClientMessage implements Message
{
	/**
	 * The game Code
	 */
	private final int gameCode;

	/**
	 * Constructor for GameCodeClientMessage (do not use)
	 *
	 * @param gameCode The game Code
	 */
	public GameCodeClientMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.setGameCode(gameCode);
		Platform.runLater(() -> {
			SceneManager.getInstance().showScene(SceneType.PAWN_CHOICE);
			LobbyController.getInstance().update();

			// clear all chats
			LobbyController.getInstance().getChat().clear();
			BoardController.getInstance().getChat().clear();
			AuctionController.getInstance().getChat().clear();
			ExchangeController.getInstance().getChat().clear();
		});
	}
}
