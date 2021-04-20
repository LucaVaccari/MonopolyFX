package it.castelli.connection;

import it.castelli.connection.messages.AuctionEndedServerMessage;
import it.castelli.connection.messages.ServerMessages;
import it.castelli.connection.messages.UpdateAuctionTimerServerMessage;
import it.castelli.serialization.Serializer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * The class handling the timer of the auction
 */
public class AuctionTimerTask extends TimerTask
{
	/**
	 * The game code this auction is in
	 */
	private final int gameCode;

	/**
	 * The number of seconds after which the auction ends without any further offer
	 */
	private int repeats;

	/**
	 * The timer handling the auction
	 */
	Timer timer;

	/**
	 * Constructor for AuctionTimerTask
	 *
	 * @param repeats The number of seconds after which the auction ends without any further offer
	 * @param gameCode The game code this auction is in
	 */
	public AuctionTimerTask(int repeats, int gameCode)
	{
		this.repeats = repeats;
		this.gameCode = gameCode;
	}

	/**
	 * Starts a new countdown for the auction
	 */
	public void startAuction()
	{
		timer = new Timer();
		timer.schedule(this, 0, TimeUnit.SECONDS.toMillis(1));
	}

	/**
	 * At each step this method updates the players about the number of seconds left. If it becomes 0, the auction ends
	 */
	@Override
	public void run()
	{
		if (repeats == 0)
			endAuction();
		else
		{
			ConnectionManager.getInstance().getGames().get(gameCode)
					.sendAll(ServerMessages.UPDATE_AUCTION_TIMER_MESSAGE_NAME,
					         Serializer.toJson(new UpdateAuctionTimerServerMessage(repeats)));
		}
		repeats--;
	}

	/**
	 * Ends the auction calling the GameManager method to assign the contract to the player
	 */
	public void endAuction()
	{
		timer.cancel();
		System.out.println("Auction timer finished");
		ConnectionManager.getInstance().getGames().get(gameCode)
				.sendAll(ServerMessages.AUCTION_ENDED_MESSAGE_NAME,
                         Serializer.toJson(new AuctionEndedServerMessage()));
		ConnectionManager.getInstance().getGames().get(gameCode).getGameManager().endAuction();
		ConnectionManager.getInstance().getGames().get(gameCode).updatePlayers();

	}

	/**
	 * Ends the auction timer
	 */
	public void interruptAuctionTimer()
	{
		timer.cancel();
	}
}
