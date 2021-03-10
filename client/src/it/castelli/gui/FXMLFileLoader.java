package it.castelli.gui;

import it.castelli.ClientMain;
import it.castelli.gui.scene.SceneManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class FXMLFileLoader
{
	public static Parent loadFXML(String path, Object caller)
	{
		FXMLLoader loader = new FXMLLoader();
        URL location = caller.getClass().getResource(path);
        if (location == null)
            System.out.println("Location is null for: " + path);
        loader.setLocation(location);
		try
		{
			return loader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static FXMLLoader getLoader(String path)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(FXMLFileLoader.class.getResource(path));
		return loader;
	}
}
