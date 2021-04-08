package it.castelli.gameLogic.transactions;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

import java.util.ArrayList;

public class Asset
{
	private int money;
	private ArrayList<Contract> contracts;

	public Asset()
	{
		this.money = 0;
		this.contracts = new ArrayList<>();
	}

	public int getMoney()
	{
		return money;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public ArrayList<Contract> getContracts()
	{
		return contracts;
	}

	public void setContracts(ArrayList<Contract> contracts)
	{
		this.contracts = contracts;
	}

	public void removeFromPlayer(Player player)
	{
		player.removeMoney(money);
		for (Contract contract : contracts)
		{
			player.removeContract(contract);
		}
	}

	public void addToPlayer(Player player)
	{
		player.addMoney(money);
		for (Contract contract : contracts)
		{
			player.addContract(contract);
		}
	}
}
