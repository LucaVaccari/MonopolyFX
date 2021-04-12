package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.AlertUtil;

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
	 * @param randomEventType The random event type
	 * @param randomEventText The random event text body
	 */
	public RandomEventClientMessage(String randomEventType, String randomEventText)
	{
		this.randomEventType = randomEventType;
		this.randomEventText = randomEventText;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Player activePlayer = Game.getGameManager().getCurrentRound().getCurrentActivePlayer();
		System.out.println(randomEventType + "! " + randomEventText);
		AlertUtil.showInformationAlert(activePlayer.getName(), randomEventType, randomEventText);
	}
}
