package it.castelli.gameLogic.dice;

import java.util.Random;

/**
 * Class for getting two random numbers between 1 and 6
 */
public class Dice
{
	/**
	 * Get a roll of dice
	 *
	 * @return The result of the roll
	 */
	public static DiceResult rollDice()
	{
		Random random = new Random();
		return new DiceResult(random.nextInt(6) + 1, random.nextInt(6) + 1);
	}
}
