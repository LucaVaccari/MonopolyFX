package it.castelli.gameLogic.squares;

import it.castelli.gameLogic.Player;

/**
 * Square which makes the player pay a tax
 */
public class TaxSquare implements Square
{
	/**
	 * The amount to be payed
	 */
	private final int value;
	/**
	 * The message shown to the player when landing on the square
	 */
	private final String message;

	/**
	 * The constructor of the TaxSquare
	 *
	 * @param value   The amount to be payed
	 * @param message The message shown to the player when landing on the square
	 */
	public TaxSquare(int value, String message)
	{
		this.value = value;
		this.message = message;
	}

	/**
	 * Getter for the value
	 *
	 * @return The amount to be payed
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * Getter for the message
	 *
	 * @return The message shown to the player when landing on the square
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Make the player pay the tax
	 *
	 * @param player The player who landed on the square
	 */
	@Override
	public void interact(Player player)
	{
		// TODO: take money from the player
	}
}
