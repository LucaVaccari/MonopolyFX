package it.castelli;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;

public class Game
{
    public static GameManager gameManager = new GameManager();
    public static Player player;

    private Game(){}

    public static GameManager getGameManager()
    {
        return gameManager;
    }

    public static Player getPlayer()
    {
        return player;
    }

    public static void setPlayer(Player player)
    {
        Game.player = player;
    }
}
