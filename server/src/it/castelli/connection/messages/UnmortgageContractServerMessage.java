package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.serialization.Serializer;

/**
 * Message that removes the mortgage from a given mortgage contract and removes a money amount equal to the cost of this operation from the owner (receive only)
 */
public class UnmortgageContractServerMessage implements Message
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
     * Constructor for UnmortgageContractServerMessage (do not use)
     *
     * @param gameCode The game code
     * @param contract The contract to mortgage
     */
    public UnmortgageContractServerMessage(int gameCode, Contract contract)
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
        Player unmortgagingPlayer = gameManager.getSamePlayer(contractToMortgage.getOwner().toPlayer());

        if (contractToMortgage.isMortgaged())
        {
            contractToMortgage.setMortgaged(false);
            unmortgagingPlayer.removeMoney(contractToMortgage.getMortgageValue() + (int)(contractToMortgage.getMortgageValue() * 10));
        }
        else
        {
            connection.send(ServerMessages.ERROR_MESSAGE_NAME, Serializer.toJson(new ErrorServerMessage("Non potete sciogliere l'ipoteca da questa proprietà, poichè non è ipotecata!")));
        }

        gameConnectionManager.updatePlayers();
    }
}
