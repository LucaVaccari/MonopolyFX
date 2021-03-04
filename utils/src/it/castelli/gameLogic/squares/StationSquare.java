package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.StationContract;

public class StationSquare implements Square
{
	private StationContract contract;

	public StationSquare(StationContract contract)
	{
		this.contract = contract;
	}

	public StationContract getContract()
	{
		return contract;
	}

	@Override
	public void interact(Player player)
	{
		// TODO: invoke station method
	}
}
