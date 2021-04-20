package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventType;

/**
 * Random event to make a player pay a certain amount based on the number of houses he owns
 */
public class PayHousesRandomEvent extends RandomEvent
{
	/**
	 * The cost to pay for each house
	 */
	private final int houseCost;

	/**
	 * The cost to pay for each hotel
	 */
	private final int hotelCost;

	/**
	 * Constructor for the PayHousesRandomEvent
	 *
	 * @param message The message shown to the player when he draws the card
	 * @param randomEventType The type of the random event
	 * @param houseCost The cost to pay for each house
	 * @param hotelCost The cost to pay for each hotel
	 */
	public PayHousesRandomEvent(String message, RandomEventType randomEventType, int houseCost, int hotelCost)
	{
		super(message, randomEventType);
		this.houseCost = houseCost;
		this.hotelCost = hotelCost;
	}

	/**
	 * Make the player pay a certain amount based on the number of houses he has
	 *
	 * @param player The player who must pay
	 * @param gameManager  The game manager of the game
	 */
	@Override
	public void applyEffect(Player player, GameManager gameManager)
	{
		int totalCost = 0;

		for (Contract contract : player.getContracts())
		{
			if (contract instanceof PropertyContract)
			{
				if (((PropertyContract) contract).getNumberOfHouses() < 5)
					totalCost += ((PropertyContract) contract).getNumberOfHouses() * houseCost;
				else
					totalCost += hotelCost;
			}
		}

		player.removeMoney(totalCost);
		gameManager.addRandomEvent(this, getType());
	}
}
