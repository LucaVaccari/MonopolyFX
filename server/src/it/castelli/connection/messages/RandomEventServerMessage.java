package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message to communicate te client the random event (send only)
 */
public class RandomEventServerMessage implements Message
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
     * Constructor for RandomEventServerMessage
     *
     * @param randomEventType The random event type
     * @param randomEventText The random event text body
     */
    public RandomEventServerMessage(String randomEventType, String randomEventText)
    {
        this.randomEventType = randomEventType;
        this.randomEventText = randomEventText;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
