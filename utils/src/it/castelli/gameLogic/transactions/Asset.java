package it.castelli.gameLogic.transactions;

import it.castelli.gameLogic.GameManager;
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

	public void removeFromPlayer(Player player, GameManager gameManager)
	{
		player.removeMoney(money);
		for (Contract contract : contracts)
		{
			Contract contractToRemove = gameManager.getSameContract(contract);
			player.removeContract(contractToRemove);
		}
	}

	public void addToPlayer(Player player, GameManager gameManager)
	{
		player.addMoney(money);
		for (Contract contract : contracts)
		{
			Contract contractToAdd = gameManager.getSameContract(contract);
			player.addContract(contractToAdd);
		}
	}
}
