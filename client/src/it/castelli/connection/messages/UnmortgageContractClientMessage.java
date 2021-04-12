package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Request to remove the mortgage from a given mortgage contract and remove a money amount equal to the cost of this operation from the owner (send only)
 */
public class UnmortgageContractClientMessage implements Message
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
     * Constructor for UnmortgageContractClientMessage
     *
     * @param gameCode The game code
     * @param contract The contract to mortgage
     */
    public UnmortgageContractClientMessage(int gameCode, Contract contract)
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
