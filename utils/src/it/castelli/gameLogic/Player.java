package it.castelli.gameLogic;

import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.randomEvents.RandomEvent;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * A player of the game
 */
public class Player
{
	/**
	 * The list of his contracts
	 */
	private final HashSet<Contract> contracts = new HashSet<>();
	/**
	 * All kept RandomEvent cards (like PrisonEscape)
	 */
	private final ArrayList<RandomEvent> keptRandomEventCards = new ArrayList<>();
	/**
	 * The name of the player
	 */
	private final String name;
	/**
	 * The amount of money the player has
	 */
	private int money;
	/**
	 * The position of the player on the board
	 */
	private int position = 0;

	/**
	 * Constructor for the Player
	 *
	 * @param money The amount of money the player has
	 * @param name  The name of the player
	 */
	public Player(int money, String name)
	{
		this.money = money;
		this.name = name;
	}

	/**
	 * Getter for the position
	 *
	 * @return The position of the player on the board
	 */
	public int getPosition()
	{
		return position;
	}

	/**
	 * Getter for the name
	 *
	 * @return The name of the player
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Getter for the list of contracts
	 *
	 * @return The list of contracts owned by the player
	 */
	public HashSet<Contract> getContracts()
	{
		return contracts;
	}

	/**
	 * Getter for the money
	 *
	 * @return The amount of money the player has
	 */
	public int getMoney()
	{
		return money;
	}

	/**
	 * Getter for the list of kept random event cards
	 *
	 * @return The list of kept random event cards
	 */
	public ArrayList<RandomEvent> getKeptRandomEventCards()
	{
		return keptRandomEventCards;
	}

	/**
	 * Set the position of the player by specifying it precisely
	 *
	 * @param position            The new position for the player
	 * @param passThroughGoSquare If it passes through the GoSquare, should it get paid?
	 */
	public void setPosition(int position, boolean passThroughGoSquare)
	{
		this.position = position;
		if (position >= 40)
			;// TODO: give money to player
		this.position %= 40;
		// TODO: GameManager.getSquare(position).interact();
	}

	/**
	 * Move the player towards by a certain amount (after throwing the dice or a random event)
	 *
	 * @param units The number of squares to move
	 */
	public void move(int units)
	{
		setPosition(position + units, true);
	}

	/**
	 * Add money to the player
	 *
	 * @param x The amount of money to add
	 */
	// TODO: change x name
	public void addMoney(int x)
	{
		money += x;
	}

	/**
	 * Take money from the player
	 *
	 * @param x The amount of money to take
	 */
	// TODO: change x name
	public void removeMoney(int x)
	{
		if (money >= x)
			money -= x;
	}

	/**
	 * Check if he has more money than those passed
	 *
	 * @param x The amount of money to compare
	 * @return If the player has enough money
	 */
	// TODO: change x name
	public boolean hasMoney(int x)
	{
		return money >= x;
	}
}
