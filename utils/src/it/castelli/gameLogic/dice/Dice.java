package it.castelli.gameLogic.dice;

import java.util.Random;

public class Dice
{
	public static DiceResult rollDice()
	{
		Random random = new Random();
		return new DiceResult(random.nextInt(6) + 1, random.nextInt(6) + 1);
	}
}
