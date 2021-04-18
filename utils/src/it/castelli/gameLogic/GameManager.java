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
	 * The array of all the squares
	 */
	private final Square[] board;
	private final CopyOnWriteArrayList<Exchange> exchanges = new CopyOnWriteArrayList<>();
	private final RandomEventManager randomEventManager = new RandomEventManager();
	private Auction auction;
	private Round currentRound;
	private boolean inGame = false;

	/**
	 * Constructor for the GameManager
	 */
	public GameManager()
	{
		board = new Square[]{
				new GoSquare(),
				new PropertySquare(
						new PropertyContract("Vicolo Corto", 60, 2, 10, 30, 90,
								160, 250, 50,
								PropertyColor.BROWN, 2)),
				new CommunityChestSquare(),
				new PropertySquare(
						new PropertyContract("Vicolo Stretto", 60, 4, 20, 60,
								180, 320, 450, 50,
								PropertyColor.BROWN, 2)),
				new TaxSquare(200, "Tassa Patrimoniale"),
				new StationSquare(new StationContract("Stazione Sud", 200,
						25)),
				new PropertySquare(
						new PropertyContract("Bastioni Gransasso", 100, 6, 30,
								90, 270, 400, 550, 50,
								PropertyColor.LIGHT_BLUE, 3)),
				new ChanceSquare(),
				new PropertySquare(
						new PropertyContract("Viale Monterosa", 100, 6, 30, 90,
								270, 400, 550, 50,
								PropertyColor.LIGHT_BLUE, 3)),
				new PropertySquare(
						new PropertyContract("Viale Vesuvio", 120, 8, 40, 100,
								300, 450, 600, 50,
								PropertyColor.LIGHT_BLUE, 3)),
				new JustVisitingSquare(),
				new PropertySquare(
						new PropertyContract("Via Accademia", 140, 10, 50, 150,
								450, 620, 750, 100,
								PropertyColor.MAGENTA, 3)),
				new CompanySquare(
						new CompanyContract("Societa' Elettrica", CompanyContract.Company.ELECTRIC, 150)),
				new PropertySquare(
						new PropertyContract("Corso Ateneo", 140, 10, 50, 150,
								450, 620, 750, 100,
								PropertyColor.MAGENTA, 3)),
				new PropertySquare(
						new PropertyContract("Piazza Universita'", 160, 12, 60,
								180, 500, 700, 900, 100,
								PropertyColor.MAGENTA, 3)),
				new StationSquare(
						new StationContract("Stazione Ovest", 200, 25)),
				new PropertySquare(
						new PropertyContract("Via Verdi", 180, 14, 70, 200,
								550,
								750, 950, 100,
								PropertyColor.ORANGE, 3)),
				new CommunityChestSquare(),
				new PropertySquare(
						new PropertyContract("Corso Raffaello", 180, 14, 70,
								200, 550, 750, 950, 100,
								PropertyColor.ORANGE, 3)),
				new PropertySquare(
						new PropertyContract("Piazza Dante", 200, 16, 80, 220,
								600, 800, 1000, 100,
								PropertyColor.ORANGE, 3)),
				new JustVisitingSquare(),
				new PropertySquare(
						new PropertyContract("Via Marco Polo", 220, 18, 90,
								250,
								700, 880, 1050, 150,
								PropertyColor.RED, 3)),
				new ChanceSquare(),
				new PropertySquare(
						new PropertyContract("Corso Magellano", 220, 18, 90,
								250, 700, 880, 1050, 150,
								PropertyColor.RED, 3)),
				new PropertySquare(new PropertyContract("Largo Colombo", 240, 20, 100,
						300,
						750, 900, 1100, 150,
						PropertyColor.RED, 3)),
				new StationSquare(
						new StationContract("Stazione Nord", 200, 25)),
				new PropertySquare(
						new PropertyContract("Viale Costantino", 260, 22, 110,
								330, 800, 1000, 1200, 150,
								PropertyColor.YELLOW, 3)),
				new PropertySquare(
						new PropertyContract("Viale Traiano", 260, 22, 110,
								330,
								800, 1000, 1200, 150,
								PropertyColor.YELLOW, 3)),
				new CompanySquare(
						new CompanyContract("Societa' Acqua Potabile", CompanyContract.Company.WATER, 150)),
				new PropertySquare(
						new PropertyContract("Piazza Giulio Cesare", 280, 24,
								120, 360, 850, 1050, 1250,
								150,
								PropertyColor.YELLOW, 3)),
				new GoToJailSquare(),
				new PropertySquare(
						new PropertyContract("Via Roma", 300, 26, 130, 400,
								900,
								1100, 1300, 200,
								PropertyColor.GREEN, 3)),
				new PropertySquare(
						new PropertyContract("Corso Impero", 300, 26, 130, 400,
								900, 1100, 1300, 200,
								PropertyColor.GREEN, 3)),
				new CommunityChestSquare(),
				new PropertySquare(
						new PropertyContract("Largo Augusto", 320, 28, 150,
								450,
								1000, 1200, 1400, 200,
								PropertyColor.GREEN, 3)),
				new StationSquare(new StationContract("Stazione Est", 200,
						25)),
				new ChanceSquare(),
				new PropertySquare(
						new PropertyContract("Viale dei Giardini", 350, 35,
								200,
								500, 1100, 1300, 1500, 200,
								PropertyColor.BLUE, 2)),
				new TaxSquare(100, "Tassa di lusso"),
				new PropertySquare(
						new PropertyContract("Parco della Vittoria", 400, 50,
								200, 600, 1400, 1700, 2000, 200,
								PropertyColor.BLUE, 2))
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
		System.out.println("Still " + exchanges.size() + " exchanges active");
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
}
