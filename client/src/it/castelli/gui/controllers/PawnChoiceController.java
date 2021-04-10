package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.gameLogic.Pawn;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public class PawnChoiceController
{
	private static HashMap<String, Pawn> PawnPaths = new HashMap<>();
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

	public static HashMap<String, Pawn> getPawnPaths()
	{
		return PawnPaths;
	}

	@FXML
	private void initialize()
	{
		PawnPaths.put("images/pawns/boat.png", Pawn.BOAT);
		PawnPaths.put("images/pawns/dog.png", Pawn.DOG);
		PawnPaths.put("images/pawns/shoe.png", Pawn.SHOE);
		PawnPaths.put("images/pawns/car.png", Pawn.CAR);
		PawnPaths.put("images/pawns/wagon.png", Pawn.WAGON);
		PawnPaths.put("images/pawns/thimble.png", Pawn.THIMBLE);

		thimblePawnImageView.setOnMouseClicked(event -> {
					ClientMain.getConnection().send(ClientMessages.CHOOSE_PAWN_MESSAGE_NAME, Serializer.toJson(Pawn.THIMBLE));
					Platform.runLater(() -> SceneManager.getInstance().showScene(SceneType.LOBBY));
				}
		);
		wagonPawnImageView.setOnMouseClicked(event ->
				ClientMain.getConnection().send(ClientMessages.CHOOSE_PAWN_MESSAGE_NAME, Serializer.toJson(Pawn.WAGON))
		);
		shoePawnImageView.setOnMouseClicked(event ->
				ClientMain.getConnection().send(ClientMessages.CHOOSE_PAWN_MESSAGE_NAME, Serializer.toJson(Pawn.SHOE))
		);
		dogPawnImageView.setOnMouseClicked(event ->
				ClientMain.getConnection().send(ClientMessages.CHOOSE_PAWN_MESSAGE_NAME, Serializer.toJson(Pawn.DOG))
		);
		carPawnImageView.setOnMouseClicked(event ->
				ClientMain.getConnection().send(ClientMessages.CHOOSE_PAWN_MESSAGE_NAME, Serializer.toJson(Pawn.CAR))
		);
		boatPawnImageView.setOnMouseClicked(event ->
				ClientMain.getConnection().send(ClientMessages.CHOOSE_PAWN_MESSAGE_NAME, Serializer.toJson(Pawn.BOAT))
		);
	}
}
