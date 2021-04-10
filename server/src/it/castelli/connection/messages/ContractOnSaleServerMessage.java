package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Message to communicate to the client that the contract is on sale (send only)
 */
public class ContractOnSaleServerMessage implements Message
{
    /**
     * The contract on sale
     */
    private final Contract contract;

    /**
     * Constructor for ContractOnSaleServerMessage
     *
     * @param contract The contract on sale
     */
    public ContractOnSaleServerMessage(Contract contract)
    {
        this.contract = contract;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //do nothing
    }
}
