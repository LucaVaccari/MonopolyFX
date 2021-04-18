package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.serialization.Serializer;

/**
 * Message that sells a given contract and gives the owner half of the price of sale (receive only)
 */
public class SellContractServerMessage implements Message
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
     * Constructor for SellContractServerMessage (do not use)
     *
     * @param gameCode The game code
     * @param contract The contract to sell
     */
    public SellContractServerMessage(int gameCode, Contract contract)
    {
        this.gameCode = gameCode;
        this.contract = contract;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
        GameManager gameManager = gameConnectionManager.getGameManager();

        Contract contractToSell = gameManager.getSameContract(contract);
        Player owner = gameManager.getSamePlayer(contractToSell.getOwner().toPlayer());

        boolean isSellable = true;

        if (contractToSell instanceof PropertyContract)
            if (((PropertyContract) contractToSell).getNumberOfHouses() != 0)
                isSellable = false;

        if (isSellable)
        {
            contractToSell.setOwner(null);
            owner.getContracts().remove(contractToSell);
            owner.addMoney(contractToSell.getValue() / 2);
        }
        else
        {
            connection.send(ServerMessages.GENERIC_MESSAGE_NAME, Serializer.toJson(new GenericServerMessage("Operazione negata", "Non potete vendere questa proprieta' perchè vi sono ancora delle case o un albergo! Per vendere la proprieta' vendere prima le case o l'albergo.")));
        }

        gameConnectionManager.updatePlayers();
    }
}
