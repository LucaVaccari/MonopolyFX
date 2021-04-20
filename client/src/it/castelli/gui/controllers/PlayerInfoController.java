package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.CreateExchangeClientMessage;
import it.castelli.connection.messages.VoteKickClientMessage;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.customComponents.SmallTerrainViewComponent;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Controller for playerInfo FXML
 */
public class PlayerInfoController
{
	private Player player;

	@FXML
	private Label playerNameLabel;
	@FXML
	private Label playerMoneyLabel;
	@FXML
	private Button exchangeButton;
	@FXML
	private Button votekickButton;
	@FXML
	private FlowPane propertyView;

	public void setPlayer(Player player)
	{
		this.player = Game.getGameManager().getSamePlayer(player);

		playerNameLabel.setText(player.getName());
		playerMoneyLabel.setText(player.getMoney() + "M");

		for (Contract contract : player.getContracts())
			propertyView.getChildren().add(new SmallTerrainViewComponent(contract));

		exchangeButton.setVisible(!Game.getPlayer().betterEquals(player));
		exchangeButton.setDisable(Game.getPlayer().betterEquals(player));

		exchangeButton.setTooltip(new Tooltip("Effettuate uno scambio con questo giocatore"));
		exchangeButton.setOnAction(event -> {
			ClientMain.getConnection()
					.send(ClientMessages.CREATE_EXCHANGE_MESSAGE_NAME, Serializer
							.toJson(new CreateExchangeClientMessage(Game.getPlayer(), player, Game.getGameCode())));
			Stage thisStage = SceneManager.getInstance().getStageByType(SceneType.PLAYER_INFO);
			if (thisStage != null)
				thisStage.close();
		});

		votekickButton
				.setVisible(!Game.getPlayer().betterEquals(player) && Game.getGameManager().getPlayers().size() > 2);
		votekickButton
				.setDisable(Game.getPlayer().betterEquals(player) && Game.getGameManager().getPlayers().size() <= 2);
		votekickButton.setOnAction(event -> {
			if (Game.hasKickedPlayer(player))
			{
				ClientMain.getConnection().send(ClientMessages.VOTE_KICK_MESSAGE_NAME, Serializer
						.toJson(new VoteKickClientMessage(player, false, Game.getGameCode())));
				Game.getVoteKickedPlayers().remove(player);
				votekickButton.setText("Votate espulsione");
			}
			else
			{
				ClientMain.getConnection().send(ClientMessages.VOTE_KICK_MESSAGE_NAME, Serializer
						.toJson(new VoteKickClientMessage(player, true, Game.getGameCode())));
				Game.getVoteKickedPlayers().add(player);
				votekickButton.setText("Annullate espulsione");
			}
		});

		update();
	}

	public void update()
	{
		votekickButton.setText(Game.hasKickedPlayer(player) ? "Annullate espulsione" : "Votate espulsione");
	}
}
