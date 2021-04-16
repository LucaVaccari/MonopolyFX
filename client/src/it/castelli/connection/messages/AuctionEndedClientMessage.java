package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Platform;

/**
 * Message that closes the auction window (receive only)
 */
public class AuctionEndedClientMessage implements Message
{
	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.getGameManager().setAuction(null);
		Platform.runLater(() -> SceneManager.getInstance().getStageByType(SceneType.AUCTION).close());
	}
}
