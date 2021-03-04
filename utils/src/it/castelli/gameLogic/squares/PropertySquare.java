package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.PropertyContract;

public class PropertySquare implements Square
{
	private PropertyContract contract;

	public PropertySquare(PropertyContract contract)
	{
		this.contract = contract;
	}

	public PropertyContract getContract()
	{
		return contract;
	}

	@Override
	public void interact(Player player)
	{
		// TODO: invoke property method
	}
}
