package it.castelli.gui.controllers;

import it.castelli.ClientMain;
import it.castelli.Game;
import it.castelli.connection.messages.ClientMessages;
import it.castelli.connection.messages.EndRoundClientMessage;
import it.castelli.connection.messages.LeaveGameClientMessage;
import it.castelli.connection.messages.ThrowDiceClientMessage;
import it.castelli.gameLogic.Pawn;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.Round;
import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gameLogic.dice.DiceResult;
import it.castelli.gui.AlertUtil;
import it.castelli.gui.GUIUtils;
import it.castelli.gui.customComponents.ChatComponent;
import it.castelli.gui.customComponents.PlayerInfoComponent;
import it.castelli.gui.customComponents.SmallTerrainViewComponent;
import it.castelli.gui.customComponents.SquareComponent;
import it.castelli.gui.scene.SceneManager;
import it.castelli.gui.scene.SceneType;
import it.castelli.serialization.Serializer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static it.castelli.Game.getGameManager;
import static it.castelli.Game.getPlayer;

/**
 * FXML controller for the board scene, the main one of the game
 */
public class BoardController
{
	public static final String PROPERTY_VIEW_FXML_PATH = "/FXMLs/propertyView.fxml";
	public static final String STATION_VIEW_FXML_PATH = "/FXMLs/stationView.fxml";
	public static final String COMPANY_VIEW_FXML_PATH = "/FXMLs/companyView.fxml";
	public static final String PLAYER_INFO_FXML_PATH = "/FXMLs/playerInfo.fxml";
	private static final int SHOWN_OWNED_PROPERTIES = 9;
	/**
	 * Singleton instance
	 */
	private static BoardController instance;
	/**
	 * Array of all the Group squares on the board, containing a SquareComponent (image) and a FlowPane (pawn parent)
	 */
	private final Group[] squares = new Group[40];
	/**
	 * Maps the ImageView shown on the board with the corresponding piece
	 */
	private final HashMap<Pawn, ImageView> playerPawns = new HashMap<>();
	@FXML
	private ImageView die1Image;
	@FXML
	private ImageView die2Image;
	@FXML
	private Button throwDiceButton;
	@FXML
	private VBox playerListView;
	@FXML
	private Button endRoundButton;
	@FXML
	private Button leaveGameButton;
	@FXML
	private Button giveUpButton;
	// CHAT
	@FXML
	private ChatComponent chat;
	// BOARD
	@FXML
	private Group goSquare;
	@FXML
	private Group vicoloCortoSquare;
	@FXML
	private Group communityChestSquare1;
	@FXML
	private Group vicoloStrettoSquare;
	@FXML
	private Group patrimonialTaxSquare;
	@FXML
	private Group southStationSquare;
	@FXML
	private Group bastioniGranSassoSquare;
	@FXML
	private Group chanceSquare1;
	@FXML
	private Group vialeMonterosaSquare;
	@FXML
	private Group vialeVesuvioSquare;
	@FXML
	private Group justVisitingSquare;
	@FXML
	private Group viaAccademiaSquare;
	@FXML
	private Group electricSocietySquare;
	@FXML
	private Group corsoAteneoSquare;
	@FXML
	private Group piazzaUniversitaSquare;
	@FXML
	private Group westStationSquare;
	@FXML
	private Group viaVerdiSquare;
	@FXML
	private Group communityChestSquare2;
	@FXML
	private Group corsoRaffaelloSquare;
	@FXML
	private Group piazzaDanteSquare;
	@FXML
	private Group freeParkingSquare;
	@FXML
	private Group viaMarcoPoloSquare;
	@FXML
	private Group chanceSquare2;
	@FXML
	private Group corsoMagellanoSquare;
	@FXML
	private Group largoColomboSquare;
	@FXML
	private Group northStationSquare;
	@FXML
	private Group vialeCostantinoSquare;
	@FXML
	private Group vialeTraianoSquare;
	@FXML
	private Group waterWorksSquare;
	@FXML
	private Group piazzaGiulioCesareSquare;
	@FXML
	private Group goToJailSquare;
	@FXML
	private Group viaRomaSquare;
	@FXML
	private Group corsoImperoSquare;
	@FXML
	private Group communityChestSquare3;
	@FXML
	private Group largoAugustoSquare;
	@FXML
	private Group eastStationSquare;
	@FXML
	private Group chanceSquare3;
	@FXML
	private Group vialeDeiGiardiniSquare;
	@FXML
	private Group luxuryTaxSquare;
	@FXML
	private Group parcoDellaVittoriaSquare;
	@FXML
	private FlowPane ownedPropertiesPane;
	@FXML
	private Label moneyLabel;

