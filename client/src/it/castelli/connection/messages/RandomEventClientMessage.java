package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message from the server that communicates the random event (receive only)
 */
public class RandomEventClientMessage implements Message
{
    /**
     * The random event text body
     */
    private final String randomEventText;

    /**
     * The random event type
     */
    private final String randomEventType;

    /**
     * Constructor for RandomEventClientMessage (do not use)
     *
     * @param randomEventText The random event text body
     * @param randomEventType The random event type
     */
    public RandomEventClientMessage(String randomEventText, String randomEventType)
    {
        this.randomEventText = randomEventText;
        this.randomEventType = randomEventType;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        Player activePlayer = Game.getGameManager().getCurrentRound().getCurrentActivePlayer();
        //TODO: show a window for the random event
    }
}
