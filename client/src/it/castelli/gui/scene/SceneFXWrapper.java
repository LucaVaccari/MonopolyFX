package it.castelli.gui.scene;

import javafx.scene.Scene;

public class SceneFXWrapper
{
	private Scene scene;
	private boolean showInPrimaryStage;
	private String name;

	public SceneFXWrapper(Scene scene, boolean showInPrimaryStage, String name)
	{
		this.scene = scene;
		this.showInPrimaryStage = showInPrimaryStage;
		this.name = name;
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
