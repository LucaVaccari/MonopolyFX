package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.AlertUtil;
import javafx.application.Platform;

/**
 * Message from the server that communicates the last encountered event (receive only)
 */
public class EventClientMessage implements Message
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
	 * Constructor for EventClientMessage (do not use)
	 *
	 * @param randomEventType The event type
	 * @param randomEventText The event text body
	 */
	public EventClientMessage(String randomEventType, String randomEventText)
	{
		this.randomEventType = randomEventType;
		this.randomEventText = randomEventText;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Player activePlayer = Game.getGameManager().getCurrentRound().getCurrentActivePlayer();
		Platform.runLater(() -> AlertUtil.showInformationAlert(randomEventType, activePlayer.getName(), randomEventText));
	}
}
