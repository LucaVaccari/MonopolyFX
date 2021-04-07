package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.dice.DiceResult;

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
        //TODO: update dice images
        // move player?
    }
}
