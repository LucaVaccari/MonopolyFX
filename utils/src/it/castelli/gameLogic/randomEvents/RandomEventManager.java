package it.castelli.gameLogic.randomEvents;

import it.castelli.gameLogic.randomEvents.randomEventImplementations.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Class to handle the decks of RandomEvents
 */
public class RandomEventManager
{
	/**
	 * The list of chances
	 */
	private final LinkedList<RandomEvent> chances = new LinkedList<>();
	/**
	 * The list of community chests
	 */
	private final LinkedList<RandomEvent> communityChests = new LinkedList<>();

	public RandomEventManager()
	{
		chances.addAll(Arrays.asList(
				new BirthdayRandomEvent(
						"E' il vostro compleanno, ogni giocatore vi regala 10M", RandomEventType.CHANCE),
				new GoToRandomEvent(
						"Andate in prigione direttamente senza passare dal " +
								"via", RandomEventType.CHANCE,
						30, false),
				new PayHousesRandomEvent(
						"Avete tutti i vostri stabili da riparare: " +
								"pagare 25M per ogni casa e 100 M per ogni albergo", RandomEventType.CHANCE, 25, 100),
				new GoToRandomEvent(
						"Andate alla Stazione NORD; se passate dal 'Via!' " +
								"ritirate 200M", RandomEventType.CHANCE,
						25, true),
				new YouWonRandomEvent(
						"Avete vinto un terno al lotto: ritirate 100M", RandomEventType.CHANCE, 100),
				new GoToRandomEvent("Andate avanti sino al 'Via!'", RandomEventType.CHANCE, 40, true),
				new GoToRandomEvent(
						"Andate sino al Largo Colombo: se passate dal 'Via!'" +
								" " +
								"ritirate 200M", RandomEventType.CHANCE,
						24, true),
				new PayRandomEvent(
						"Matrimonio in famiglia: spese impreviste 150M", RandomEventType.CHANCE, 150),
				new YouWonRandomEvent(
						"Maturano le cedole delle vostre cartelle di rendita, ritirate 150M", RandomEventType.CHANCE,
						150),
				new PayHousesRandomEvent(
						"Dovete pagare un contributo di miglioria stradale, " +
								"40" +
								"M per ogni casa, " +
								"100M per ogni albergo che possedete", RandomEventType.CHANCE, 40, 100),
				new GoBackRandomEvent(
						"Fate 3 passi indietro (con tanti auguri!)", RandomEventType.CHANCE, 3),
				new GoToRandomEvent("Andate fino al Parco della Vittoria", RandomEventType.CHANCE, 39,
						false),
				new GoToRandomEvent(
						"Andate fino alla Via Accademia: se passate dal " +
								"'Via!'" +
								" ritirate 200M", RandomEventType.CHANCE,
						11, true),
				new PrisonEscapeRandomEvent(
						"Uscite gratis di prigione, se ci siete: potete " +
								"conservare questo cartoncino sino al momento" +
								" di servirvene (non si sa mai!) oppure venderlo", RandomEventType.CHANCE),
				new YouWonRandomEvent(
						"La Banca Vi paga gli interessi del vostro Conto " +
								"Corrente: ritirate 50M", RandomEventType.CHANCE,
						50),
				new PayRandomEvent("Versate 20M per benificenza", RandomEventType.CHANCE, 20)
		));

		communityChests.addAll(Arrays.asList(
				new GoToRandomEvent("Ritornate al Vicolo Corto", RandomEventType.COMMUNITY_CHEST, 1, false),
				new GoToRandomEvent(
						"Andate in prigione direttamente senza passare dal " +
								"via", RandomEventType.COMMUNITY_CHEST,
						30, false),
				new YouWonRandomEvent(
						"Avete venduto delle azioni: ricavate 50M", RandomEventType.COMMUNITY_CHEST, 50),
				new YouWonRandomEvent(
						"Siete creditori verso la Banca di 200M: ritiratele", RandomEventType.COMMUNITY_CHEST,
						200),
				new YouWonRandomEvent("Ereditate da un lontano parente 100M", RandomEventType.COMMUNITY_CHEST,
						100),
				new PrisonEscapeRandomEvent(
						"Uscite gratis di prigione, se ci siete: potete " +
								"conservare questo cartoncino sino al momento" +
								" di servirvene (non si sa mai!) oppure venderlo",
						RandomEventType.COMMUNITY_CHEST),
				new GoToRandomEvent("Andate avanti sino al 'Via!'", RandomEventType.COMMUNITY_CHEST, 0, true),
				new YouWonRandomEvent(
						"E' maturata la cedola delle vostre azioni: ritirate " +
								"25" +
								"M", RandomEventType.CHANCE,
						25),
				new PayRandomEvent(
						"Scade il Vostro premio di assicurazione: pagate 50M", RandomEventType.COMMUNITY_CHEST,
						50),
				new YouWonRandomEvent(
						"Avete vinto un premio di consolazione alla Lotteria" +
								" " +
								"di Merano, ritirate 100M", RandomEventType.COMMUNITY_CHEST,
						100),
				new YouWonRandomEvent(
						"Avete vinto il secondo premio in un concorso di " +
								"bellezza: ritirate 10M", RandomEventType.COMMUNITY_CHEST,
						10),
				new YouWonRandomEvent(
						"Rimborso tassa sul reddito: ritirate 20M dalla " +
								"Banca", RandomEventType.COMMUNITY_CHEST,
						20),
				new PayRandomEvent("Avete perso una causa: pagate 100M", RandomEventType.COMMUNITY_CHEST, 100)
		));
	}

	/**
	 * Draw a chance card (removes it from the list and returns it)
	 *
	 * @return A chance card
	 */
	public RandomEvent drawChance()
	{
		return chances.poll();
	}

	/**
	 * Draw a community chest card (removes it from the list and returns it)
	 *
	 * @return A community chest
	 */
	public RandomEvent drawCommunityChest()
	{
		return communityChests.poll();
	}

	/**
	 * Puts back a chance card in the list
	 *
	 * @param chance The chance card to be put back
	 */
	public void addChance(RandomEvent chance)
	{
		chances.add(chance);
	}

	/**
	 * Puts back a community chest card in the list
	 *
	 * @param communityChest The community chest card to be put back
	 */
	public void addCommunityChest(RandomEvent communityChest)
	{
		communityChests.add(communityChest);
	}

	public void shuffleCards()
	{
		Collections.shuffle(chances);
		Collections.shuffle(communityChests);
	}

}
