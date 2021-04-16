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
 * Message received from the server that closes the window of the successfully closed exchange (receive only)
 */
public class ExchangeSuccessfulClientMessage implements Message
{
	/**
	 * The exchange that closed
	 */
	private final Exchange exchange;

	/**
	 * Constructor for ExchangeSuccessfulClientMessage (do not use)
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
		if (exchange.getPlayer1().betterEquals(Game.getPlayer()) || exchange.getPlayer2().betterEquals(Game.getPlayer()))
		{
			Game.getGameManager().removeExchange(exchange);
			Platform.runLater(() -> {
				SceneManager.getInstance().getStageByType(SceneType.EXCHANGE).close();
				AlertUtil.showInformationAlert("Successo", "Scambio eseguito",
				                               "Lo scambio e' stato effettuato con successo!");
			});
		}
	}
}
