package it.castelli.gameLogic;

import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.randomEvents.RandomEvent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A player of the game
 */
public class Player
{
	/**
	 * The list of his contracts
	 */
	private final ArrayList<Contract> contracts = new ArrayList<>();
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
	 * Is the player in prison?
	 */
	private boolean inPrison = false;
	/**
	 * the player's pawn
	 */
	private Pawn pawn;

	/**
	 * The type of the last random event the player encountered
	 */
	private String randomEventType = null;

	/**
	 * The description of the last random event the player encountered
	 */
	private String randomEventDescription = null;

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
	 * Getter for the list of contracts (do NOT use to modify the player contracts)
	 *
	 * @return The list of contracts owned by the player
	 */
	public ArrayList<Contract> getContracts()
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
	 * Add the given contract to the player contracts
	 *
	 * @param contract the contract to add
	 */
	public void addContract(Contract contract)
	{
		contract.setOwner(this);
		contracts.add(contract);
	}

	/**
	 * Remove the given contract of the player contracts
	 *
	 * @param contract the contract to remove
	 */
	public void removeContract(Contract contract)
	{
		contracts.remove(contract);
	}

	/**
	 * Getter for inPrison
	 *
	 * @return Is the player in prison?
	 */
	public boolean isInPrison()
	{
		return inPrison;
	}

	public void setInPrison(boolean inPrison)
	{
		this.inPrison = inPrison;
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
	 * @param passThroughGoSquare If it passes through the GoSquare, should it
	 *                            get paid?
	 */
	public void setPosition(int position, boolean passThroughGoSquare)
	{
		this.position = position;
		if (position >= 40)
			addMoney(200);// TODO: give money to player
		this.position %= 40;
		// TODO: GameManager.getSquare(position).interact();
	}

	/**
	 * Move the player towards by a certain amount (after throwing the dice or
	 * a random event)
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

	/**
	 * Getter for the pawn
	 *
	 * @return The player pawn
	 */
	public Pawn getPawn()
	{
		return pawn;
	}

	/**
	 * Getter for randomEventType
	 *
	 * @return The type of the last random event the player encountered
	 */
	public String getRandomEventType()
	{
		return randomEventType;
	}

	/**
	 * Set the last encountered random event type
	 *
	 * @param randomEventType the last encountered random event type
	 */
	private void setRandomEventType(String randomEventType)
	{
		this.randomEventType = randomEventType;
	}

	/**
	 * Getter for randomEventDescription
	 *
	 * @return The description of the last random event the player encountered
	 */
	public String getRandomEventDescription()
	{
		return randomEventDescription;
	}

	/**
	 * Set the last encountered random event description
	 *
	 * @param randomEventDescription the last encountered random event description
	 */
	private void setRandomEventDescription(String randomEventDescription)
	{
		this.randomEventDescription = randomEventDescription;
	}

	public void setLastRandomEvent(String randomEventType, String randomEventDescription)
	{
		setRandomEventType(randomEventType);
		setRandomEventDescription(randomEventDescription);
	}


	/**
	 * Setter for the pawn
	 *
	 * @param pawn The new player pawn
	 */
	public void setPawn(Pawn pawn)
	{
		this.pawn = pawn;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * <p>
	 * The {@code equals} method implements an equivalence relation
	 * on non-null object references:
	 * <ul>
	 * <li>It is <i>reflexive</i>: for any non-null reference value
	 *     {@code x}, {@code x.equals(x)} should return
	 *     {@code true}.
	 * <li>It is <i>symmetric</i>: for any non-null reference values
	 *     {@code x} and {@code y}, {@code x.equals(y)}
	 *     should return {@code true} if and only if
	 *     {@code y.equals(x)} returns {@code true}.
	 * <li>It is <i>transitive</i>: for any non-null reference values
	 *     {@code x}, {@code y}, and {@code z}, if
	 *     {@code x.equals(y)} returns {@code true} and
	 *     {@code y.equals(z)} returns {@code true}, then
	 *     {@code x.equals(z)} should return {@code true}.
	 * <li>It is <i>consistent</i>: for any non-null reference values
	 *     {@code x} and {@code y}, multiple invocations of
	 *     {@code x.equals(y)} consistently return {@code true}
	 *     or consistently return {@code false}, provided no
	 *     information used in {@code equals} comparisons on the
	 *     objects is modified.
	 * <li>For any non-null reference value {@code x},
	 *     {@code x.equals(null)} should return {@code false}.
	 * </ul>
	 * <p>
	 * The {@code equals} method for class {@code Object} implements
	 * the most discriminating possible equivalence relation on objects;
	 * that is, for any non-null reference values {@code x} and
	 * {@code y}, this method returns {@code true} if and only
	 * if {@code x} and {@code y} refer to the same object
	 * ({@code x == y} has the value {@code true}).
	 * <p>
	 * Note that it is generally necessary to override the {@code hashCode}
	 * method whenever this method is overridden, so as to maintain the
	 * general contract for the {@code hashCode} method, which states
	 * that equal objects must have equal hash codes.
	 *
	 * @param obj the reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj
	 * argument; {@code false} otherwise.
	 * @see #hashCode()
	 * @see HashMap
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Player)
		{
			Player other = (Player) obj;
			return this.money == other.money && this.name.equals(other.name) && this.position == other.position &&
			       this.inPrison == other.inPrison && this.pawn == other.pawn;
		}
		return false;
	}
}
