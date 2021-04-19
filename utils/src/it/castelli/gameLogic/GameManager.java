package it.castelli.gameLogic;

import it.castelli.gameLogic.contracts.*;
import it.castelli.gameLogic.randomEvents.RandomEvent;
import it.castelli.gameLogic.randomEvents.RandomEventManager;
import it.castelli.gameLogic.randomEvents.RandomEventType;
import it.castelli.gameLogic.squares.*;
import it.castelli.gameLogic.transactions.Auction;
import it.castelli.gameLogic.transactions.Exchange;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The main class holding information about the game (like the board, the players)
 */
public class GameManager
{
	/**
	 * The list of players
	 */
	private final CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<>();

	/**
	 * The array of all contracts
	 */
	private final Contract[] allContracts;

	/**
	 * The array of all the squares
	 */
	private final Square[] board;
	/**
	 * The list of active exchanges
	 */
	private final CopyOnWriteArrayList<Exchange> exchanges = new CopyOnWriteArrayList<>();
	/**
	 * The Manager of all Chances and CommunityChests
	 */
	private final RandomEventManager randomEventManager = new RandomEventManager();
	/**
	 * The current ongoing auction
	 */
	private Auction auction;
	/**
	 * The current round
	 */
	private Round currentRound;
	/**
	 * Is the game being played or are the players in the lobby?
	 */
	private boolean inGame = false;

