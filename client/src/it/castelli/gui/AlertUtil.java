package it.castelli.gui;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.BuyContractClientMessage;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gui.customComponents.SmallTerrainViewComponent;
import it.castelli.serialization.Serializer;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.LinkedList;
import java.util.Optional;

/**
 * Provides methods for showing different types of dialogs and alert windows. Cannot be instantiated.
 */
public final class AlertUtil
{
	private static final LinkedList<Alert> alertQueue = new LinkedList<>();

	/**
	 * Private constructor to prevent instantiating
	 */
	private AlertUtil()
	{
	}

	private static void showGenericAlert(Alert.AlertType alertType, String title, String header, String content)
	{
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alertQueue.add(alert);
		pollAlerts();
	}

	/**
	 * Shows a dialog with a text input
	 *
	 * @param defaultValue The value which is already written when the dialogue opens
	 * @param title        The title of the dialogue (in the top-center of the window)
	 * @param headerText   The header, the bigger message
	 * @param contentText  The text near the input field (at his left)
	 * @return An optional string possibly containing the content of the input field
	 */
	public static Optional<String> showTextInputDialogue(String defaultValue, String title, String headerText,
	                                                     String contentText)
	{
		TextInputDialog dialog = new TextInputDialog(defaultValue);
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setContentText(contentText);
		return dialog.showAndWait();
	}

	/**
	 * Shows a dialog with "Cancel" and "Ok" options
	 *
	 * @param title       The title of the dialogue (in the top-center of the window)
	 * @param headerText  The header, the bigger message
	 * @param contentText The content of the message
	 * @return The result of the operation
	 */
	public static Optional<ButtonType> showConfirmationAlert(String title, String headerText, String contentText)
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		return alert.showAndWait();
	}

	/**
	 * Shows a simple dialog message not interactive (with an ok button to close)
	 *
	 * @param title   The title of the dialogue (in the top-center of the window)
	 * @param header  The header, the bigger message
	 * @param content The content of the message
	 */
	public static void showInformationAlert(String title, String header, String content)
	{
		showGenericAlert(Alert.AlertType.INFORMATION, title, header, content);
	}

	/**
	 * Shows an error dialog, similar to the information, but with an error sign
	 *
	 * @param title   The title of the dialogue (in the top-center of the window)
	 * @param header  The header, the bigger message
	 * @param content The content of the message
	 */
	public static void showErrorAlert(String title, String header, String content)
	{
		showGenericAlert(Alert.AlertType.ERROR, title, header, content);
	}

	/**
	 * Show the open alerts and removes them from the queue
	 */
	public static void pollAlerts()
	{
		if (alertQueue.isEmpty())
			return;

		alertQueue.peek().showAndWait();
		alertQueue.poll();
		pollAlerts();
	}

	/**
	 * Know whether an alert window is open or not
	 *
	 * @return True if an alert window is opened, false otherwise
	 */
	public static boolean anyAlertWindowOpen()
	{
		return !alertQueue.isEmpty();
	}

	public static void showSuggestionOfBuy(String title, String headerText, String contentText, Contract contract)
	{
		if (alertQueue.isEmpty())
			suggestBuying(title, headerText, contentText, contract);
		else
			alertQueue.peek().setOnCloseRequest(event -> suggestBuying(title, headerText, contentText, contract));
	}

	private static void suggestBuying(String title, String headerText, String contentText, Contract contract)
	{
		if (Game.getPlayer().hasMoney(contract.getValue()))
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle(title);
			alert.setHeaderText(headerText);
			alert.setContentText(contentText);
			ButtonType buyButtonType = new ButtonType("Acquisto");
			ButtonType auctionButtonType = new ButtonType("Asta");

			alert.setGraphic(new SmallTerrainViewComponent(contract));

			alert.getButtonTypes().setAll(buyButtonType, auctionButtonType);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent())
			{
				if (result.get() == buyButtonType)
				{
					ClientMain.getConnection().send(ClientMessages.BUY_CONTRACT_MESSAGE_NAME, Serializer
							.toJson(new BuyContractClientMessage(contract, Game.getPlayer(), true, Game
									.getGameCode())));
				}
				else if (result.get() == auctionButtonType)
					ClientMain.getConnection().send(ClientMessages.BUY_CONTRACT_MESSAGE_NAME, Serializer
							.toJson(new BuyContractClientMessage(contract, Game.getPlayer(), false, Game
									.getGameCode())));
				else showSuggestionOfBuy(title, headerText, contentText, contract);
			}
		}
		else
		{
			ClientMain.getConnection().send(ClientMessages.BUY_CONTRACT_MESSAGE_NAME, Serializer
					.toJson(new BuyContractClientMessage(contract, Game.getPlayer(), false, Game.getGameCode())));
		}
	}
}