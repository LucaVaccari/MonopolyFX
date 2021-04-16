package it.castelli.connection;

import it.castelli.connection.messages.AuctionEndedServerMessage;
import it.castelli.connection.messages.ServerMessages;
import it.castelli.connection.messages.UpdateAuctionTimerServerMessage;
import it.castelli.serialization.Serializer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class AuctionTimerTask extends TimerTask
{
    private final int gameCode;
    private int repeats;
    Timer timer;

    public AuctionTimerTask(int repeats, int gameCode)
    {
        this.repeats = repeats;
        this.gameCode = gameCode;
    }

    public void init()
    {
        timer = new Timer();
        timer.schedule(this, 0, TimeUnit.SECONDS.toMillis(1));
    }

    @Override
    public void run()
    {
        if (repeats == 0)
            stop();
        //ConnectionManager.getInstance().getGames().get(gameCode).sendAll(ServerMessages.UPDATE_AUCTION_TIMER_MESSAGE_NAME, Serializer.toJson(new UpdateAuctionTimerServerMessage(repeats)));
        System.out.println(repeats);
        repeats--;
    }

    public void stop()
    {
        timer.cancel();
        System.out.println("Auction timer finished");
        //ConnectionManager.getInstance().getGames().get(gameCode).sendAll(ServerMessages.AUCTION_ENDED_MESSAGE_NAME, Serializer.toJson(new AuctionEndedServerMessage()));
        //ConnectionManager.getInstance().getGames().get(gameCode).getGameManager().getAuction().endAuction();
    }
}
