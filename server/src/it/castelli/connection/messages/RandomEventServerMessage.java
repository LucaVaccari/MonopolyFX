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
     * @param randomEventText The random event text body
     * @param randomEventType The random event type
     */
    public RandomEventServerMessage(String randomEventText, String randomEventType)
    {
        this.randomEventText = randomEventText;
        this.randomEventType = randomEventType;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
