package it.castelli.connection;

import java.util.ArrayList;
import java.util.HashMap;

public class ConnectionManager
{
    private ArrayList<Connection> waitingRoom;
    private HashMap<Integer, GameConnectionManager> games;

    private void newConnection(){}

    private void moveToGame(int code, Connection connection){}

    private void moveToWaitingRoom(Connection connection){}

    public void addGame(GameConnectionManager game){}

    public void removeGame(int code){}

    public int createGame(){return 0;}

    public void joinGame(int code, Connection connection){}

    public void leaveGame(int code, Connection connection){}
}
