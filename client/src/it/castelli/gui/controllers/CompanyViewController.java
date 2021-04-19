package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.MortgageContractClientMessage;
import it.castelli.connection.messages.SellContractClientMessage;
import it.castelli.connection.messages.UnmortgageContractClientMessage;
import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Controller for companyView FXML
 */
public class CompanyViewController
{
	private static CompanyViewController instance;
	/**
	 * The id of the contract
	 */
	private int contractId;
	@FXML
	private ImageView companyImage;
	@FXML
	private Label companyNameLabel;
	@FXML
	private HBox onlyIfOwnedPane;
	@FXML
	private Button sellButton;
	@FXML
	private Button mortgageButton;

	public static CompanyViewController getInstance()
	{
		return instance;
	}

	@FXML
	public void initialize()
	{
		instance = this;
	}

	/**
	 * Update the visuals of the CompanyView.
	 *
	 * @param id The id of the contract of the company
	 */
	public void setContract(int id)
	{
		contractId = id;
		update();
	}

	/**
	 * Update the visual information
	 */
	public void update()
	{
		CompanyContract contract = (CompanyContract) Game.getGameManager().getContract(contractId);

		switch (contract.getCompany())
		{
			case ELECTRIC -> companyImage.setImage(new Image("/images/squares/electric_society.png"));
			case WATER -> companyImage.setImage(new Image("/images/squares/water_works.png"));
		}

		companyNameLabel.setText(contract.getName());

		if (contract.getOwner() != null)
		{
			boolean isOwnedByMe = contract.getOwner().toPlayer().betterEquals(Game.getPlayer());
			boolean isMyRound = contract.getOwner().toPlayer()
					.betterEquals(Game.getGameManager().getCurrentRound().getCurrentActivePlayer());
			onlyIfOwnedPane.setVisible(isOwnedByMe);
			onlyIfOwnedPane.setDisable(!isOwnedByMe && !isMyRound);
		}
		else
		{
			onlyIfOwnedPane.setVisible(false);
			onlyIfOwnedPane.setDisable(true);
		}

		sellButton.setTooltip(new Tooltip("Vendete questa compagnia"));
		sellButton.setOnAction(event -> {
			ClientMain.getConnection().send(ClientMessages.SELL_CONTRACT_MESSAGE_NAME, Serializer
					.toJson(new SellContractClientMessage(Game.getGameCode(), contract)));
			SceneManager.getInstance().getStageByType(SceneType.COMPANY_VIEW).close();
		});

		if (contract.isMortgaged())
			mortgageButton.setDisable(!Game.getPlayer().hasMoney(contract.getMortgageValue() * 11 / 10));

		mortgageButton.setTooltip(new Tooltip(contract.isMortgaged() ?
				"Rimuovete l'ipoteca e riottenete il terreno (pagando il 10% in piu' del" +
						" costo dell'ipoteca)" :
				"Ipotecate il terreno"));
		mortgageButton.setText(contract.isMortgaged() ? "Sciogli ipoteca" : "Ipoteca");
		mortgageButton.setOnAction(event -> {
			if (contract.isMortgaged())
				ClientMain.getConnection().send(ClientMessages.UNMORTGAGE_CONTRACT_MESSAGE_NAME, Serializer
						.toJson(new UnmortgageContractClientMessage(Game.getGameCode(), contract)));
			else
				ClientMain.getConnection().send(ClientMessages.MORTGAGE_CONTRACT_MESSAGE_NAME, Serializer
						.toJson(new MortgageContractClientMessage(Game.getGameCode(), contract)));
			SceneManager.getInstance().getStageByType(SceneType.COMPANY_VIEW).close();
		});
	}
}
