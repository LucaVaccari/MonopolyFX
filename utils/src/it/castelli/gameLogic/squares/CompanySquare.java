package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.CompanyContract;

public class CompanySquare implements Square
{
	private CompanyContract contract;

	public CompanySquare(CompanyContract contract)
	{
		this.contract = contract;
	}

	public CompanyContract getContract()
	{
		return contract;
	}

	@Override
	public void interact(Player player)
	{
		// TODO: invoke company method
	}
}
