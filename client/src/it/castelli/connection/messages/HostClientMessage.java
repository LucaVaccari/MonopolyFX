package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.controllers.LobbyController;

public class HostClientMessage implements Message
{
	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.setHost(true);
		System.out.println("Host");
		LobbyController.getInstance().showPlayButton();
	}
}
