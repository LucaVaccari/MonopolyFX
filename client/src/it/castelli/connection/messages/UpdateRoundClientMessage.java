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
		System.out.println("UpdateRoundClientMessage, dice already thrown? " + round.isDiceThrown());
		//TODO: disable the throwDiceButton according to round.isDiceThrown()
		System.out.println(round.getCurrentActivePlayer().getName());
		System.out.println(Game.getPlayer().getName());
		System.out.println(round.getCurrentActivePlayer().getMoney());
		System.out.println(Game.getPlayer().getMoney());
		System.out.println(round.getCurrentActivePlayer().getPosition());
		System.out.println(Game.getPlayer().getPosition());
		System.out.println(round.getCurrentActivePlayer().isInPrison());
		System.out.println(Game.getPlayer().isInPrison());
		System.out.println(round.getCurrentActivePlayer().getPawn().toString());
		System.out.println(Game.getPlayer().getPawn().toString());


		Platform.runLater(() -> {
			if (round.getCurrentActivePlayer().equals(Game.getPlayer()))
			{
				BoardController.getInstance().getThrowDiceButton().setDisable(round.isDiceThrown());
				BoardController.getInstance().getThrowDiceButton().setVisible(!round.isDiceThrown());
			}
			else
			{
				BoardController.getInstance().getThrowDiceButton().setDisable(true);
				BoardController.getInstance().getThrowDiceButton().setVisible(false);
			}
		});
	}
}
