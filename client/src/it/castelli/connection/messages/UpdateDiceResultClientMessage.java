package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.dice.DiceResult;
import it.castelli.gui.controllers.BoardController;
import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * Message received from the server that updates the dice result with the last dice throw result (receive only)
 */
public class UpdateDiceResultClientMessage implements Message
{
	/**
	 * The last dice result
	 */
	private final DiceResult diceResult;

	/**
	 * Constructor for UpdateDiceResultClientMessage (do not use)
	 *
	 * @param diceResult The last dice result
	 */
	public UpdateDiceResultClientMessage(DiceResult diceResult)
	{
		this.diceResult = diceResult;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.setLastDiceResult(diceResult);

		Platform.runLater(() -> BoardController.getInstance().updateDice(diceResult));

		if (Game.getGameManager().getCurrentRound().getCurrentActivePlayer().betterEquals(Game.getPlayer()))
			Game.useDiceResult();
	}
}
