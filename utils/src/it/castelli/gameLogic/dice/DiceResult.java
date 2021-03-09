package it.castelli.gameLogic.dice;

/**
 * Class for holding the result of a roll of dice
 */
public class DiceResult
{
	/**
	 * The result of the first die
	 */
	private final int firstResult;
	/**
	 * The result of the second die
	 */
	private final int secondResult;

	/**
	 * Constructor of the dice result
	 * @param result1 The result of the first die
	 * @param result2 The result of the second die
	 */
	public DiceResult(int result1, int result2)
	{
		this.firstResult = result1;
		this.secondResult = result2;
	}

	/**
	 * Getter for the first result
	 * @return The result of the first die
	 */
	public int getResult1()
	{
		return firstResult;
	}

	/**
	 * Getter for the second result
	 * @return The result of the second die
	 */
	public int getSecondResult()
	{
		return secondResult;
	}
}
