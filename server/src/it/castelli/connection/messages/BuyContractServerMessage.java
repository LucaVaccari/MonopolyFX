package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.serialization.Serializer;

/**
 * Message from the clients that buys the contract on sale or starts an auction for that contract depending on a boolean value (receive only)
 */
public class BuyContractServerMessage implements Message
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
     * Constructor for BuyContractServerMessage (do not use)
     *
     * @param contract The contract that the player can buy
     * @param player The player
     * @param buy Does the player buy the contract?
     * @param gameCode The game code
     */
    public BuyContractServerMessage(Contract contract, Player player, boolean buy, int gameCode)
    {
        this.contract = contract;
        this.player = player;
        this.buy = buy;
        this.gameCode = gameCode;
    }

    @Override
    public void onReceive(Connection connection, Player player)
    {
        GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
        GameManager gameManager = gameConnectionManager.getGameManager();
        Player buyingPlayer = gameManager.getSamePlayer(this.player);
        Contract contractToBuy = gameManager.getSameContract(contract);

        if (buy)
        {
            buyingPlayer.addContract(contractToBuy);
            buyingPlayer.removeMoney(contractToBuy.getValue());
            gameConnectionManager.updatePlayers();
        }
        else
        {
            gameConnectionManager.startAuction(contractToBuy, 9);
            gameConnectionManager.sendAll(ServerMessages.NEW_AUCTION_MESSAGE_NAME, Serializer.toJson(new NewAuctionServerMessage(contractToBuy, null, 9)));
        }
    }
}
