package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Message to update the players list in the game (send only)
 */
public class UpdatePlayersListServerMessage implements Message
{
	/**
	 * The updated player list
	 */
	private final CopyOnWriteArrayList<Player> players;

	/**
	 * Constructor for UpdatePlayersListServerMessage
	 *
	 * @param players The updated player list
	 */
	public UpdatePlayersListServerMessage(CopyOnWriteArrayList<Player> players)
	{
		this.players = players;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
