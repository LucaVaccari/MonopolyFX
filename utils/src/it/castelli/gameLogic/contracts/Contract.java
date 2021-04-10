package it.castelli.gameLogic.contracts;

import it.castelli.gameLogic.Player;

import java.util.HashMap;

/**
 * Represents the properties of a Monopoly property
 */
public abstract class Contract
{
	/**
	 * Name of the Monopoly property
	 */
	protected String name;
	/**
	 * The cost of the property when buying it from the bank
	 */
	protected int value;
	/**
	 * The revenue of the property when a player lands on it
	 */
	protected int revenue;
	/**
	 * The profit of mortgaging the property
	 */
	protected int mortgageValue;
	/**
	 * The player who owns the property
	 */
	protected Player owner;
	/**
	 * Is the property mortgaged?
	 */
	protected boolean mortgaged;

	/**
	 * Getter for the name of the property
	 *
	 * @return Name of the Monopoly property
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Getter for the value
	 *
	 * @return The cost of the property when buying it from the bank
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * Getter for the mortgage value
	 *
	 * @return The profit of mortgaging the property
	 */
	public int getMortgageValue()
	{
		return mortgageValue;
	}

	/**
	 * Getter for the owner
	 *
	 * @return The player who owns the property
	 */
	public Player getOwner()
	{
		return owner;
	}

	/**
	 * Setter for the player
	 *
	 * @param owner The new player who owns the property
	 */
	public void setOwner(Player owner)
	{
		this.owner = owner;
	}

	/**
	 * Getter fot mortgaged
	 *
	 * @return Is the property mortgaged?
	 */
	public boolean isMortgaged()
	{
		return mortgaged;
	}

	/**
	 * Setter for mortgaged
	 *
	 * @param mortgaged The new mortgaged value
	 */
	public void setMortgaged(boolean mortgaged)
	{
		this.mortgaged = mortgaged;
	}

	/**
	 * Abstract getter for revenue
	 *
	 * @return The revenue of the property when a player lands on it
	 */
	public abstract int getRevenue();

	/**
	 * Returns a hash code value for the object. This method is supported for
	 * the benefit of hash
	 * tables such as those provided by {@link HashMap}.
	 * <p>
	 * The general contract of {@code hashCode} is:
	 * <ul>
	 * <li>Whenever it is invoked on the same object more than once during
	 *     an execution of a Java application, the {@code hashCode} method
	 *     must consistently return the same integer, provided no information
	 *     used in {@code equals} comparisons on the object is modified.
	 *     This integer need not remain consistent from one execution of an
	 *     application to another execution of the same application.
	 * <li>If two objects are equal according to the {@code equals(Object)}
	 *     method, then calling the {@code hashCode} method on each of
	 *     the two objects must produce the same integer result.
	 * <li>It is <em>not</em> required that if two objects are unequal
	 *     according to the {@link Object#equals(Object)}
	 *     method, then calling the {@code hashCode} method on each of the
	 *     two objects must produce distinct integer results.  However, the
	 *     programmer should be aware that producing distinct integer results
	 *     for unequal objects may improve the performance of hash tables.
	 * </ul>
	 *
	 * @return a hash code value for this object.
	 * @implSpec As far as is reasonably practical, the {@code hashCode}
	 * method defined by class
	 * {@code Object} returns distinct integers for distinct objects.
	 * @see Object#equals(Object)
	 * @see System#identityHashCode
	 */
	@Override
	public int hashCode()
	{
		return name.length() + value * mortgageValue;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * <p>
	 * The {@code equals} method implements an equivalence relation on
	 * non-null object references:
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
	 * @return {@code true} if this object is the same as the obj argument;
	 * {@code false}
	 * otherwise.
	 * @see #hashCode()
	 * @see HashMap
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Contract))
			return false;

		return name.equals(((Contract) obj).name) &&
				value == ((Contract) obj).value &&
				revenue == ((Contract) obj).revenue &&
				mortgageValue == ((Contract) obj).mortgageValue;
	}
}
