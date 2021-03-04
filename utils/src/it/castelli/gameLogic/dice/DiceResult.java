package it.castelli.gameLogic.dice;

public class DiceResult
{
	private final int firstResult;
	private final int secondResult;

	public DiceResult(int result1, int result2)
	{
		this.firstResult = result1;
		this.secondResult = result2;
	}

	public int getResult1()
	{
		return firstResult;
	}

	public int getSecondResult()
	{
		return secondResult;
	}
}
