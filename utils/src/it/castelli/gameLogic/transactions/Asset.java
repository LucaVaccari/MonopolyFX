package it.castelli.gameLogic.transactions;

import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

import java.util.HashSet;

public class Asset
{
	private int money;
	private HashSet<Contract> contracts = new HashSet<>();

	public Asset()
	{
		this.money = 0;
	}

	public int getMoney()
	{
		return money;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public HashSet<Contract> getContracts()
	{
		return contracts;
	}

	public void setContracts(HashSet<Contract> contracts)
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