	/**
	 * Singleton instance getter
	 *
	 * @return The singleton instance
	 */
	public static BoardController getInstance()
	{
		return instance;
	}

	@FXML
	private void initialize()
	{
		instance = this;

		// squares callbacks and contracts
		{
			// PROPERTIES
			SquareComponent vicoloCortoSquareComponent = (SquareComponent) vicoloCortoSquare.getChildren().get(0);
			vicoloCortoSquareComponent.setContractId(getGameManager().getSquare(1).getContract().getId());
			vicoloCortoSquare.setOnMouseClicked(
					event -> SceneManager.getInstance()
							.showTerrainView((PropertyContract) vicoloCortoSquareComponent.getContract()));
			squares[1] = vicoloCortoSquare;
			Tooltip.install(squares[1], new Tooltip(vicoloCortoSquareComponent.getContract().getName()));

			SquareComponent vicoloStrettoSquareComponent = (SquareComponent) vicoloStrettoSquare.getChildren().get(0);
			vicoloStrettoSquareComponent.setContractId(getGameManager().getSquare(3).getContract().getId());
			vicoloStrettoSquare
					.setOnMouseClicked(
							event -> SceneManager.getInstance()
									.showTerrainView((PropertyContract) vicoloStrettoSquareComponent.getContract()));
			squares[3] = vicoloStrettoSquare;
			Tooltip.install(squares[3], new Tooltip(vicoloStrettoSquareComponent.getContract().getName()));

			SquareComponent bastioniGranSassoSquareComponent =
					(SquareComponent) bastioniGranSassoSquare.getChildren().get(0);
			bastioniGranSassoSquareComponent.setContractId(getGameManager().getSquare(6).getContract().getId());
			bastioniGranSassoSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) bastioniGranSassoSquareComponent.getContract()));
			squares[6] = bastioniGranSassoSquare;
			Tooltip.install(bastioniGranSassoSquare,
			                new Tooltip(bastioniGranSassoSquareComponent.getContract().getName()));

			SquareComponent vialeMonterosaSquareComponent =
					(SquareComponent) vialeMonterosaSquare.getChildren().get(0);
			vialeMonterosaSquareComponent.setContractId(getGameManager().getSquare(8).getContract().getId());
			vialeMonterosaSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) vialeMonterosaSquareComponent.getContract()));
			squares[8] = vialeMonterosaSquare;
			Tooltip.install(vialeMonterosaSquare, new Tooltip(vialeMonterosaSquareComponent.getContract().getName()));

			SquareComponent vialeVesuvioSquareComponent = (SquareComponent) vialeVesuvioSquare.getChildren().get(0);
			vialeVesuvioSquareComponent.setContractId(getGameManager().getSquare(9).getContract().getId());
			vialeVesuvioSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) vialeVesuvioSquareComponent.getContract()));
			squares[9] = vialeVesuvioSquare;
			Tooltip.install(vialeVesuvioSquare, new Tooltip(vialeVesuvioSquareComponent.getContract().getName()));

			SquareComponent viaAccademiaSquareComponent = (SquareComponent) viaAccademiaSquare.getChildren().get(0);
			viaAccademiaSquareComponent.setContractId(getGameManager().getSquare(11).getContract().getId());
			viaAccademiaSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) viaAccademiaSquareComponent.getContract()));
			squares[11] = viaAccademiaSquare;
			Tooltip.install(viaAccademiaSquare, new Tooltip(viaAccademiaSquareComponent.getContract().getName()));

			SquareComponent corsoAteneoSquareComponent = (SquareComponent) corsoAteneoSquare.getChildren().get(0);
			corsoAteneoSquareComponent.setContractId(getGameManager().getSquare(13).getContract().getId());
			corsoAteneoSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) corsoAteneoSquareComponent.getContract()));
			squares[13] = corsoAteneoSquare;
			Tooltip.install(corsoAteneoSquare, new Tooltip(corsoAteneoSquareComponent.getContract().getName()));

			SquareComponent piazzaUniversitaSquareComponent =
					(SquareComponent) piazzaUniversitaSquare.getChildren().get(0);
			piazzaUniversitaSquareComponent.setContractId(getGameManager().getSquare(14).getContract().getId());
			piazzaUniversitaSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) piazzaUniversitaSquareComponent.getContract()));
			squares[14] = piazzaUniversitaSquare;
			Tooltip.install(piazzaUniversitaSquare,
			                new Tooltip(piazzaUniversitaSquareComponent.getContract().getName()));

			SquareComponent viaVerdiSquareComponent = (SquareComponent) viaVerdiSquare.getChildren().get(0);
			viaVerdiSquareComponent.setContractId(getGameManager().getSquare(16).getContract().getId());
			viaVerdiSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) viaVerdiSquareComponent.getContract()));
			squares[16] = viaVerdiSquare;
			Tooltip.install(viaVerdiSquare, new Tooltip(viaVerdiSquareComponent.getContract().getName()));

			SquareComponent corsoRaffaelloSquareComponent =
					(SquareComponent) corsoRaffaelloSquare.getChildren().get(0);
			corsoRaffaelloSquareComponent.setContractId(getGameManager().getSquare(18).getContract().getId());
			corsoRaffaelloSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) corsoRaffaelloSquareComponent.getContract()));
			squares[18] = corsoRaffaelloSquare;
			Tooltip.install(corsoRaffaelloSquare, new Tooltip(corsoRaffaelloSquareComponent.getContract().getName()));

			SquareComponent piazzaDanteSquareComponent = (SquareComponent) piazzaDanteSquare.getChildren().get(0);
			piazzaDanteSquareComponent.setContractId(getGameManager().getSquare(19).getContract().getId());
			piazzaDanteSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) piazzaDanteSquareComponent.getContract()));
			squares[19] = piazzaDanteSquare;
			Tooltip.install(piazzaDanteSquare, new Tooltip(piazzaDanteSquareComponent.getContract().getName()));

			SquareComponent viaMarcoPoloSquareComponent = (SquareComponent) viaMarcoPoloSquare.getChildren().get(0);
			viaMarcoPoloSquareComponent.setContractId(getGameManager().getSquare(21).getContract().getId());
			viaMarcoPoloSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) viaMarcoPoloSquareComponent.getContract()));
			squares[21] = viaMarcoPoloSquare;
			Tooltip.install(viaMarcoPoloSquare, new Tooltip(viaMarcoPoloSquareComponent.getContract().getName()));

			SquareComponent corsoMagellanoSquareComponent =
					(SquareComponent) corsoMagellanoSquare.getChildren().get(0);
			corsoMagellanoSquareComponent.setContractId(getGameManager().getSquare(23).getContract().getId());
			corsoMagellanoSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) corsoMagellanoSquareComponent.getContract()));
			squares[23] = corsoMagellanoSquare;
			Tooltip.install(corsoMagellanoSquare, new Tooltip(corsoMagellanoSquareComponent.getContract().getName()));

			SquareComponent largoColomboSquareComponent = (SquareComponent) largoColomboSquare.getChildren().get(0);
			largoColomboSquareComponent.setContractId(getGameManager().getSquare(24).getContract().getId());
			largoColomboSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) largoColomboSquareComponent.getContract()));
			squares[24] = largoColomboSquare;
			Tooltip.install(largoColomboSquare, new Tooltip(largoColomboSquareComponent.getContract().getName()));

			SquareComponent vialeCostantinoSquareComponent =
					(SquareComponent) vialeCostantinoSquare.getChildren().get(0);
			vialeCostantinoSquareComponent.setContractId(getGameManager().getSquare(26).getContract().getId());
			vialeCostantinoSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) vialeCostantinoSquareComponent.getContract()));
			squares[26] = vialeCostantinoSquare;
			Tooltip.install(vialeCostantinoSquare,
			                new Tooltip(vialeCostantinoSquareComponent.getContract().getName()));

			SquareComponent vialeTraianoSquareComponent = (SquareComponent) vialeTraianoSquare.getChildren().get(0);
			vialeTraianoSquareComponent.setContractId(getGameManager().getSquare(27).getContract().getId());
			vialeTraianoSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) vialeTraianoSquareComponent.getContract()));
			squares[27] = vialeTraianoSquare;
			Tooltip.install(vialeTraianoSquare, new Tooltip(vialeTraianoSquareComponent.getContract().getName()));

			SquareComponent piazzaGiulioCesareSquareComponent =
					(SquareComponent) piazzaGiulioCesareSquare.getChildren().get(0);
			piazzaGiulioCesareSquareComponent.setContractId(getGameManager().getSquare(29).getContract().getId());
			piazzaGiulioCesareSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) piazzaGiulioCesareSquareComponent.getContract()));
			squares[29] = piazzaGiulioCesareSquare;
			Tooltip.install(piazzaGiulioCesareSquare,
			                new Tooltip(piazzaGiulioCesareSquareComponent.getContract().getName()));

			SquareComponent viaRomaSquareComponent = (SquareComponent) viaRomaSquare.getChildren().get(0);
			viaRomaSquareComponent.setContractId(getGameManager().getSquare(31).getContract().getId());
			viaRomaSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) viaRomaSquareComponent.getContract()));
			squares[31] = viaRomaSquare;
			Tooltip.install(viaRomaSquare, new Tooltip(viaRomaSquareComponent.getContract().getName()));

			SquareComponent corsoImperoSquareComponent = (SquareComponent) corsoImperoSquare.getChildren().get(0);
			corsoImperoSquareComponent.setContractId(getGameManager().getSquare(32).getContract().getId());
			corsoImperoSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) corsoImperoSquareComponent.getContract()));
			squares[32] = corsoImperoSquare;
			Tooltip.install(corsoImperoSquare, new Tooltip(corsoImperoSquareComponent.getContract().getName()));

			SquareComponent largoAugustoSquareComponent = (SquareComponent) largoAugustoSquare.getChildren().get(0);
			largoAugustoSquareComponent.setContractId(getGameManager().getSquare(34).getContract().getId());
			largoAugustoSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) largoAugustoSquareComponent.getContract()));
			squares[34] = largoAugustoSquare;
			Tooltip.install(largoAugustoSquare, new Tooltip(largoAugustoSquareComponent.getContract().getName()));

			SquareComponent vialeDeiGiardiniSquareComponent =
					(SquareComponent) vialeDeiGiardiniSquare.getChildren().get(0);
			vialeDeiGiardiniSquareComponent.setContractId(getGameManager().getSquare(37).getContract().getId());
			vialeDeiGiardiniSquare.setOnMouseClicked(event -> SceneManager.getInstance().showTerrainView(
					(PropertyContract) vialeDeiGiardiniSquareComponent.getContract()));
			squares[37] = vialeDeiGiardiniSquare;
			Tooltip.install(vialeDeiGiardiniSquare,
			                new Tooltip(vialeDeiGiardiniSquareComponent.getContract().getName()));

			SquareComponent parcoDellaVittoriaSquareComponent =
					(SquareComponent) parcoDellaVittoriaSquare.getChildren().get(0);
			parcoDellaVittoriaSquareComponent.setContractId(getGameManager().getSquare(39).getContract().getId());
			parcoDellaVittoriaSquare.setOnMouseClicked(
					event -> SceneManager.getInstance()
							.showTerrainView((PropertyContract) parcoDellaVittoriaSquareComponent.getContract()));
			squares[39] = parcoDellaVittoriaSquare;
			Tooltip.install(parcoDellaVittoriaSquare,
			                new Tooltip(parcoDellaVittoriaSquareComponent.getContract().getName()));

			// STATIONS
			SquareComponent southStationSquareComponent = (SquareComponent) southStationSquare.getChildren().get(0);
			southStationSquareComponent.setContractId(getGameManager().getSquare(5).getContract().getId());
			southStationSquare.setOnMouseClicked(
					event -> SceneManager.getInstance()
							.showTerrainView((StationContract) southStationSquareComponent.getContract()));
			squares[5] = southStationSquare;
			Tooltip.install(southStationSquare, new Tooltip(southStationSquareComponent.getContract().getName()));

			SquareComponent westStationSquareComponent = (SquareComponent) westStationSquare.getChildren().get(0);
			westStationSquareComponent.setContractId(getGameManager().getSquare(15).getContract().getId());
			westStationSquare.setOnMouseClicked(
					event -> SceneManager.getInstance()
							.showTerrainView((StationContract) westStationSquareComponent.getContract()));
			squares[15] = westStationSquare;
			Tooltip.install(westStationSquare, new Tooltip(westStationSquareComponent.getContract().getName()));

			SquareComponent northStationSquareComponent = (SquareComponent) northStationSquare.getChildren().get(0);
			northStationSquareComponent.setContractId(getGameManager().getSquare(25).getContract().getId());
			northStationSquare.setOnMouseClicked(
					event -> SceneManager.getInstance()
							.showTerrainView((StationContract) northStationSquareComponent.getContract()));
			squares[25] = northStationSquare;
			Tooltip.install(northStationSquare, new Tooltip(northStationSquareComponent.getContract().getName()));

			SquareComponent eastStationSquareComponent = (SquareComponent) eastStationSquare.getChildren().get(0);
			eastStationSquareComponent.setContractId(getGameManager().getSquare(35).getContract().getId());
			eastStationSquare.setOnMouseClicked(
					event -> SceneManager.getInstance()
							.showTerrainView((StationContract) eastStationSquareComponent.getContract()));
			squares[35] = eastStationSquare;
			Tooltip.install(eastStationSquare, new Tooltip(eastStationSquareComponent.getContract().getName()));

			// COMPANIES
			SquareComponent electricSocietySquareComponent =
					(SquareComponent) electricSocietySquare.getChildren().get(0);
			electricSocietySquareComponent.setContractId(getGameManager().getSquare(12).getContract().getId());
			electricSocietySquare.setOnMouseClicked(
					event -> SceneManager.getInstance()
							.showTerrainView((CompanyContract) electricSocietySquareComponent.getContract()));
			squares[12] = electricSocietySquare;
			Tooltip.install(electricSocietySquare, new Tooltip(electricSocietySquareComponent.getContract().getName()));

			SquareComponent waterWorksSquareComponent = (SquareComponent) waterWorksSquare.getChildren().get(0);
			waterWorksSquareComponent.setContractId(getGameManager().getSquare(28).getContract().getId());
			waterWorksSquare.setOnMouseClicked(event -> SceneManager.getInstance()
							.showTerrainView((CompanyContract) waterWorksSquareComponent.getContract()));
			squares[28] = waterWorksSquare;
			Tooltip.install(waterWorksSquare, new Tooltip(waterWorksSquareComponent.getContract().getName()));

			// CHANCES
			squares[7] = chanceSquare1;
			squares[22] = chanceSquare2;
			squares[36] = chanceSquare3;
			Tooltip.install(chanceSquare1, new Tooltip("Imprevisto"));
			Tooltip.install(chanceSquare2, new Tooltip("Imprevisto"));
			Tooltip.install(chanceSquare3, new Tooltip("Imprevisto"));

			// COMMUNITY CHESTS
			squares[2] = communityChestSquare1;
			squares[17] = communityChestSquare2;
			squares[33] = communityChestSquare3;
			Tooltip.install(communityChestSquare1, new Tooltip("Probabilita'"));
			Tooltip.install(communityChestSquare2, new Tooltip("Probabilita'"));
			Tooltip.install(communityChestSquare3, new Tooltip("Probabilita'"));

			// TAXES
			squares[4] = patrimonialTaxSquare;
			squares[38] = luxuryTaxSquare;
			Tooltip.install(patrimonialTaxSquare, new Tooltip("Tassa patrimoniale"));
			Tooltip.install(luxuryTaxSquare, new Tooltip("Tassa di lusso"));

			// CORNERS
			squares[0] = goSquare;
			squares[10] = justVisitingSquare;
			squares[20] = freeParkingSquare;
			squares[30] = goToJailSquare;
			Tooltip.install(goSquare, new Tooltip("Via!"));
			Tooltip.install(justVisitingSquare, new Tooltip("Transito"));
			Tooltip.install(freeParkingSquare, new Tooltip("Parcheggio gratuito"));
			Tooltip.install(goToJailSquare, new Tooltip("Andate in prigione"));
		}

		// button callback
		throwDiceButton.setOnAction(event -> ClientMain.getConnection().send(ClientMessages.THROW_DICE_MESSAGE_NAME,
		                                                                     Serializer
				                                                                     .toJson(new ThrowDiceClientMessage(
						                                                                     Game.getGameCode()))));
		endRoundButton.setTooltip(new Tooltip("Lasciate il turno al giocatore successivo"));
		endRoundButton.setOnAction(event -> {
			if (Game.getGameManager().getCurrentRound().isDiceThrown())
				if (Game.getPlayer().hasMoney(0))
					ClientMain.getConnection().send(ClientMessages.END_ROUND_MESSAGE_NAME,
					                                Serializer.toJson(new EndRoundClientMessage(Game.getGameCode())));
				else
					AlertUtil.showInformationAlert("Debito", "Siete in debito",
					                               "Saldate il debito prima di finire il turno. Se finite le " +
					                               "risorse perderete la partita.");
			else if (!Game.getPlayer().betterEquals(Game.getGameManager().getCurrentRound().getCurrentActivePlayer()))
				AlertUtil.showInformationAlert("turno!", "Non e' il Vostro turno",
				                               "Non potete finire il turno perche' non e' il Vostro turno.");
			else
				AlertUtil.showInformationAlert("Tirate!", "Dovete tirare i dadi",
				                               "Non potete finire il turno senza tirare prima i dadi.");
		});

		leaveGameButton.setTooltip(new Tooltip("Abbandonate la partita corrente (non potrete piu' rientrare)"));
		leaveGameButton.setOnAction(event -> {
			Optional<ButtonType> confirm =
					AlertUtil.showConfirmationAlert("Conferma", "Volete davvero uscire?",
					                                "Non potrete piu' rientrare nella partita. Siete veramente " +
					                                "sicuri?");
			if (confirm.isPresent())
			{
				if (confirm.get().equals(ButtonType.OK))
				{
					if (Game.getGameManager().getCurrentRound().getCurrentActivePlayer().betterEquals(getPlayer()))
						ClientMain.getConnection().send(ClientMessages.END_ROUND_MESSAGE_NAME, Serializer
								.toJson(new EndRoundClientMessage(Game.getGameCode())));
					ClientMain.getConnection().send(ClientMessages.LEAVE_GAME_MESSAGE_NAME,
					                                Serializer.toJson(new LeaveGameClientMessage(Game.getGameCode())));

					SceneManager.getInstance().showScene(SceneType.MAIN_MENU);
				}
			}
		});

		giveUpButton.setTooltip(new Tooltip("Se siete in debito, abbandonate la partita e perdete :("));
		giveUpButton.setOnAction(event -> {
			Optional<ButtonType> confirm;
			if (Game.getPlayer().hasMoney(0))
				confirm = AlertUtil.showConfirmationAlert("Conferma", "Volete davvero arrendervi??",
				                                          "Avete ancora soldi a disposizione per giocare...");
			else
				confirm = Optional.of(ButtonType.OK);

			if (confirm.isPresent())
			{
				if (confirm.get().equals(ButtonType.OK))
				{
					if (Game.getGameManager().getCurrentRound().getCurrentActivePlayer().betterEquals(getPlayer()))
						ClientMain.getConnection().send(ClientMessages.END_ROUND_MESSAGE_NAME, Serializer
								.toJson(new EndRoundClientMessage(Game.getGameCode())));
					ClientMain.getConnection().send(ClientMessages.LEAVE_GAME_MESSAGE_NAME,
					                                Serializer.toJson(new LeaveGameClientMessage(Game.getGameCode())));

					SceneManager.getInstance().showScene(SceneType.MAIN_MENU);
				}
			}
		});

		Tooltip.install(moneyLabel, new Tooltip("I Vostri soldi"));
	}

	/**
	 * Should be called when the Board scene is loaded by the SceneManager
	 */
	public void onSceneLoaded()
	{
		// player pawns
		playerPawns.clear();

		for (Player player : Game.getGameManager().getPlayers())
		{
			String imagePath = GUIUtils.getPawnPaths().get(player.getPawn());
			Image pawnImage = new Image(String.valueOf(getClass().getResource(imagePath)));
			ImageView pawnImageView = new ImageView(pawnImage);
			pawnImageView.setPreserveRatio(true);
			playerPawns.put(player.getPawn(), pawnImageView);
		}

		update();
	}

	/**
	 * Calculate and show the owned terrains in the pane under the board
	 */
	private void calculateOwnedTerrains()
	{
		Label showOtherProperties =
				(Label) ownedPropertiesPane.getChildren().get(ownedPropertiesPane.getChildren().size() - 1);
		ownedPropertiesPane.getChildren().clear();
		for (int i = 0; i < SHOWN_OWNED_PROPERTIES; i++)
		{
			SmallTerrainViewComponent terrainView = new SmallTerrainViewComponent(null);
			terrainView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			ownedPropertiesPane.getChildren().add(terrainView);
		}
		ownedPropertiesPane.getChildren().add(showOtherProperties);

		ownedPropertiesPane.getChildren().get(SHOWN_OWNED_PROPERTIES)
				.setOnMouseClicked(event -> {
					OwnedTerrainViewController.getInstance().update();
					SceneManager.getInstance().showScene(SceneType.OWNED_TERRAIN);
				});

		SmallTerrainViewComponent[] ownedTerrains = new SmallTerrainViewComponent[SHOWN_OWNED_PROPERTIES];
		ArrayList<Contract> mostProductiveContracts = new ArrayList<>(SHOWN_OWNED_PROPERTIES);

		for (int i = 0; i < SHOWN_OWNED_PROPERTIES; i++)
		{
			Node terrainView = ownedPropertiesPane.getChildren().get(i);
			if (terrainView instanceof SmallTerrainViewComponent)
				ownedTerrains[i] = (SmallTerrainViewComponent) terrainView;
			else
				continue;

			if (getPlayer() == null)
			{
				ownedTerrains[i].setVisible(false);
				ownedTerrains[i].setDisable(true);
				continue;
			}

			if (getPlayer().getContracts().size() > 0)
			{
				Contract mostProductiveContract = null;

				for (Contract contract : getPlayer().getContracts())
					if (!mostProductiveContracts.contains(contract))
						mostProductiveContract = contract;

				for (Contract contract : getPlayer().getContracts())
				{
					if (mostProductiveContract != null)
						if (contract.getRevenue() > mostProductiveContract.getRevenue() &&
						    !mostProductiveContracts.contains(contract))
							mostProductiveContract = contract;
				}

				if (mostProductiveContract != null)
				{
					ownedTerrains[i].setDisable(false);
					ownedTerrains[i].setVisible(true);
					ownedTerrains[i].setContract(mostProductiveContract);
				}
				else
				{
					ownedTerrains[i].setVisible(false);
					ownedTerrains[i].setDisable(true);
				}
				mostProductiveContracts.add(mostProductiveContract);
			}
			else
			{
				ownedTerrains[i].setVisible(false);
				ownedTerrains[i].setDisable(true);
			}
		}
	}

	/**
	 * Update the label showing the money of the player
	 */
	public void updateMoneyLabel()
	{
		if (getPlayer() != null)
			moneyLabel.setText(getPlayer().getMoney() + "M");
	}

	/**
	 * Update the list view of all players
	 */
	public void updatePlayerListView()
	{
		playerListView.getChildren().clear();

		for (Player player : Game.getGameManager().getPlayers())
		{
			PlayerInfoComponent playerInfoComponent = new PlayerInfoComponent(player);
			playerInfoComponent.setCursor(Cursor.HAND);
			playerInfoComponent.setOnMouseClicked(event -> SceneManager.getInstance().showPlayerInfoView(player));
			playerInfoComponent.setMaxHeight(Double.MAX_VALUE);
			playerInfoComponent.setMaxWidth(Double.MAX_VALUE);
			VBox.setVgrow(playerInfoComponent, Priority.ALWAYS);
			playerListView.getChildren().add(playerInfoComponent);
		}
	}

	/**
	 * Update the view of player pawns on the board
	 */
	public void updatePawnsOnBoard()
	{
		for (int i = 0; i < squares.length; i++)
		{
			Group square = squares[i];
			FlowPane flowPane = (FlowPane) square.getChildren().get(1);

			if (i != 10)
				flowPane.getChildren().clear();
			else
			{
				// prison / just visiting
				((VBox) flowPane.getChildren().get(0)).getChildren().clear();
				((FlowPane) flowPane.getChildren().get(1)).getChildren().clear();
				((HBox) flowPane.getChildren().get(2)).getChildren().clear();
			}
		}

		for (Player player : Game.getGameManager().getPlayers())
		{
			FlowPane flowPane = (FlowPane) squares[player.getPosition()].getChildren().get(1);
			ObservableList<Node> children = flowPane.getChildren();
			ImageView pawnImageView = playerPawns.get(player.getPawn());
			if (player.getPosition() != 10)
			{
				flowPane.setVgap(5);
				flowPane.setHgap(5);
				if (pawnImageView != null)
					children.add(pawnImageView);
			}
			else
			{
				VBox vBox = (VBox) flowPane.getChildren().get(0);
				FlowPane flowPane1 = (FlowPane) flowPane.getChildren().get(1);
				HBox hBox = (HBox) flowPane.getChildren().get(2);

				if (player.isInPrison())
				{
					if (pawnImageView != null)
						flowPane1.getChildren().add(pawnImageView);
				}
				else
				{
					if (vBox.getChildren().size() < 3)
					{
						if (pawnImageView != null)
							vBox.getChildren().add(pawnImageView);
					}
					else
					{
						if (pawnImageView != null)
							hBox.getChildren().add(pawnImageView);
					}
				}
			}
		}
		for (Player player : Game.getGameManager().getPlayers())
		{
			FlowPane flowPane = (FlowPane) squares[player.getPosition()].getChildren().get(1);
			ObservableList<Node> children = flowPane.getChildren();
			ImageView pawnImageView = playerPawns.get(player.getPawn());
			if (pawnImageView != null)
			{
				int size;
				if (player.isInPrison())
				{
					size = switch (((FlowPane) children.get(1)).getChildren().size())
							{
								case 1 -> 30;
								case 2, 3, 4 -> 20;
								case 5, 6 -> 10;
								default -> 5;
							};
				}
				else
				{
					if (player.getPosition() == 10)
					{
						size = 15;
					}
					else
					{
						size = switch (children.size())
								{
									case 1 -> 30;
									case 2, 3, 4 -> 20;
									case 5, 6 -> 15;
									default -> 10;
								};
					}
				}
				pawnImageView.setFitHeight(size);
				pawnImageView.setFitWidth(size);
			}
		}
	}

	/**
	 * Update the view of player pawns on the board
	 */
	public void updateRound()
	{
		Round currentRound = getGameManager().getCurrentRound();
		if (currentRound == null)
			return;
		boolean yourTurn = getPlayer().betterEquals(currentRound.getCurrentActivePlayer());

		// player list view
		ObservableList<Node> list = playerListView.getChildren();
		for (Node element : list)
		{
			PlayerInfoComponent player = (PlayerInfoComponent) element;
			if (player.getPlayer().betterEquals(currentRound.getCurrentActivePlayer()))
				player.setRound();
		}

		// hide buttons
		endRoundButton.setVisible(yourTurn);
		endRoundButton.setDisable(!yourTurn);

		throwDiceButton.setVisible(yourTurn && !currentRound.isDiceThrown());
		throwDiceButton.setDisable(!(yourTurn && !currentRound.isDiceThrown()));
	}

	/**
	 * Update the labels of all players if they are in prison
	 */
	private void updateInPrison()
	{
		ObservableList<Node> list = playerListView.getChildren();
		for (Node element : list)
		{
			PlayerInfoComponent player = (PlayerInfoComponent) element;
			player.setInPrison(player.getPlayer().isInPrison());
		}
	}

	/**
	 * Update the images of the dice
	 *
	 * @param result The result of a dice throw to put into dice
	 */
	public void updateDice(DiceResult result)
	{
		Image firstDieImage = new Image(
				String.valueOf(getClass().getResource("/images/dice/face_" + result.getFirstResult() + ".png")));
		die1Image.setImage(firstDieImage);
		Image secondDieImage = new Image(
				String.valueOf(getClass().getResource("/images/dice/face_" + result.getSecondResult() + ".png")));
		die2Image.setImage(secondDieImage);
	}

	/**
	 * Set the border color of all squares
	 */
	public void updateBoard()
	{
		for (Group square : squares)
		{
			SquareComponent squareComponent = (SquareComponent) square.getChildren().get(0);
			Contract contract = squareComponent.getContract();
			if (contract != null)
			{
				if (contract.getOwner() == null)
				{
					square.getChildren().get(1).setStyle("-fx-border-width: 0");
				}
				else
				{
					square.getChildren().get(1)
							.setStyle(
									"-fx-border-color: " + GUIUtils.getPawnColor().get(contract.getOwner().getPawn())
									+ ";-fx-border-width: 2");
					if (contract instanceof PropertyContract)
					{
						int numberOfHouses = ((PropertyContract) contract).getNumberOfHouses();
						HBox houseHolder = (HBox) square.getChildren().get(2);
						houseHolder.getChildren().clear();
						if (numberOfHouses > 0)
						{
							if (numberOfHouses < 5)
							{
								for (int j = 0; j < numberOfHouses; j++)
								{
									String imagePath = "/images/houses/house.png";
									Image houseImage = new Image(String.valueOf(getClass().getResource(imagePath)));
									ImageView houseImageView = new ImageView(houseImage);
									houseImageView.setFitWidth(12);
									houseImageView.setFitHeight(20);
									houseHolder.getChildren().add(houseImageView);
								}
							}
							else
							{
								String imagePath = "/images/houses/hotel.png";
								Image houseImage = new Image(String.valueOf(getClass().getResource(imagePath)));
								ImageView houseImageView = new ImageView(houseImage);
								houseImageView.setFitWidth(20);
								houseImageView.setFitHeight(20);
								houseHolder.getChildren().add(houseImageView);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Update everything (ownedTerrains, moneyLabel, playerListView, pawnsOnBoard)
	 */
	public void update()
	{
		calculateOwnedTerrains();
		updateMoneyLabel();
		updatePlayerListView();
		updatePawnsOnBoard();
		updateRound();
		updateInPrison();
		updateBoard();
	}

	/**
	 * Get the board chat component
	 *
	 * @return The board chat component
	 */
	public ChatComponent getChat()
	{
		return chat;
	}
}
