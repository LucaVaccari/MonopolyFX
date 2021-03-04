package it.castelli.gameLogic;

import it.castelli.gameLogic.squares.Square;
import it.castelli.gameLogic.transactions.Auction;
import it.castelli.gameLogic.transactions.Exchange;

import java.util.ArrayList;

public class GameManager
{
    private Square[] board;
    private ArrayList<Player> players;
    private Auction auction;
    private Exchange exchange;

    private static GameManager instance;

    public static GameManager getInstance()
    {
        if (instance == null)
            instance = new GameManager();
        return instance;
    }

    private GameManager(){}

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    public void removePlayer(Player player)
    {
        players.remove(player);
    }

    public Square getSquare(int index)
    {
        return board[index];
    }
}
