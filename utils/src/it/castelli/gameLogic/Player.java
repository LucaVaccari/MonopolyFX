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

	public Player(int money)
	{
		this.money = money;
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

	// TODO: change x name
	public void addMoney(int x)
	{
		money += x;
	}

	// TODO: change x name
	public void removeMoney(int x)
	{
		if (money >= x)
			money -= x;
	}

	// TODO: change x name
	public boolean hasMoney(int x)
	{
		return money >= x;
	}
}
