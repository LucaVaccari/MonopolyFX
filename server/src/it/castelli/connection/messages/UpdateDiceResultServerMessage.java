package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.dice.DiceResult;

public class UpdateDiceResultServerMessage implements Message
{
	private final DiceResult diceResult;

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
