package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.AlertUtil;
import javafx.application.Platform;

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
		Platform.runLater(() -> showSuggestionOfBuy("Proprietà", contract.getName() + " e' in vendita!",
				"Volete comprare la proprieta' o metterla all'asta?", contract));

	}

	public static void showSuggestionOfBuy(String title, String headerText, String contentText, Contract contract)
	{
		AlertUtil.showSuggestionOfBuy(title, headerText, contentText, contract);
	}
}
