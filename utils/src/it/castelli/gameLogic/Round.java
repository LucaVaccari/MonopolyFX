package it.castelli.gameLogic;

import it.castelli.gameLogic.dice.Dice;
import it.castelli.gameLogic.dice.DiceResult;

public class Round
{
	private final Player currentActivePlayer;
	private final int playerIndex;
	private boolean diceThrown = false;
	private DiceResult diceResult;

	public Round(Player currentActivePlayer, int playerIndex)
	{
		this.currentActivePlayer = currentActivePlayer;
		this.playerIndex = playerIndex;
	}

	public Player getCurrentActivePlayer()
	{
		return currentActivePlayer;
	}

	public boolean isDiceThrown()
	{
		return diceThrown;
	}

	public void setDiceThrown(boolean diceThrown)
	{
		this.diceThrown = diceThrown;
	}

	public int getPlayerIndex()
	{
		return playerIndex;
	}

	public DiceResult getDiceResult()
	{
		return diceResult;
	}

	public void throwDice()
	{
		diceResult = Dice.rollDice();
		diceThrown = !diceResult.areResultsEquals();
	}
}
