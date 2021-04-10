package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Message from server that is received when the contract is on sale (receive only)
 */
public class ContractOnSaleClientMessage implements Message
{
    /**
     * The contract on sale
     */
    private final Contract contract;

    /**
     * Constructor for ContractOnSaleClientMessage (do not use)
     *
     * @param contract The contract on sale
     */
    public ContractOnSaleClientMessage(Contract contract)
    {
        this.contract = contract;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        //TODO: show the property and the "want to buy it" choose window
    }
}
