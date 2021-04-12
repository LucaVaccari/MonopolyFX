package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;


/**
 * Message for buy houses (send only)
 */
public class BuyHousesClientMessage implements Message
{
    /**
     * The game code
     */
    private int gameCode;

    /**
     * Contract for which player want to buy houses
     */
    private Contract contract;

    /**
     * Number of houses that player wants to buy
     */
    private int numberOfHousesToBuy;

    /**
     * Constructor for BuyHousesClientMessage
     *
     * @param gameCode The game code
     * @param contract Contract for which player want to buy houses
     * @param numberOfHousesToBuy Number of houses that player wants to buy
     */
    public BuyHousesClientMessage(int gameCode, Contract contract, int numberOfHousesToBuy)
    {
        this.gameCode = gameCode;
        this.contract = contract;
        this.numberOfHousesToBuy = numberOfHousesToBuy;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //do nothing
    }
}
