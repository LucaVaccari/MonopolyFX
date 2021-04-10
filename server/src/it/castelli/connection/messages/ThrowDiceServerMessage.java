package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.Round;
import it.castelli.gameLogic.dice.DiceResult;
import it.castelli.serialization.Serializer;

/**
 * Message from the client that throws the dice (receive only)
 */
public class ThrowDiceServerMessage implements Message
{
	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for ThrowDiceServerMessage (do not use)
	 *
	 * @param gameCode The game code
	 */
	public ThrowDiceServerMessage(int gameCode)
	{
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		System.out.println("Throwing dice");
		Round currentRound =
				ConnectionManager.getInstance().getGames().get(gameCode).getGameManager().getCurrentRound();
		currentRound.throwDice();
		DiceResult diceResult = currentRound.getDiceResult();

		ConnectionManager.getInstance().getGames().get(gameCode).sendAll(ServerMessages.UPDATE_DICE_RESULT_MESSAGE_NAME,
		                                                                 Serializer.toJson(new UpdateDiceResultServerMessage(
				                                                                 diceResult)));


	}
}