	/**
	 * Constructor for the GameManager
	 */
	public GameManager()
	{
		// contracts
		PropertyContract vicoloCortoContract =
				new PropertyContract(0, "Vicolo Corto", 60, 2, 10, 30, 90, 160, 250, 50, PropertyColor.BROWN, 2);
		PropertyContract vicoloStrettoContract = new PropertyContract(1, "Vicolo Stretto", 60, 4, 20, 60,
		                                                              180, 320, 450, 50,
		                                                              PropertyColor.BROWN, 2);
		StationContract stazioneSudContract = new StationContract(2, "Stazione Sud", 200, 25);
		PropertyContract bastioniGransassoContract = new PropertyContract(3, "Bastioni Gransasso", 100, 6, 30,
		                                                                  90, 270, 400, 550, 50,
		                                                                  PropertyColor.LIGHT_BLUE, 3);
		PropertyContract vialeMonterosaContract = new PropertyContract(4, "Viale Monterosa", 100, 6, 30, 90,
		                                                               270, 400, 550, 50,
		                                                               PropertyColor.LIGHT_BLUE, 3);
		PropertyContract vialeVesuvioContract = new PropertyContract(5, "Viale Vesuvio", 120, 8, 40, 100,
		                                                             300, 450, 600, 50,
		                                                             PropertyColor.LIGHT_BLUE, 3);
		PropertyContract viaAccademia = new PropertyContract(6, "Via Accademia", 140, 10, 50, 150,
		                                                     450, 620, 750, 100,
		                                                     PropertyColor.MAGENTA, 3);
		CompanyContract societaElettricaContract =
				new CompanyContract(7, "Societa' Elettrica", CompanyContract.Company.ELECTRIC, 150);
		PropertyContract corsoAteneoContract = new PropertyContract(8, "Corso Ateneo", 140, 10, 50, 150,
		                                                            450, 620, 750, 100,
		                                                            PropertyColor.MAGENTA, 3);
		PropertyContract piazzaUniversitaContract = new PropertyContract(9, "Piazza Universita'", 160, 12, 60,
		                                                                 180, 500, 700, 900, 100,
		                                                                 PropertyColor.MAGENTA, 3);
		StationContract stazioneOvestContract = new StationContract(10, "Stazione Ovest", 200, 25);
		PropertyContract viaVerdiContract = new PropertyContract(11, "Via Verdi", 180, 14, 70, 200,
		                                                         550,
		                                                         750, 950, 100,
		                                                         PropertyColor.ORANGE, 3);
		PropertyContract corsoRaffaelloContract = new PropertyContract(12, "Corso Raffaello", 180, 14, 70,
		                                                               200, 550, 750, 950, 100,
		                                                               PropertyColor.ORANGE, 3);
		PropertyContract piazzaDanteContract = new PropertyContract(13, "Piazza Dante", 200, 16, 80, 220,
		                                                            600, 800, 1000, 100,
		                                                            PropertyColor.ORANGE, 3);
		PropertyContract viaMarcoPoloContract = new PropertyContract(14, "Via Marco Polo", 220, 18, 90,
		                                                             250,
		                                                             700, 880, 1050, 150,
		                                                             PropertyColor.RED, 3);
		PropertyContract corsoMagellanoContract = new PropertyContract(15, "Corso Magellano", 220, 18, 90,
		                                                               250, 700, 880, 1050, 150,
		                                                               PropertyColor.RED, 3);
		PropertyContract largoColomboContract = new PropertyContract(16, "Largo Colombo", 240, 20, 100,
		                                                             300,
		                                                             750, 900, 1100, 150,
		                                                             PropertyColor.RED, 3);
		StationContract stazioneNordContract = new StationContract(17, "Stazione Nord", 200, 25);
		PropertyContract vialeCostantinoContract = new PropertyContract(18, "Viale Costantino", 260, 22, 110,
		                                                                330, 800, 1000, 1200, 150,
		                                                                PropertyColor.YELLOW, 3);
		PropertyContract vialeTraianoContract = new PropertyContract(19, "Viale Traiano", 260, 22, 110,
		                                                             330,
		                                                             800, 1000, 1200, 150,
		                                                             PropertyColor.YELLOW, 3);
		CompanyContract societaAcquaPotabileContract =
				new CompanyContract(20, "Societa' Acqua Potabile", CompanyContract.Company.WATER, 150);
		PropertyContract piazzaGiulioCesareContract = new PropertyContract(21, "Piazza Giulio Cesare", 280, 24,
		                                                                   120, 360, 850, 1050, 1250,
		                                                                   150, PropertyColor.YELLOW, 3);
		PropertyContract viaRomaContract = new PropertyContract(22, "Via Roma", 300, 26, 130, 400,
		                                                        900,
		                                                        1100, 1300, 200,
		                                                        PropertyColor.GREEN, 3);
		PropertyContract corsoImperoContract = new PropertyContract(23, "Corso Impero", 300, 26, 130, 400,
		                                                            900, 1100, 1300, 200,
		                                                            PropertyColor.GREEN, 3);
		PropertyContract largoAugustoContract = new PropertyContract(24, "Largo Augusto", 320, 28, 150,
		                                                             450,
		                                                             1000, 1200, 1400, 200,
		                                                             PropertyColor.GREEN, 3);
		StationContract stazioneEstContract = new StationContract(25, "Stazione Est", 200,
		                                                          25);
		PropertyContract vialeDeiGiardiniContract = new PropertyContract(26, "Viale dei Giardini", 350, 35,
		                                                                 200, 500, 1100, 1300, 1500, 200,
		                                                                 PropertyColor.BLUE, 2);
		PropertyContract parcoDellaVittoriaContract = new PropertyContract(27, "Parco della Vittoria", 400, 50,
		                                                                   200, 600, 1400, 1700, 2000, 200,
		                                                                   PropertyColor.BLUE, 2);
		allContracts = new Contract[]{
				vicoloCortoContract,
				vicoloStrettoContract,
				stazioneSudContract,
				bastioniGransassoContract,
				vialeMonterosaContract,
				vialeVesuvioContract,
				viaAccademia,
				societaElettricaContract,
				corsoAteneoContract,
				piazzaUniversitaContract,
				stazioneOvestContract,
				viaVerdiContract,
				corsoRaffaelloContract,
				piazzaDanteContract,
				viaMarcoPoloContract,
				corsoMagellanoContract,
				largoColomboContract,
				stazioneNordContract,
				vialeCostantinoContract,
				vialeTraianoContract,
				societaAcquaPotabileContract,
				piazzaGiulioCesareContract,
				viaRomaContract,
				corsoImperoContract,
				largoAugustoContract,
				stazioneEstContract,
				vialeDeiGiardiniContract,
				parcoDellaVittoriaContract
		};

		board = new Square[]{
				new GoSquare(),
				new PropertySquare(vicoloCortoContract),
				new CommunityChestSquare(),
				new PropertySquare(vicoloStrettoContract),
				new TaxSquare(200, "Tassa Patrimoniale"),
				new StationSquare(stazioneSudContract),
				new PropertySquare(bastioniGransassoContract),
				new ChanceSquare(),
				new PropertySquare(vialeMonterosaContract),
				new PropertySquare(vialeVesuvioContract),
				new JustVisitingSquare(),
				new PropertySquare(viaAccademia),
				new CompanySquare(societaElettricaContract),
				new PropertySquare(corsoAteneoContract),
				new PropertySquare(piazzaUniversitaContract),
				new StationSquare(stazioneOvestContract),
				new PropertySquare(viaVerdiContract),
				new CommunityChestSquare(),
				new PropertySquare(corsoRaffaelloContract),
				new PropertySquare(piazzaDanteContract),
				new JustVisitingSquare(),
				new PropertySquare(viaMarcoPoloContract),
				new ChanceSquare(),
				new PropertySquare(corsoMagellanoContract),
				new PropertySquare(largoColomboContract),
				new StationSquare(stazioneNordContract),
				new PropertySquare(vialeCostantinoContract),
				new PropertySquare(vialeTraianoContract),
				new CompanySquare(societaAcquaPotabileContract),
				new PropertySquare(piazzaGiulioCesareContract),
				new GoToJailSquare(),
				new PropertySquare(viaRomaContract),
				new PropertySquare(corsoImperoContract),
				new CommunityChestSquare(),
				new PropertySquare(largoAugustoContract),
				new StationSquare(stazioneEstContract),
				new ChanceSquare(),
				new PropertySquare(vialeDeiGiardiniContract),
				new TaxSquare(100, "Tassa di lusso"),
				new PropertySquare(parcoDellaVittoriaContract)
		};
	}

