package it.castelli.gameLogic;

import it.castelli.gameLogic.dice.Dice;
import it.castelli.gameLogic.dice.DiceResult;

/**
 * The round class
 */
public class Round
{
	/**
	 * The player who is playing in the current round
	 */
	private final Player currentActivePlayer;

	/**
	 * The index of the current player
	 */
	private final int playerIndex;

	/**
	 * Parameter which say if dice has been thrown
	 */
	private boolean diceThrown = false;

	/**
	 * The result of the dice throw
	 */
	private DiceResult diceResult;

	/**
	 * Constructor for Round class
	 *
	 * @param currentActivePlayer The player who is playing in the current round
	 * @param playerIndex The index of the current player
	 */
	public Round(Player currentActivePlayer, int playerIndex)
	{
		this.currentActivePlayer = currentActivePlayer;
		this.playerIndex = playerIndex;
	}

	/**
	 * Getter for the currentActivePlayer
	 * @return The player who is playing in the current round
	 */
	public Player getCurrentActivePlayer()
	{
		return currentActivePlayer;
	}

	/**
	 * Checks if dice has been thrown
	 *
	 * @return Parameter which say if dice has been thrown
	 */
	public boolean isDiceThrown()
	{
		return diceThrown;
	}

	/**
	 * Set the diceThrown
	 *
	 * @param diceThrown Parameter which say if dice has been thrown
	 */
	public void setDiceThrown(boolean diceThrown)
	{
		this.diceThrown = diceThrown;
	}

	/**
	 * Getter for the playerIndex
	 *
	 * @return The index of the current player
	 */
	public int getPlayerIndex()
	{
		return playerIndex;
	}

	/**
	 * Getter for the diceResult
	 *
	 * @return The result of the dice throw
	 */
	public DiceResult getDiceResult()
	{
		return diceResult;
	}

	/**
	 * Throws the dice
	 */
	public void throwDice()
	{
		diceResult = Dice.rollDice();
		diceThrown = !diceResult.areResultsEquals();
	}
}
