package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Request to mortgage a given contract (send only)
 */
public class MortgageContractClientMessage implements Message
{
    /**
     * The game code
     */
    private final int gameCode;

    /**
     * The contract to mortgage
     */
    private final Contract contract;

    /**
     * Constructor for MortgageContractClientMessage
     *
     * @param gameCode The game code
     * @param contract The contract to mortgage
     */
    public MortgageContractClientMessage(int gameCode, Contract contract)
    {
        this.gameCode = gameCode;
        this.contract = contract;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //do nothing
    }
}
