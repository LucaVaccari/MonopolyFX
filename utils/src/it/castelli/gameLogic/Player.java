package it.castelli.gameLogic;

import it.castelli.gameLogic.contracts.Contract;
import it.castelli.gameLogic.randomEvents.RandomEvent;

import java.util.ArrayList;
import java.util.HashSet;

public class Player
{
    private HashSet<Contract> contracts = new HashSet<>();
    private int money;
    private ArrayList<RandomEvent> keptRandomEventCards = new ArrayList<>();
    private int position = 0;
    private String name;

    public Player(int money, String name)
    {
        this.money = money;
        this.name = name;
    }

    public int getPosition()
    {
        return position;
    }

    public String getName()
    {
        return name;
    }

    public HashSet<Contract> getContracts()
    {
        return contracts;
    }

    public int getMoney()
    {
        return money;
    }

    public ArrayList<RandomEvent> getKeptRandomEventCards()
    {
        return keptRandomEventCards;
    }

    public void setPosition(int position, boolean passThroughGoSquare)
    {
        this.position = position;
        if ( position >= 40 )
            ;// TODO: give money to player
        this.position %= 40;
        // TODO: GameManager.getSquare(position).interact();
    }

    public void move(int units)
    {
        setPosition( position + units, true );
    }

    // TODO: change x name
    public void addMoney(int x)
    {
        money += x;
    }

    // TODO: change x name
    public void removeMoney(int x)
    {
        if ( money >= x )
            money -= x;
    }

    // TODO: change x name
    public boolean hasMoney(int x)
    {
        return money >= x;
    }
}
