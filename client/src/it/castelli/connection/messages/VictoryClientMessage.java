package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.AlertUtil;
import it.castelli.serialization.Serializer;
import javafx.application.Platform;

import java.util.Random;

/**
 * Message which communicates the player won (receive only)
 */
public class VictoryClientMessage implements Message
{
	@Override
	public void onReceive(Connection connection, Player player)
	{
		Platform.runLater(() -> AlertUtil
				.showInformationAlert("Vittoria", getRandomWinSentence(), getRandomWinSentence()));

		connection.send(ClientMessages.LEAVE_GAME_MESSAGE_NAME, Serializer
				.toJson(new LeaveGameClientMessage(Game.getGameCode())));
	}

	private String getRandomWinSentence()
	{
		String playerName = Game.getPlayer().getName();
		final String[] sentences = new String[]{
				playerName + ", congratulazioni!",
				playerName + ", avete vinto!",
				"Un bel brindisi per " + playerName + "!",
				"Bella partita, complimenti",
				"La fortuna era dalla vostra, " + playerName,
				"Nessuno vi fermerà mai!",
				"GG EZ",
				"Brao",
				"Avete giocato bene",
		};

		Random random = new Random();
		return sentences[random.nextInt(sentences.length)];
	}
}
