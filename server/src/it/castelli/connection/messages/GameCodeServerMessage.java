package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
/**
 * Game code info message (send only)
 */
public class GameCodeServerMessage implements Message
{
	private int code;

	/**
	 * Constructor for GameCodeServerMessage
	 * @param code game code
	 */
	public GameCodeServerMessage(int code)
	{
		this.code = code;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		//do nothing
	}
}
