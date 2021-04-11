package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventManager;
import it.castelli.gameLogic.randomEvents.RandomEventType;

public class GoBackRandomEvent extends RandomEvent
{
	private final int numberOfSteps;

	public GoBackRandomEvent(String message, RandomEventManager randomEventManager, RandomEventType type, int numberOfSteps)
	{
		super(message, randomEventManager, type);
		this.numberOfSteps = -numberOfSteps;
	}

	@Override
	public void applyEffect(Player player)
	{
		player.move(numberOfSteps);
		if (super.getType() == RandomEventType.CHANCE)
			super.getRandomEventManager().addChance(this);
		else
			super.getRandomEventManager().addCommunityChest(this);

	}
}
