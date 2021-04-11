package it.castelli.connection.messages;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.AlertUtil;
import it.castelli.serialization.Serializer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

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
        System.out.println(contract.getName() + " è in vendita!");
        //TODO: show the property and the "want to buy it" choose window
        Platform.runLater(()->showSuggestionOfBuy("Proprietà","Possibilita' di comprare una proprieta'","Potete scegliere se comprare o mettere all'asta la proprieta'",contract));

    }
    public static void showSuggestionOfBuy(String title, String headerText, String contentText, Contract contract)
    {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        ButtonType buyButtonType = new ButtonType("Compra");
        ButtonType auctionButtonType = new ButtonType("Asta");

        alert.getButtonTypes().setAll(buyButtonType, auctionButtonType);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent()){
            if (result.get() == buyButtonType){
                System.out.println("stai comprando");
                ClientMain.getConnection().send(ClientMessages.BUY_CONTRACT_MESSAGE_NAME, Serializer.toJson(new BuyContractClientMessage(contract, Game.getPlayer(), true, Game.getGameCode())));
            }
            else if (result.get() == auctionButtonType)
                ClientMain.getConnection().send(ClientMessages.BUY_CONTRACT_MESSAGE_NAME, Serializer.toJson(new BuyContractClientMessage(contract, Game.getPlayer(), false, Game.getGameCode())));
            else showSuggestionOfBuy(title, headerText, contentText, contract);
        }

    }
}
