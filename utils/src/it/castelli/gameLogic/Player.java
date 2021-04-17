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
	 * Something changed in the player?
	 */
	private boolean somethingChanged = false;

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
	 * The type of the last event the player encountered
	 */
	private String eventType = null;

	/**
	 * The description of the last event the player encountered
	 */
	private String eventDescription = null;

	/**
	 * The previous position of the player
	 */
	private int previousPosition;

	/**
	 * The number of vote to kick this player
	 */
	private int numberOfVotes = 0;

	/**
	 * Getter for previousPosition
	 *
	 * @return The previous position of the player
	 */
	public int getPreviousPosition()
	{
		return previousPosition;
	}

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
		somethingChanged = true;
		if (!contracts.contains(contract))
		{
			contract.setOwner(this);
			contracts.add(contract);
		}
	}

	/**
	 * Getter for somethingChanged
	 *
	 * @return Something changed in the player?
	 */
	public boolean hasSomethingChanged()
	{
		return somethingChanged;
	}

	/**
	 * Setter for somethingChanged
	 *
	 * @param somethingChanged if something has changed or is marked as applied
	 */
	public void setSomethingChanged(boolean somethingChanged)
	{
		this.somethingChanged = somethingChanged;
	}

	/**
	 * Remove the given contract of the player contracts
	 *
	 * @param contract the contract to remove
	 */
	public void removeContract(Contract contract)
	{
		somethingChanged = true;
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

	/**
	 * Setter for inPrison
	 *
	 * @param inPrison Is the player in prison?
	 */
	public void setInPrison(boolean inPrison)
	{
		somethingChanged = true;
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
		somethingChanged = true;
		setPreviousPosition(this.position);
		this.position = position;
		if (position >= 40 && passThroughGoSquare)
			addMoney(200);
		this.position %= 40;
	}

	/**
	 * Getter for numberOfVOtes
	 *
	 * @return The number of vote to kick this player
	 */
	public int getNumberOfVotes()
	{
		return numberOfVotes;
	}

	/**
	 * Adds a vote to the player votes
	 */
	public void addVote()
	{
		this.numberOfVotes++;
	}

	/**
	 * Set the previous position of the player
	 *
	 * @param previousPosition The new position for the player
	 *
	 */
	public void setPreviousPosition(int previousPosition)
	{
		somethingChanged = true;
		this.previousPosition = previousPosition;
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
	public void addMoney(int x)
	{
		somethingChanged = true;
		money += x;
	}

	/**
	 * Take money from the player
	 *
	 * @param x The amount of money to take
	 */
	public void removeMoney(int x)
	{
		somethingChanged = true;
		money -= x;
	}

	/**
	 * Check if he has more money than those passed
	 *
	 * @param x The amount of money to compare
	 * @return If the player has enough money
	 */
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
	public String getEventType()
	{
		return eventType;
	}

	/**
	 * Set the last encountered random event type
	 *
	 * @param eventType the last encountered random event type
	 */
	private void setEventType(String eventType)
	{
		somethingChanged = true;
		this.eventType = eventType;
	}

	/**
	 * Getter for randomEventDescription
	 *
	 * @return The description of the last random event the player encountered
	 */
	public String getEventDescription()
	{
		return eventDescription;
	}

	/**
	 * Set the last encountered random event description
	 *
	 * @param eventDescription the last encountered random event description
	 */
	private void setEventDescription(String eventDescription)
	{
		somethingChanged = true;
		this.eventDescription = eventDescription;
	}

	public void setLastEncounteredEvent(String randomEventType, String randomEventDescription)
	{
		somethingChanged = true;
		setEventType(randomEventType);
		setEventDescription(randomEventDescription);
	}

	/**
	 * Setter for the pawn
	 *
	 * @param pawn The new player pawn
	 */
	public void setPawn(Pawn pawn)
	{
		somethingChanged = true;
		this.pawn = pawn;
	}

	/**
	 * Indicates whether some other object is "equal to" this one. (do not use)
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

	/**
	 * Like equals, but better
	 *
	 * @param obj the other player
	 * @return if the two players are the same one
	 */
	public boolean betterEquals(Object obj)
	{
		if (obj instanceof Player)
		{
			Player other = (Player) obj;
			return this.name.equals(other.name) && this.pawn == other.pawn;
		}
		return false;
	}

	/**
	 * Returns a string representation of the object. In general, the
	 * {@code toString} method returns a string that
	 * "textually represents" this object. The result should
	 * be a concise but informative representation that is easy for a
	 * person to read.
	 * It is recommended that all subclasses override this method.
	 * <p>
	 * The {@code toString} method for class {@code Object}
	 * returns a string consisting of the name of the class of which the
	 * object is an instance, the at-sign character `{@code @}', and
	 * the unsigned hexadecimal representation of the hash code of the
	 * object. In other words, this method returns a string equal to the
	 * value of:
	 * <blockquote>
	 * <pre>
	 * getClass().getName() + '@' + Integer.toHexString(hashCode())
	 * </pre></blockquote>
	 *
	 * @return a string representation of the object.
	 */
	@Override
	public String toString()
	{
		return getName();
	}
}
