package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Request to sell a given contract and give the owner half of the price of sale (send only)
 */
public class SellContractClientMessage implements Message
{
    /**
     * The game code
     */
    private final int gameCode;

    /**
     * The contract to sell
     */
    private final Contract contract;

    /**
     * Constructor for SellContractClientMessage
     *
     * @param gameCode The game code
     * @param contract The contract to sell
     */
    public SellContractClientMessage(int gameCode, Contract contract)
    {
        this.gameCode = gameCode;
        this.contract = contract;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
