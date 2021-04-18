package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.AlertUtil;
import javafx.application.Platform;

/**
 * Message from the server that communicates when a player pays to another one to the client (receive only)
 */
public class PlayerPaidClientMessage implements Message
{
	/**
	 * The name of the player that paid
	 */
	private final String playerName;

	/**
	 * The name of the owner of the contract
	 */
	private final String ownerName;

	/**
	 * The contract name
	 */
	private final String contractName;

	/**
	 * The amount of money that got paid
	 */
	private final int moneyPaid;

	/**
	 * Did the owner got paid? (else the bank got paid)
	 */
	private final boolean ownerGotPaid;

	/**
	 * Constructor for PlayerPaidClientMessage (do not use)
	 *
	 * @param playerName   The name of the player that paid
	 * @param ownerName    The name of the owner of the contract
	 * @param contractName The contract name
	 * @param moneyPaid    The amount of money that got paid
	 * @param ownerGotPaid Did the owner got paid? (else the bank got paid)
	 */
	public PlayerPaidClientMessage(String playerName, String ownerName, String contractName, int moneyPaid,
	                               boolean ownerGotPaid)
	{
		this.playerName = playerName;
		this.ownerName = ownerName;
		this.contractName = contractName;
		this.moneyPaid = moneyPaid;
		this.ownerGotPaid = ownerGotPaid;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Platform.runLater(() -> AlertUtil.showInformationAlert("Pedaggio pagato",
				"Il giocatore " + playerName + " e' finito su " +
						contractName + "!",
				"Ha pagato " + moneyPaid + "M a " + ownerName));
	}
}
