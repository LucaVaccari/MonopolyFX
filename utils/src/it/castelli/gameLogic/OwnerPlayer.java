package it.castelli.gameLogic;

import it.castelli.gameLogic.randomEvents.RandomEvent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A player of the game
 */
public class OwnerPlayer
{
    /**
     * All kept RandomEvent cards (like PrisonEscape)
     */
    private final ArrayList<RandomEvent> keptRandomEventCards;
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
     * The previous position of the player
     */
    private int previousPosition;

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
     * @param player The player from which to copy the information
     */
    public OwnerPlayer(Player player)
    {
        this.money = player.getMoney();
        this.name = player.getName();
        this.inPrison = player.isInPrison();
        this.position = player.getPosition();
        this.previousPosition = player.getPreviousPosition();
        this.pawn = player.getPawn();
        this.keptRandomEventCards = player.getKeptRandomEventCards();
        this.randomEventDescription = player.getRandomEventDescription();
        this.randomEventType = player.getRandomEventType();

    }

    public Player toPlayer()
    {
        Player player = new Player(this.money, this.name);
        player.setPreviousPosition(this.previousPosition);
        player.setPosition(this.position, false);
        player.setInPrison(this.inPrison);
        player.setPawn(this.pawn);
        player.setLastRandomEvent(this.randomEventType, this.randomEventDescription);
        player.getKeptRandomEventCards().addAll(this.getKeptRandomEventCards());
        return player;
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
     * Getter for the money
     *
     * @return The amount of money the player has
     */
    public int getMoney()
    {
        return money;
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
        setPreviousPosition(this.position);
        this.position = position;
        if (position >= 40 && passThroughGoSquare)
            addMoney(200);
        this.position %= 40;
    }

    /**
     * Set the previous position of the player
     *
     * @param previousPosition The new position for the player
     *
     */
    public void setPreviousPosition(int previousPosition)
    {
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
        if (obj instanceof OwnerPlayer)
        {
            OwnerPlayer other = (OwnerPlayer) obj;
            return this.money == other.money && this.name.equals(other.name) && this.position == other.position &&
                    this.inPrison == other.inPrison && this.pawn == other.pawn;
        }
        return false;
    }

    /**
     * Tells if two players are actually equals
     *
     * @param obj the other player
     * @return if the two players are the same one
     */
    public boolean betterEquals(Object obj)
    {
        if (obj instanceof OwnerPlayer)
        {
            OwnerPlayer other = (OwnerPlayer) obj;
            return this.name.equals(other.name) && this.pawn == other.pawn;
        }
        return false;
    }
}
