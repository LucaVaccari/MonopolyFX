package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventManager;
import it.castelli.gameLogic.randomEvents.RandomEventType;

/**
 * Random event to make a player pay a certain amount based on the number of
 * houses he owns
 */
public class PayHousesRandomEvent extends RandomEvent
{
	private final int houseCost;
	private final int hotelCost;

	/**
	 * Constructor for the PayHousesRandomEvent
	 *
	 * @param message The message shown to the player when he draws the card
	 */
	public PayHousesRandomEvent(String message, RandomEventManager randomEventManager, RandomEventType randomEventType, int houseCost, int hotelCost)
	{
		super(message, randomEventManager, randomEventType);
		this.houseCost = houseCost;
		this.hotelCost = hotelCost;
	}

	/**
	 * Make the player pay a certain amount based on the number of houses he
	 * has
	 *
	 * @param player The player who must pay
	 */
	@Override
	public void applyEffect(Player player)
	{
		int totalCost = 0;

		for (Contract contract : player.getContracts())
		{
			if (contract instanceof PropertyContract)
			{
				if (((PropertyContract) contract).getNumberOfHouses() < 5)
				{
					totalCost += ((PropertyContract) contract).getNumberOfHouses() * houseCost;
				}
				else
				{
					totalCost += hotelCost;
				}
			}
		}

		player.removeMoney(totalCost);
		if (super.getType() == RandomEventType.CHANCE)
			super.getRandomEventManager().addChance(this);
		else
			super.getRandomEventManager().addCommunityChest(this);
	}
}
