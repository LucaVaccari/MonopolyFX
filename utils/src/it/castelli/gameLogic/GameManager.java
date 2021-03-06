package it.castelli.gameLogic;

import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gameLogic.contracts.PropertyColor;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gameLogic.squares.*;
import it.castelli.gameLogic.transactions.Auction;
import it.castelli.gameLogic.transactions.Exchange;

import java.util.HashSet;

/**
 * The main class holding information about the game (like the board, the players)
 */
public class GameManager
{
	/**
	 * Singleton instance
	 */
	private static GameManager instance;
	/**
	 * The array of all the squares
	 */
	private final Square[] board;
	/**
	 * The list of players
	 */
	private final HashSet<Player> players = new HashSet<>();
	private Auction auction;
	private Exchange exchange;

	/**
	 * Constructor for the GameManager
	 */
	private GameManager()
	{
		board = new Square[]{
				new GoSquare(),
				new PropertySquare(
						new PropertyContract("Vicolo Corto", 60, 2, 10, 30, 90,
								160, 250, 50,
								PropertyColor.BROWN)),
				new CommunityChestSquare(),
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
				new ChanceSquare(),
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
								PropertyColor.PINK)),
				new CompanySquare(
						new CompanyContract("Società Elettrica", 150)),
				new PropertySquare(
						new PropertyContract("Corsa Ateneo", 140, 10, 50, 150,
								450, 620, 750, 100,
								PropertyColor.PINK)),
				new PropertySquare(
						new PropertyContract("Piazza Università", 160, 12, 60,
								180, 500, 700, 900, 100,
								PropertyColor.PINK)),
				new StationSquare(
						new StationContract("Stazione Ovest", 200, 25)),
				new PropertySquare(
						new PropertyContract("Via Verdi", 180, 14, 70, 200,
								550,
								750, 950, 100,
								PropertyColor.ORANGE)),
				new CommunityChestSquare(),
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
				new ChanceSquare(),
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
						new CompanyContract("Società Acqua Potabile", 150)),
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
				new CommunityChestSquare(),
				new PropertySquare(
						new PropertyContract("Largo Augusto", 320, 28, 150,
								450,
								1000, 1200, 1400, 200,
								PropertyColor.GREEN)),
				new StationSquare(new StationContract("Stazione Est", 200,
						25)),
				new ChanceSquare(),
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
	 * Singleton instance getter
	 *
	 * @return The instance of the GameManager
	 */
	public static GameManager getInstance()
	{
		if (instance == null)
			instance = new GameManager();
		return instance;
	}

	/**
	 * Adds a player to the list of players
	 *
	 * @param player The new player
	 */
	public void addPlayer(Player player)
	{
		players.add(player);
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
	 * Get a square by its index position
	 *
	 * @param index The index position of the square
	 * @return The square at the specified index
	 */
	public Square getSquare(int index)
	{
		return board[index];
	}
}
