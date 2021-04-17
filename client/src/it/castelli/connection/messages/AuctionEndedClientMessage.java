package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.transactions.Auction;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import javafx.application.Platform;

/**
 * Message that closes the auction window (receive only)
 */
public class AuctionEndedClientMessage implements Message
{
	@Override
	public void onReceive(Connection connection, Player player)
	{
		Platform.runLater(() -> {
			SceneManager.getInstance().getStageByType(SceneType.AUCTION).close();

			Auction auction = Game.getGameManager().getAuction();
			Player winningPlayer = auction.getPlayer();
			String content = winningPlayer == null ?
			                 "Nessuno ha vinto l'asta. Il terreno resta alla banca" : 
			                 winningPlayer + " ha ottenuto " + auction.getContract() + "!";
			AlertUtil.showInformationAlert("Fine asta", "L'asta si è conclusa", content);

			Game.getGameManager().setAuction(null);
		});
	}
}
