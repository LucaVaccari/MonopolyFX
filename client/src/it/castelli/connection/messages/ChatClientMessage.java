package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;
import it.castelli.gui.controllers.BoardController;
import it.castelli.gui.controllers.LobbyController;
import javafx.application.Platform;

public class ChatClientMessage implements Message
{
	private final int gameCode;
	private final String message;

	public ChatClientMessage(int gameCode, Player senderPlayer, String messageBody)
	{
		this.gameCode = gameCode;
		message = senderPlayer.getName() + ": " + messageBody;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{
		Platform.runLater(() -> LobbyController.getInstance().getChat().addMessage(message));
		Platform.runLater(() -> BoardController.getInstance().getChat().addMessage(message));
		//TODO: print the content of the message
	}
}
