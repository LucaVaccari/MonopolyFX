package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message to update the timer shown in the client auction window (send only)
 */
public class UpdateAuctionTimerServerMessage implements Message
{
    /**
     * The number of seconds left to offer in the auction
     */
    private final int timerNumber;

    /**
     * Constructor for UpdateAuctionTimerServerMessage
     *
     * @param timerNumber The number of seconds left to offer in the auction
     */
    public UpdateAuctionTimerServerMessage(int timerNumber)
    {
        this.timerNumber = timerNumber;
    }


    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
