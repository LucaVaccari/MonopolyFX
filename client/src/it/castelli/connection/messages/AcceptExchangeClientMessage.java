package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class AcceptExchangeClientMessage implements Message
{
	private final Player player;
	private final int gameCode;

	public AcceptExchangeClientMessage(Player player, int gameCode)
	{
		this.player = player;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
