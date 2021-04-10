package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.gui.AlertUtil;

/**
 * Request to refuse the exchange offer (send only)
 */
public class RefuseExchangeClientMessage implements Message
{
	/**
	 * The exchange to refuse
	 */
	private final Exchange exchange;

	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for RefuseExchangeClientMessage
	 *
	 * @param exchange The exchange to refuse
	 * @param gameCode The game code
	 */
	public RefuseExchangeClientMessage(Exchange exchange, int gameCode)
	{
		this.exchange = exchange;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		if (exchange.getPlayer1().equals(Game.getPlayer()) || exchange.getPlayer2().equals(Game.getPlayer()))
		{
			Game.getGameManager().removeExchange(exchange);
			AlertUtil.showInformationAlert("Fallimento", "Scambio rifiutato",
					"Lo scambio è stato rifiutato.");
			// TODO: close the exchange window
		}
	}
}
