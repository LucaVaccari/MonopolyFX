package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;

/**
 * Message for sell houses (receive only)
 */
public class SellHousesServerMessage implements Message
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
     * Constructor for SellHousesServerMessage (do not use)
     *
     * @param gameCode The game code
     * @param contract Contract for which player want to sell houses
     * @param numberOfHousesToSell Number of houses that player wants to sell
     */
    public SellHousesServerMessage(int gameCode, Contract contract, int numberOfHousesToSell)
    {
        this.gameCode = gameCode;
        this.contract = contract;
        this.numberOfHousesToSell = numberOfHousesToSell;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
        GameManager gameManager = gameConnectionManager.getGameManager();

        Contract sameContract = gameManager.getSameContract(contract);
        Player owner = gameManager.getSamePlayer(sameContract.getOwner().toPlayer());

        if(sameContract instanceof PropertyContract)
        {
            ((PropertyContract) sameContract).removeHouses(numberOfHousesToSell);
            owner.addMoney(((PropertyContract) sameContract).getHouseCost() / 2 * numberOfHousesToSell);
        }
        else
            connection.send(ServerMessages.ERROR_MESSAGE_NAME, "Non potete vendere case di questa proprietà, poiché è una stazione oppure una società");

        gameConnectionManager.updatePlayers();
    }
}
