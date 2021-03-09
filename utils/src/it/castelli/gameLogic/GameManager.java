package it.castelli.gameLogic;

import it.castelli.gameLogic.squares.GoSquare;
import it.castelli.gameLogic.squares.Square;
import it.castelli.gameLogic.transactions.Auction;
import it.castelli.gameLogic.transactions.Exchange;

import java.util.ArrayList;
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
				new GoSquare()
		};
	}

	/**
	 * Singleton instance getter
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
	 * @param player The new player
	 */
	public void addPlayer(Player player)
	{
		players.add(player);
	}

	/**
	 * Removes a player from the list of players
	 * @param player The player to remove
	 */
	public void removePlayer(Player player)
	{
		players.remove(player);
	}

	/**
	 * Get a square by its index position
	 * @param index The index position of the square
	 * @return The square at the specified index
	 */
	public Square getSquare(int index)
	{
		return board[index];
	}
}
