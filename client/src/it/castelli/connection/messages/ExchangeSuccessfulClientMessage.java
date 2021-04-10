package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.gui.AlertUtil;

/**
 * Message received from the server that closes the window of the successfully closed exchange (receive only)
 */
public class ExchangeSuccessfulClientMessage implements Message
{
	/**
	 * The exchange that closed
	 */
	private final Exchange exchange;

	/**
	 * Constructor for ExchangeSuccessfulClientMessage
	 *
	 * @param exchange The exchange that closed
	 */
	public ExchangeSuccessfulClientMessage(Exchange exchange)
	{
		this.exchange = exchange;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		if (exchange.getPlayer1().equals(Game.getPlayer()) || exchange.getPlayer2().equals(Game.getPlayer()))
		{
			AlertUtil.showInformationAlert("Successo", "Scambio eseguito",
					"Lo scambio è stato effettuato con successo!");
			// TODO: close the exchange window
		}
	}
}
