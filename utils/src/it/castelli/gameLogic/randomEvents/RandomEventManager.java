package it.castelli.gameLogic.randomEvents;

import java.util.LinkedList;

/**
 * Class to handle the decks of RandomEvents
 */
public class RandomEventManager
{
	/**
	 * The list of chances
	 */
	private LinkedList<RandomEvent> chances = new LinkedList<>();
	/**
	 * The list of community chests
	 */
	private LinkedList<RandomEvent> communityChests = new LinkedList<>();

	/**
	 * Draw a chance card (removes it from the list and returns it)
	 *
	 * @return A chance card
	 */
	public RandomEvent drawChance()
	{
		return chances.poll();
	}

	/**
	 * Draw a community chest card (removes it from the list and returns it)
	 *
	 * @return A community chest
	 */
	public RandomEvent drawCommunityChest()
	{
		return communityChests.poll();
	}

	/**
	 * Puts back a chance card in the list
	 *
	 * @param chance The chance card to be put back
	 */
	public void addChance(RandomEvent chance)
	{
		chances.add(chance);
	}

	/**
	 * Puts back a community chest card in the list
	 *
	 * @param communityChest The community chest card to be put back
	 */
	public void addCommunityChest(RandomEvent communityChest)
	{
		communityChests.add(communityChest);
	}
}
