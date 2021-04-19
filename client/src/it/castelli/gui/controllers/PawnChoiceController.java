package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ChoosePawnClientMessage;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.gameLogic.Pawn;
import it.castelli.gameLogic.Player;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class PawnChoiceController
{

	@FXML
	private ImageView boatPawnImageView;
	@FXML
	private ImageView carPawnImageView;
	@FXML
	private ImageView dogPawnImageView;
	@FXML
	private ImageView shoePawnImageView;
	@FXML
	private ImageView thimblePawnImageView;
	@FXML
	private ImageView wagonPawnImageView;


	@FXML
	private void initialize()
	{
		thimblePawnImageView.setOnMouseClicked(event -> checkPawn(Pawn.THIMBLE));
		wagonPawnImageView.setOnMouseClicked(event -> checkPawn(Pawn.WAGON));
		shoePawnImageView.setOnMouseClicked(event -> checkPawn(Pawn.SHOE));
		dogPawnImageView.setOnMouseClicked(event -> checkPawn(Pawn.DOG));
		carPawnImageView.setOnMouseClicked(event -> checkPawn(Pawn.CAR));
		boatPawnImageView.setOnMouseClicked(event -> checkPawn(Pawn.BOAT));


	}

	private void checkPawn(Pawn pawn)
	{
		boolean pawnAlreadyUsed = false;
		for (Player player : Game.getGameManager().getPlayers())
		{
			if (player.getPawn() == pawn)
			{
				pawnAlreadyUsed = true;
				break;
			}
		}
		if (!pawnAlreadyUsed)
		{
			Game.getPlayer().setPawn(pawn);
			ClientMain.getConnection().send(ClientMessages.CHOOSE_PAWN_MESSAGE_NAME,
					Serializer.toJson(new ChoosePawnClientMessage(pawn, Game.getGameCode())));
			Platform.runLater(() -> {
				SceneManager.getInstance().showScene(SceneType.LOBBY);
			});
		}
		else
		{
			AlertUtil.showInformationAlert("Errore", "pedina gia' stata scelta",
					"la pedina che hai selezionato non e' disponibile,seleziona un'altra " +
							"pedina");
		}
	}
}
