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
 * Message that mortgages a given contract
 */
public class MortgageContractServerMessage implements Message
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
     * Constructor for MortgageContractServerMessage (do not use)
     *
     * @param gameCode The game code
     * @param contract The contract to mortgage
     */
    public MortgageContractServerMessage(int gameCode, Contract contract)
    {
        this.gameCode = gameCode;
        this.contract = contract;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
        GameManager gameManager = gameConnectionManager.getGameManager();

        Contract contractToMortgage = gameManager.getSameContract(contract);
        Player mortgagingPlayer = gameManager.getSamePlayer(contractToMortgage.getOwner().toPlayer());

        boolean isMortgageable = true;

        if (contractToMortgage instanceof PropertyContract)
            if (((PropertyContract) contractToMortgage).getNumberOfHouses() != 0 )
                isMortgageable = false;
        if (contractToMortgage.isMortgaged())
            isMortgageable = false;

        if (isMortgageable)
        {
            contractToMortgage.setMortgaged(true);
            mortgagingPlayer.addMoney(contractToMortgage.getMortgageValue());
        }
        else
        {
            connection.send(ServerMessages.ERROR_MESSAGE_NAME, Serializer.toJson(new ErrorServerMessage("Non potete ipotecare questa proprietà, assicuratevi che non vi siano case o alberghi o che la proprietà non sia già ipotecata!")));
        }

        gameConnectionManager.updatePlayers();
    }
}
