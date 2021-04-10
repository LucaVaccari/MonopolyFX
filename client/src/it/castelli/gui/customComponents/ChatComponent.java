package it.castelli.gui.customComponents;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ChatClientMessage;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.gui.FXMLFileLoader;
import it.castelli.serialization.Serializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ChatComponent extends AnchorPane
{
	private static final String CHAT_FXML_PATH = "/FXMLs/chat.fxml";

	@FXML
	private ListView<Label> messageListView;
	@FXML
	private TextField messageTextField;
	@FXML
	private Button sendButton;

	public ChatComponent()
	{
		super();
		FXMLLoader loader = FXMLFileLoader.getLoader(CHAT_FXML_PATH);
		loader.setRoot(this);
		loader.setController(this);
		try
		{
			loader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize()
	{
		messageTextField.setOnAction(this::onMessageSend);
		sendButton.setOnAction(this::onMessageSend);
	}

	private void onMessageSend(ActionEvent event)
	{
		ClientMain.getConnection().send(ClientMessages.CHAT_MESSAGE_NAME, Serializer
				.toJson(new ChatClientMessage(Game.getGameCode(), Game.getPlayer(), messageTextField.getText())));
		messageTextField.setText("");
		messageListView.scrollTo(messageListView.getItems().size() - 1);
	}

	public void addMessage(String message)
	{
		messageListView.getItems().add(new Label(message));
	}
}