	/**
	 * Adds a player to the list of players
	 *
	 * @param player The new player
	 */
	public void addPlayer(Player player)
	{
		if (!players.contains(player))
			players.add(player);
		else
			throw new IllegalArgumentException(
					"The player already exist in this game");
	}

	/**
	 * Removes a player from the list of players
	 *
	 * @param player The player to remove
	 */
	public void removePlayer(Player player)
	{
		players.remove(player);
	}

	/**
	 * Remove all the players from the player list
	 */
	public void clearPlayers()
	{
		players.clear();
	}

	/**
	 * Get a square by its index position
	 *
	 * @param index The index position of the square
	 * @return The square at the specified index
	 */
	public Square getSquare(int index)
	{
		return board[index];
	}

	public CopyOnWriteArrayList<Player> getPlayers()
	{
		return players;
	}

	public void startGame()
	{
		Player newPlayer = players.get(0);
		currentRound = new Round(newPlayer, 0);
		inGame = true;
		randomEventManager.shuffleCards();
	}

	public void endGame()
	{
		inGame = false;
		players.clear();
		auction = null;
		exchanges.clear();
		currentRound = null;
	}

	public void nextRound()
	{
		if (players.size() > 0)
		{
			int newIndex = (currentRound.getPlayerIndex() + 1) % players.size();
			Player newPlayer = players.get(newIndex);
			currentRound = new Round(newPlayer, newIndex);
		}
	}

	public void startAuction(Contract contract, int offer)
	{
		auction = new Auction(contract, offer, null);
	}

	public void endAuction()
	{
		if (getSamePlayer(auction.getPlayer()) != null)
		{
			getSamePlayer(auction.getPlayer()).addContract(getSameContract(auction.getContract()));
			getSamePlayer(auction.getPlayer()).removeMoney(auction.getBestOfferProposed());
		}
		else
			System.out.println("Auction player is null");
	}

	public Contract getSameContract(Contract contract)
	{
		for (Square square : board)
		{
			if (square.getContract() != null)
				if (square.getContract().equals(contract))
					return square.getContract();
		}
		return null;
	}

	public Auction getAuction()
	{
		return auction;
	}

	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}

	public Round getCurrentRound()
	{
		return currentRound;
	}

	public void setCurrentRound(Round currentRound)
	{
		this.currentRound = currentRound;
	}

	public RandomEventManager getRandomEventManager()
	{
		return randomEventManager;
	}

	public void removeExchange(Exchange exchange)
	{
		exchanges.remove(exchange);
	}

	public Exchange getExchangeFromPlayer(Player player)
	{
		for (Exchange exchange : exchanges)
		{
			if (exchange.getPlayer1().betterEquals(player) || exchange.getPlayer2().betterEquals(player))
			{
				return exchange;
			}
		}
		return null;
	}

	public Square[] getBoard()
	{
		return board;
	}

	public void setBoard(Square[] board)
	{
		System.arraycopy(board, 0, this.board, 0, board.length);

		int index = 0;
		for (var square : board)
		{
			if (square.getContract() != null)
			{
				allContracts[index] = square.getContract();
				index++;
			}
		}
	}

	public void updateExchange(Exchange exchange)
	{
		for (int i = 0, exchangesSize = exchanges.size(); i < exchangesSize; i++)
		{
			if (exchanges.get(i).equals(exchange))
				exchanges.set(i, exchange);
		}
	}

	public void addExchange(Exchange exchange)
	{
		exchanges.add(exchange);
	}

	public boolean isInGame()
	{
		return inGame;
	}

	public Player getSamePlayer(Player player)
	{
		for (Player element : players)
		{
			if (element.betterEquals(player))
				return element;
		}
		return null;
	}

	public void addRandomEvent(RandomEvent event, RandomEventType type)
	{
		switch (type)
		{
			case CHANCE -> randomEventManager.addChance(event);
			case COMMUNITY_CHEST -> randomEventManager.addCommunityChest(event);
		}
	}

	/**
	 * Get a contract by its ID
	 *
	 * @param id The id of the contract to get
	 * @return The contract with the passed ID
	 */
	public Contract getContract(int id)
	{
		return allContracts[id];
	}
}
