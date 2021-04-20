package it.castelli.gui.customComponents;

import it.castelli.gameLogic.Player;
import it.castelli.gui.FXMLFileLoader;
import it.castelli.gui.GUIUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * GUI component containing the info of a player
 */
public class PlayerInfoComponent extends AnchorPane
{
	public static final String PLAYER_INFO_ELEMENT_FXML_PATH = "/FXMLs/playerInfoElement.fxml";

	/**
	 * Singleton instance
	 */
	private static PlayerInfoComponent instance;

	private Player player;
	@FXML
	private ImageView pawnImageView;
	@FXML
	private Label playerNameLabel;
	@FXML
	private Label playerMoneyLabel;
	@FXML
	private ImageView prisonImageView;

	public PlayerInfoComponent(Player player)
	{
		super();
		try
		{
			FXMLLoader loader = FXMLFileLoader.getLoader(PLAYER_INFO_ELEMENT_FXML_PATH);
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			setPlayer(player);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static PlayerInfoComponent getInstance()
	{
		return instance;
	}

	@FXML
	private void initialize()
	{
		instance = this;
		prisonImageView.setVisible(false);
	}

	/**
	 * Set that this is this player's round, by coloring the label
	 */
	public void setRound()
	{
		playerNameLabel.setStyle("-fx-background-color: " + GUIUtils.getPawnColor().get(player.getPawn()));
		playerNameLabel.setText(player.getName() + "   E' il tuo turno");
	}

	/**
	 * Set whether is in prison or not, showing a small icon near the money
	 *
	 * @param inPrison Is the player in prison?
	 */
	public void setInPrison(boolean inPrison)
	{
		prisonImageView.setVisible(inPrison);
	}

	public Player getPlayer()
	{
		return player;
	}

	/**
	 * Update all GUI based on the player passed
	 *
	 * @param player The player bound to this element
	 */
	private void setPlayer(Player player)
	{
		this.player = player;
		if (player.getPawn() != null)
			pawnImageView.setImage(
					new Image(String.valueOf(getClass().getResource(GUIUtils.getPawnPaths().get(player.getPawn())))));
		playerNameLabel.setText(player.getName());
		playerMoneyLabel.setText(player.getMoney() + "M");
	}
}
