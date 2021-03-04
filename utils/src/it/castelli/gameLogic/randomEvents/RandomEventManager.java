package it.castelli.gameLogic.randomEvents;

import java.util.LinkedList;

public class RandomEventManager
{
    private LinkedList<RandomEvent> chances = new LinkedList<>();
    private LinkedList<RandomEvent> communityChests = new LinkedList<>();

    public RandomEvent drawChance()
    {
        return chances.poll();
    }

    public RandomEvent drawCommunityChest()
    {
        return communityChests.poll();
    }

    public void addChance(RandomEvent chance)
    {
        chances.add(chance);
    }

    public void addCommunityChest(RandomEvent communityChest)
    {
        communityChests.add(communityChest);
    }
}
