package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

/**
 * Request to buy the contract on sale or to start an auction for that contract depending on a boolean value (send only)
 */
public class BuyContractClientMessage implements Message
{
    /**
     * The contract that the player can buy
     */
    private final Contract contract;

    /**
     * The player
     */
    private final Player player;

    /**
     * Does the player buy the contract?
     */
    private final boolean buy;

    /**
     * The game code
     */
    private final int gameCode;

    /**
     * Constructor for BuyContractClientMessage
     *
     * @param contract The contract that the player can buy
     * @param player The player
     * @param buy Does the player buy the contract?
     * @param gameCode The game code
     */
    public BuyContractClientMessage(Contract contract, Player player, boolean buy, int gameCode)
    {
        this.contract = contract;
        this.player = player;
        this.buy = buy;
        this.gameCode = gameCode;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        // do nothing
    }
}
