package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.Round;

/**
 * Message to update the current round (send only)
 */
public class UpdateRoundServerMessage implements Message
{
	/**
	 * The new round
	 */
	private final Round round;

	/**
	 * Constructor for UpdateRoundServerMessage
	 *
	 * @param round The new round
	 */
	public UpdateRoundServerMessage(Round round)
	{
		this.round = round;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
