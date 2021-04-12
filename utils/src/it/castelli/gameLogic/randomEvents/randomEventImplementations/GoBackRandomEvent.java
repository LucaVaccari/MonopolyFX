package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventType;

public class GoBackRandomEvent extends RandomEvent
{
	private final int numberOfSteps;

	public GoBackRandomEvent(String message, RandomEventType type, int numberOfSteps)
	{
		super(message, type);
		this.numberOfSteps = -numberOfSteps;
	}

	@Override
	public void applyEffect(Player player, GameManager manager)
	{
		player.move(numberOfSteps);
		manager.addRandomEvent(this, getType());
	}
}
