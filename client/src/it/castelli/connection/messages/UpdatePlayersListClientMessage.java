package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.controllers.BoardController;
import it.castelli.gui.controllers.LobbyController;
import javafx.application.Platform;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Message received from the server that updates the player list on the client
 */
public class UpdatePlayersListClientMessage implements Message
{
	/**
	 * The updated player list
	 */
	private final CopyOnWriteArrayList<Player> players;

	/**
	 * Constructor for UpdatePlayersListClientMessage (do not use)
	 *
	 * @param players The updated player list
	 */
	public UpdatePlayersListClientMessage(CopyOnWriteArrayList<Player> players)
	{
		this.players = players;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		System.out.println("Updating player list which contains " + players.size() + " players");
		Game.getGameManager().clearPlayers();
		for (Player element : players)
		{
			Game.getGameManager().addPlayer(element);
			if (element.betterEquals(Game.getPlayer()))
				Game.setPlayer(element);

			System.out.println("Il player " + element.getName() + " possiede " + element.getContracts().size() + " proprietà");
			for (Contract contract : element.getContracts())
			{
				System.out.println(element.getName() + " possiede " + contract.getName());
			}
		}

		Platform.runLater(() -> LobbyController.getInstance().updatePlayerListView());
		Platform.runLater(() -> BoardController.getInstance().update());
	}
}
