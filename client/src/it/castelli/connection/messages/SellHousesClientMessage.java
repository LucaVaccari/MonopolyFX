package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;


/**
 * Message for sell houses (send only)
 */
public class SellHousesClientMessage implements Message
{
    /**
     * The game code
     */
    private int gameCode;

    /**
     * Contract for which player want to sell houses
     */
    private Contract contract;

    /**
     * Number of houses that player wants to sell
     */
    private int numberOfHousesToSell;

    /**
     * Constructor for SellHousesClientMessage
     *
     * @param gameCode The game code
     * @param contract Contract for which player want to sell houses
     * @param numberOfHousesToSell Number of houses that player wants to sell
     */
    public SellHousesClientMessage(int gameCode, Contract contract, int numberOfHousesToSell)
    {
        this.gameCode = gameCode;
        this.contract = contract;
        this.numberOfHousesToSell = numberOfHousesToSell;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //do nothing
    }
}
