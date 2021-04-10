package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Players in the game info message (send only)
 */
public class UpdatePlayersListServerMessage implements Message
{
	private CopyOnWriteArrayList<Player> players;

	/**
	 * Constructor for GameManagerPlayersServerMessage
	 *
	 * @param players players of the game
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
