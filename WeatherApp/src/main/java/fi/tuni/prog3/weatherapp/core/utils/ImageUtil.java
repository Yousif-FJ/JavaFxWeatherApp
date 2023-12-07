package fi.tuni.prog3.weatherapp.core.utils;

import javafx.scene.image.Image;

public class ImageUtil {
    public static Image createImage(Object callerClass, String iconName){

        String iconPath = "/weathericons/"+ iconName +"@2x.png";
        String something = callerClass.getClass().getResource(iconPath).toExternalForm();

        var iconImage = new Image(something);

        return iconImage;
    }
}
