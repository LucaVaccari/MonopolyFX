package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;

public class TaxSquare implements Square
{
	private int value;
	private String message;

	public TaxSquare(int value, String message)
	{
		this.value = value;
		this.message = message;
	}

	public int getValue()
	{
		return value;
	}

	public String getMessage()
	{
		return message;
	}

	@Override
	public void interact(Player player)
	{
		// TODO: take money from the player
	}
}
