package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.controllers.AuctionController;
import javafx.application.Platform;

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
		Platform.runLater(() -> AuctionController.getInstance().setTimer(timerNumber));
	}
}
