package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.dice.DiceResult;

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
        ConnectionManager.getInstance().getGames().get(gameCode).getGameManager().getCurrentRound().throwDice();
        DiceResult diceResult = ConnectionManager.getInstance().getGames().get(gameCode).getGameManager().getCurrentRound().getDiceResult();
        //TODO: send dice result to client
    }
}
