package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.dice.DiceResult;
import it.castelli.gui.controllers.BoardController;
import javafx.scene.image.Image;

public class DiceResultClientMessage implements Message
{
	private final DiceResult diceResult;

	public DiceResultClientMessage(DiceResult diceResult)
	{
		this.diceResult = diceResult;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Game.setLastDiceResult(diceResult);

		Image firstDieImage = new Image(
				String.valueOf(getClass().getResource("/images/face_" + diceResult.getFirstResult() + ".png")));
		BoardController.getInstance().getDie1Image().setImage(firstDieImage);
		Image secondDieImage = new Image(
				String.valueOf(getClass().getResource("/images/face_" + diceResult.getSecondResult() + ".png")));
		BoardController.getInstance().getDie2Image().setImage(secondDieImage);

//		Game.useDiceResult();
	}
}
