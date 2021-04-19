package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.controllers.LobbyController;
import javafx.application.Platform;

/**
 * Message from the server that sets the player as the game host (receive only)
 */
public class SetHostClientMessage implements Message
{
	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.setHost(true);
		System.out.println("Host");
		Platform.runLater(() -> LobbyController.getInstance().update());
	}
}
