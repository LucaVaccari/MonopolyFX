package it.castelli.connection.messages;

import it.castelli.Game;
import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.application.Platform;

/**
 * Message that informs about a kicked player (receive only)
 */
public class PlayerKickedClientMessage implements Message
{
	/**
	 * The player who got kicked
	 */
	private final Player player;

	/**
	 * Constructor for PlayerKickedClientMessage (do not use)
	 *
	 * @param player The player who got kicked
	 */
	public PlayerKickedClientMessage(Player player)
	{
		this.player = player;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		if (Game.getPlayer().betterEquals(this.player))
		{
			connection.send(ClientMessages.LEAVE_GAME_MESSAGE_NAME,
					Serializer.toJson(new LeaveGameClientMessage(Game.getGameCode())));
			Platform.runLater(() -> {
				SceneManager.getInstance().showScene(SceneType.MAIN_MENU);
				AlertUtil.showInformationAlert("Espulso", "Siete stato espulso dalla partita",
						"Tutti gli altri componenti hanno votato per cacciarVi");
			});
		}
		else
		{
			Platform.runLater(() -> AlertUtil.showInformationAlert("Giocatore espulso", "Avete votato per " +
							"l'espulsione",
					"Il giocatore " + this.player.getName() +
							" e' stato espulso"));
		}
	}
}
