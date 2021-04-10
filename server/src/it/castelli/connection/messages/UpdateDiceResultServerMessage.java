package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.dice.DiceResult;

/**
 * Message to update the dice result (send only)
 */
public class UpdateDiceResultServerMessage implements Message
{
	/**
	 * The last dice result
	 */
	private final DiceResult diceResult;

	/**
	 * Constructor for UpdateDiceResultServerMessage
	 *
	 * @param diceResult The last dice result
	 */
	public UpdateDiceResultServerMessage(DiceResult diceResult)
	{
		this.diceResult = diceResult;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		// do nothing
	}
}
