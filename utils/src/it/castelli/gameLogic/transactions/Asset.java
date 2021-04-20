package it.castelli.gameLogic.transactions;

import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.gameLogic.contracts.Contract;

import java.util.HashSet;

/**
 * Class containing what players want to exchange
 */
public class Asset
{
	/**
	 * money add to the exchange
	 */
	private int money;
	/**
	 * contracts add to the exchange
	 */
	private HashSet<Contract> contracts = new HashSet<>();

	/**
	 *  Constructor of Asset
	 */
	public Asset()
	{
		this.money = 0;
	}

	/**
	 * Getter for the money
	 *
	 * @return money add to the exchange
	 */
	public int getMoney()
	{
		return money;
	}

	/**
	 * Setter for the money
	 *
	 * @param money money add to the exchange
	 */
	public void setMoney(int money)
	{
		this.money = money;
	}

	/**
	 * Getter for the contracts
	 *
	 * @return contracts add to the exchange
	 */
	public HashSet<Contract> getContracts()
	{
		return contracts;
	}

	/**
	 * Setter for the contracts
	 *
	 * @param contracts HashSet of contracts
	 */
	public void setContracts(HashSet<Contract> contracts)
	{
		this.contracts = contracts;
	}

	/**
	 * Remove money and contracts to the player in the exchange
	 *
	 * @param player Player in the exchange
	 * @param gameManager GameManager of the game
	 */
	public void removeFromPlayer(Player player, GameManager gameManager)
	{
		player.removeMoney(money);
		for (Contract contract : contracts)
		{
			Contract contractToRemove = gameManager.getSameContract(contract);
			player.removeContract(contractToRemove);
		}
	}
	/**
	 * Add money and contracts to the player in the exchange
	 *
	 * @param player Player in the exchange
	 * @param gameManager GameManager of the game
	 */
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
