package it.castelli.gameLogic.randomEvents.randomEventImplementations;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.randomEvents.RandomEvent;

public class YouWonRandomEvent extends RandomEvent
{
    public YouWonRandomEvent(String message)
    {
        super(message);
    }

    @Override
    public void applyEffect(Player player)
    {

    }
}
