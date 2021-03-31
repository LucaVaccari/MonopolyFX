package it.castelli;

/**
 * Wrapper of Main, to make the main class not extend Application
 */
public class MainWrapper
{
	/**
	 * Entry point of the program
	 * @param args The command line arguments
	 */
	public static void main(String[] args)
	{
		ClientMain.main(args);
	}
}
