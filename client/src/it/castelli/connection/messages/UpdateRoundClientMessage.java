package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.Round;
import it.castelli.gui.controllers.BoardController;
import javafx.application.Platform;

/**
 * Message received from server that updates the current round (receive only)
 */
public class UpdateRoundClientMessage implements Message
{
	/**
	 * The new round
	 */
	private final Round round;

	/**
	 * Constructor for UpdateRoundClientMessage (do not use)
	 *
	 * @param round The new round
	 */
	public UpdateRoundClientMessage(Round round)
	{
		this.round = round;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.getGameManager().setCurrentRound(round);

		System.out.println("Il giocatore (dal round) " + round.getCurrentActivePlayer() + " è in prigione? " + round.getCurrentActivePlayer().isInPrison());
		System.out.println("Il giocatore (dal Game) " + Game.getPlayer() + " è in prigione? " + Game.getPlayer().isInPrison());
		Platform.runLater(() -> BoardController.getInstance().update());
	}
}
