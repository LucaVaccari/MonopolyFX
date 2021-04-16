package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

/**
 * Message that updates the timer shown in the auction window (receive only)
 */
public class UpdateAuctionTimerClientMessage implements Message
{
    /**
     * The number of seconds left to offer in the auction
     */
    private final int timerNumber;

    /**
     * Constructor for UpdateAuctionTimerClientMessage (do not use)
     *
     * @param timerNumber The number of seconds left to offer in the auction
     */
    public UpdateAuctionTimerClientMessage(int timerNumber)
    {
        this.timerNumber = timerNumber;
    }


    @Override
    public void onReceive(Connection connection, Player player)
    {
        //TODO: update time shown in the auction window
        System.out.println("Still " + timerNumber + " seconds left to offer");
    }
}
