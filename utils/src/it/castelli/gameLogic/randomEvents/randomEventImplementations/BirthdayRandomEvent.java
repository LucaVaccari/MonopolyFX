package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventType;

public class BirthdayRandomEvent extends RandomEvent
{

	public BirthdayRandomEvent(String message, RandomEventType type)
	{
		super(message, type);
	}

	@Override
	public void applyEffect(Player player, GameManager manager)
	{
		player.addMoney((manager.getPlayers().size() - 1) * 10);
		for (Player element : manager.getPlayers())
		{
			if (!element.betterEquals(player))
				element.removeMoney(10);
		}
		manager.addRandomEvent(this, getType());
	}
}
