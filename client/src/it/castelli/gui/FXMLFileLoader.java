package it.castelli.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class FXMLFileLoader
{
    public static Parent loadFXML(String path)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLFileLoader.class.getResource( path ));
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
        loader.setLocation(FXMLFileLoader.class.getResource( path ));
        return loader;
    }
}
