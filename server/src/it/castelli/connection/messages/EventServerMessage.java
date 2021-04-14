package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message to communicate te client the random event (send only)
 */
public class EventServerMessage implements Message
{
    /**
     * The event text body
     */
    private final String randomEventText;

    /**
     * The event type
     */
    private final String randomEventType;

    /**
     * Constructor for EventServerMessage (do not use)
     *
     * @param randomEventType The event type
     * @param randomEventText The event text body
     */
    public EventServerMessage(String randomEventType, String randomEventText)
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
