package it.castelli.gameLogic;

import it.castelli.gameLogic.contracts.*;
import it.castelli.gameLogic.randomEvents.RandomEventManager;
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
	private static final CopyOnWriteArrayList<Player> players =
			new CopyOnWriteArrayList<>();
	/**
	 * The array of all the squares
	 */
	private Square[] board;
	private Auction auction;
	private Exchange exchange;
	private Round currentRound;
	private RandomEventManager randomEventManager;


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
								PropertyColor.BROWN)),
				new CommunityChestSquare(randomEventManager),
				new PropertySquare(
						new PropertyContract("Vicolo Stretto", 60, 4, 20, 60,
								180, 320, 450, 50,
								PropertyColor.BROWN)),
				new TaxSquare(200, "Tassa Patrimoniale"),
				new StationSquare(new StationContract("Stazione Sud", 200,
						25)),
				new PropertySquare(
						new PropertyContract("Bastioni Gransasso", 100, 6, 30,
								90, 270, 400, 550, 50,
								PropertyColor.LIGHT_BLUE)),
				new ChanceSquare(randomEventManager),
				new PropertySquare(
						new PropertyContract("Viale Monterosa", 100, 6, 30, 90,
								270, 400, 550, 50,
								PropertyColor.LIGHT_BLUE)),
				new PropertySquare(
						new PropertyContract("Viale Vesuvio", 120, 8, 40, 100,
								300, 450, 600, 50,
								PropertyColor.LIGHT_BLUE)),
				new JustVisitingSquare(),
				new PropertySquare(
						new PropertyContract("Via Accademia", 140, 10, 50, 150,
								450, 620, 750, 100,
								PropertyColor.MAGENTA)),
				new CompanySquare(
						new CompanyContract("Società Elettrica", CompanyContract.Company.ELECTRIC, 150)),
				new PropertySquare(
						new PropertyContract("Corsa Ateneo", 140, 10, 50, 150,
								450, 620, 750, 100,
								PropertyColor.MAGENTA)),
				new PropertySquare(
						new PropertyContract("Piazza Università", 160, 12, 60,
								180, 500, 700, 900, 100,
								PropertyColor.MAGENTA)),
				new StationSquare(
						new StationContract("Stazione Ovest", 200, 25)),
				new PropertySquare(
						new PropertyContract("Via Verdi", 180, 14, 70, 200,
								550,
								750, 950, 100,
								PropertyColor.ORANGE)),
				new CommunityChestSquare(randomEventManager),
				new PropertySquare(
						new PropertyContract("Corso Raffaello", 180, 14, 70,
								200, 550, 750, 950, 100,
								PropertyColor.ORANGE)),
				new PropertySquare(
						new PropertyContract("Piazza Dante", 200, 16, 80, 220,
								600, 800, 1000, 100,
								PropertyColor.ORANGE)),
				new JustVisitingSquare(),
				new PropertySquare(
						new PropertyContract("Via Marco Polo", 220, 18, 90,
								250,
								700, 880, 1050, 150,
								PropertyColor.RED)),
				new ChanceSquare(randomEventManager),
				new PropertySquare(
						new PropertyContract("Corso Magellano", 220, 18, 90,
								250, 700, 880, 1050, 150,
								PropertyColor.RED)),
				new PropertySquare(
						new PropertyContract("Largo Colombo", 240, 20, 100,
								300,
								750, 900, 1100, 150,
								PropertyColor.RED)),
				new StationSquare(
						new StationContract("Stazione Nord", 200, 25)),
				new PropertySquare(
						new PropertyContract("Viale Costantino", 260, 22, 110,
								330, 800, 1000, 1200, 150,
								PropertyColor.YELLOW)),
				new PropertySquare(
						new PropertyContract("Viale Traiano", 260, 22, 110,
								330,
								800, 1000, 1200, 150,
								PropertyColor.YELLOW)),
				new CompanySquare(
						new CompanyContract("Società Acqua Potabile", CompanyContract.Company.WATER, 150)),
				new PropertySquare(
						new PropertyContract("Piazza Giulio Cesare", 280, 24,
								120, 360, 850, 1050, 1250,
								150,
								PropertyColor.YELLOW)),
				new GoToJailSquare(),
				new PropertySquare(
						new PropertyContract("Via Roma", 300, 26, 130, 400,
								900,
								1100, 1300, 200,
								PropertyColor.GREEN)),
				new PropertySquare(
						new PropertyContract("Corso Impero", 300, 26, 130, 400,
								900, 1100, 1300, 200,
								PropertyColor.GREEN)),
				new CommunityChestSquare(randomEventManager),
				new PropertySquare(
						new PropertyContract("Largo Augusto", 320, 28, 150,
								450,
								1000, 1200, 1400, 200,
								PropertyColor.GREEN)),
				new StationSquare(new StationContract("Stazione Est", 200,
						25)),
				new ChanceSquare(randomEventManager),
				new PropertySquare(
						new PropertyContract("Viale dei Giardini", 350, 35,
								200,
								500, 1100, 1300, 1500, 200,
								PropertyColor.BLUE)),
				new TaxSquare(100, "Tassa di lusso"),
				new PropertySquare(
						new PropertyContract("Parco della Vittoria", 400, 50,
								200, 600, 1400, 1700, 2000, 200,
								PropertyColor.BLUE))
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
		//TODO: inGame variable?
	}

	public void nextRound()
	{
		int newIndex = (currentRound.getPlayerIndex() + 1) % players.size();
		Player newPlayer = players.get(newIndex);
		currentRound = new Round(newPlayer, newIndex);
	}

	public void startAuction(Contract contract)
	{
		this.auction = new Auction(contract, 10, null);
		this.auction.startAuction();
	}

	public void auctionOffer(Player player, int offer)
	{
		this.auction.offer(player, offer);
	}

	public Auction getAuction()
	{
		return auction;
	}

	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}

	public void setBoard(Square[] board)
	{
		this.board = board;
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

	public void createExchange(Player player1, Player player2)
	{

	}
}
