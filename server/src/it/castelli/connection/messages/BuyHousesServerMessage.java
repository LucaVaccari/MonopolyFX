package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;

/**
 * Message for buy houses (receive only)
 */
public class BuyHousesServerMessage implements Message
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
     * Constructor for BuyHousesServerMessage (do not use)
     *
     * @param gameCode The game code
     * @param contract Contract for which player want to buy houses
     * @param numberOfHousesToBuy Number of houses that player wants to buy
     */
    public BuyHousesServerMessage(int gameCode, Contract contract, int numberOfHousesToBuy)
    {
        this.gameCode = gameCode;
        this.contract = contract;
        this.numberOfHousesToBuy = numberOfHousesToBuy;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
        GameManager gameManager = gameConnectionManager.getGameManager();

        Contract sameContract = gameManager.getSameContract(contract);
        Player owner = sameContract.getOwner().toPlayer();

        if(sameContract instanceof PropertyContract)
        {
            ((PropertyContract) sameContract).addHouses(numberOfHousesToBuy);
            owner.removeMoney(numberOfHousesToBuy * ((PropertyContract) sameContract).getHouseCost());
        }
        else
            connection.send(ServerMessages.ERROR_MESSAGE_NAME, "Non potete comprare case di questa proprietà, poiché è una stazione oppure una società");

        gameConnectionManager.updatePlayers();
    }
}
