package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Exchange;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Platform;

/**
 * Message received from the server that closes the window of the canceled exchange (receive only)
 */
public class ExchangeCanceledClientMessage implements Message
{
	/**
	 * The exchange that got canceled
	 */
	private final Exchange exchange;

	/**
	 * Constructor for ExchangeCanceledClientMessage (do not use)
	 *
	 * @param exchange The exchange that got canceled
	 */
	public ExchangeCanceledClientMessage(Exchange exchange)
	{
		this.exchange = exchange;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		if (exchange.getPlayer1().equals(Game.getPlayer()) || exchange.getPlayer2().equals(Game.getPlayer()))
		{
			Game.getGameManager().removeExchange(exchange);
			Platform.runLater(() -> {
				SceneManager.getInstance().getStageByType(SceneType.EXCHANGE).close();
				AlertUtil.showInformationAlert("Fallimento", "Scambio rifiutato",
						"Lo scambio e' stato rifiutato.");
			});
		}
	}
}
