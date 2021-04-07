package it.castelli.gui.customComponents;

import it.castelli.gui.FXMLFileLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		// CHAT
		EventHandler<ActionEvent> onMessageSend = event -> {
			// TODO: send message
		};
		messageTextField.setOnAction(onMessageSend);
		sendButton.setOnAction(onMessageSend);
	}
}
