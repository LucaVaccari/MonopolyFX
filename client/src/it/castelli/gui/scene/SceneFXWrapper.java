package it.castelli.gui.scene;

import javafx.scene.Scene;

/**
 * Wrapper for a JavaFX scene, containing the scene, the window title and
 * whether it should open
 * a new stage or not
 */
public class SceneFXWrapper
{
	/**
	 * The JavaFX scene
	 */
	private Scene scene;
	/**
	 * Should the scene be opened in a new stage?
	 */
	private boolean showInPrimaryStage;
	/**
	 * The title of the window
	 */
	private String windowTitle;

	/**
	 * Constructor for SceneFXWrapper
	 *
	 * @param scene              The JavaFX scene
	 * @param showInPrimaryStage Should the scene be opened in a new stage?
	 * @param windowTitle        The title of the window
	 */
	public SceneFXWrapper(Scene scene, boolean showInPrimaryStage,
	                      String windowTitle)
	{
		this.scene = scene;
		this.showInPrimaryStage = showInPrimaryStage;
		this.windowTitle = windowTitle;
	}

	/**
	 * Getter for the scene
	 *
	 * @return The JavaFX scene
	 */
	public Scene getScene()
	{
		return scene;
	}

	/**
	 * Setter for the scene
	 *
	 * @param scene The new JavaFX scene
	 */
	public void setScene(Scene scene)
	{
		this.scene = scene;
	}

	/**
	 * Getter for showInPrimaryStage
	 *
	 * @return Should the scene be opened in a new stage?
	 */
	public boolean isShownInPrimaryStage()
	{
		return showInPrimaryStage;
	}

	/**
	 * Setter for showInPrimaryStage
	 *
	 * @param showInPrimaryStage Should the scene be opened in a new stage?
	 */
	public void setShowInPrimaryStage(boolean showInPrimaryStage)
	{
		this.showInPrimaryStage = showInPrimaryStage;
	}

	/**
	 * Getter for the window title
	 *
	 * @return The window title
	 */
	public String getWindowTitle()
	{
		return windowTitle;
	}

	/**
	 * Setter for the window title
	 *
	 * @param windowTitle The new window title
	 */
	public void setWindowTitle(String windowTitle)
	{
		this.windowTitle = windowTitle;
	}
}
