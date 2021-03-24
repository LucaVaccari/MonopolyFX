package it.castelli;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;

public class Game
{
    private static GameManager gameManager = new GameManager();
    private static Player player;

    private static int gameCode;

    private Game(){}

    public static GameManager getGameManager()
    {
        return gameManager;
    }

    public static Player getPlayer()
    {
        return player;
    }

    public static int getGameCode()
    {
        return gameCode;
    }

    public static void setPlayer(Player player)
    {
        Game.player = player;
    }

    public static void setGameCode(int gameCode)
    {
        Game.gameCode = gameCode;
    }
}
