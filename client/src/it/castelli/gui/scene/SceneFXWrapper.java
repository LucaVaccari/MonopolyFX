package it.castelli.gui.scene;

import javafx.scene.Scene;

public class SceneFXWrapper
{
	private Scene scene;
	private boolean showInPrimaryStage;
	private String windowTitle;

	public SceneFXWrapper(Scene scene, boolean showInPrimaryStage, String name)
	{
		this.scene = scene;
		this.showInPrimaryStage = showInPrimaryStage;
		this.windowTitle = name;
	}

	public Scene getScene()
	{
		return scene;
	}

	public void setScene(Scene scene)
	{
		this.scene = scene;
	}

	public boolean isShowInPrimaryStage()
	{
		return showInPrimaryStage;
	}

	public void setShowInPrimaryStage(boolean showInPrimaryStage)
	{
		this.showInPrimaryStage = showInPrimaryStage;
	}

	public String getWindowTitle()
	{
		return windowTitle;
	}

	public void setWindowTitle(String windowTitle)
	{
		this.windowTitle = windowTitle;
	}
}
