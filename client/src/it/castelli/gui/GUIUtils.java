package it.castelli.gui;

import it.castelli.gameLogic.Pawn;
import it.castelli.gameLogic.contracts.PropertyColor;

import java.util.HashMap;

/**
 * Static class for storing GUI utils
 */
public class GUIUtils
{
	/**
	 * Map containing all PropertyColors bound to their hex color codes <#nnnnnn>
	 */
	private static final HashMap<PropertyColor, String> propertyColorsCodes = new HashMap<>();
	private static final HashMap<Pawn, String> pawnPaths = new HashMap<>();
	private static final HashMap<Pawn, String> pawnColor = new HashMap<>();

	static
	{
		propertyColorsCodes.put(PropertyColor.BROWN, "#965336");
		propertyColorsCodes.put(PropertyColor.LIGHT_BLUE, "#ace2fc");
		propertyColorsCodes.put(PropertyColor.MAGENTA, "#dd3997");
		propertyColorsCodes.put(PropertyColor.ORANGE, "#f9951c");
		propertyColorsCodes.put(PropertyColor.RED, "#ee1b23");
		propertyColorsCodes.put(PropertyColor.YELLOW, "#ffef06");
		propertyColorsCodes.put(PropertyColor.GREEN, "#1fb25a");
		propertyColorsCodes.put(PropertyColor.BLUE, "#0072bd");

		pawnPaths.put(Pawn.BOAT, "/images/pawns/boat.png");
		pawnPaths.put(Pawn.DOG, "/images/pawns/dog.png");
		pawnPaths.put(Pawn.SHOE, "/images/pawns/shoe.png");
		pawnPaths.put(Pawn.CAR, "/images/pawns/car.png");
		pawnPaths.put(Pawn.WAGON, "/images/pawns/wagon.png");
		pawnPaths.put(Pawn.THIMBLE, "/images/pawns/thimble.png");

		pawnColor.put(Pawn.BOAT, "#f9951c");
		pawnColor.put(Pawn.DOG, "#ee1b23");
		pawnColor.put(Pawn.SHOE, "#1fb25a");
		pawnColor.put(Pawn.CAR, "#0072bd");
		pawnColor.put(Pawn.WAGON, "#dd3997");
		pawnColor.put(Pawn.THIMBLE, "#ffef06");
	}

	/**
	 * Private constructor to prevent instantiating the class
	 */
	private GUIUtils()
	{
	}

	/**
	 * Getter for the pawnPaths HashMap
	 */
	public static HashMap<Pawn, String> getPawnPaths()
	{
		return pawnPaths;
	}

	/**
	 * Getter for the pawnColor HashMap
	 */
	public static HashMap<Pawn, String> getPawnColor()
	{
		return pawnColor;
	}

	/**
	 * Getter for the propertyColorsCodes HashMap
	 *
	 * @return Map containing all PropertyColors bound to their hex color codes #nnnnnn
	 */
	public static HashMap<PropertyColor, String> getPropertyColorsCodes()
	{
		return propertyColorsCodes;
	}
}
