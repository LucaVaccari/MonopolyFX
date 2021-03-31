package it.castelli.gui;

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
	}

	/**
	 * Private constructor to prevent instantiating the class
	 */
	private GUIUtils() {}

	/**
	 * Getter for the propertyColorsCodes HashMap
	 *
	 * @return Map containing all PropertyColors bound to their hex color codes <#nnnnnn>
	 */
	public static HashMap<PropertyColor, String> getPropertyColorsCodes()
	{
		return propertyColorsCodes;
	}
}
