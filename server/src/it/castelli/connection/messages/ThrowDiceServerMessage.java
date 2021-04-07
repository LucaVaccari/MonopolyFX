package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.Round;
import it.castelli.gameLogic.dice.DiceResult;
import it.castelli.serialization.Serializer;

public class ThrowDiceServerMessage implements Message
{
    private final int gameCode;

    public ThrowDiceServerMessage(int gameCode)
    {
        this.gameCode = gameCode;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        Round currentRound = ConnectionManager.getInstance().getGames().get(gameCode).getGameManager().getCurrentRound();
        currentRound.throwDice();
        DiceResult diceResult = currentRound.getDiceResult();
        connection.send(ServerMessages.DICE_RESULT_MESSAGE_NAME, Serializer.toJson(new DiceResultServerMessage(diceResult)));
    }
}
