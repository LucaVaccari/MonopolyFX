package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;

public class GoBackRandomEvent extends RandomEvent
{
	private final int numberOfSteps;

	public GoBackRandomEvent(String message, int numberOfSteps)
	{
		super(message);
		this.numberOfSteps = numberOfSteps;
	}

	@Override
	public void applyEffect(Player player)
	{
		// TODO: implement
	}
}
