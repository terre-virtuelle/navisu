 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.instrument.view.gps;

import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import org.navisu.instrument.model.Display;

/**
 *
 * @author Serge Morvan
 */
public class LocationPane
        extends GPSPane {

    public LocationPane(Display display) {
        super(display);
        setId("locationPane");
        backgroundFileName = "gpsPosSatBackground.png";
        foregroundFileName = "verre.png";
        backgroundNightFileName = "night_gpsPosSatBackground.png";

        LAYOUT_X = 28;
        LAYOUT_Y = 28;
        createScene();
    }

    private void createScene() {
        backgroundImage = new Image(rootDir + IMAGES + backgroundFileName);
        background = ImageViewBuilder.create()
                .id("posSat")
                .layoutX(LAYOUT_X - 2)
                .layoutY(LAYOUT_Y)
                .pickOnBounds(true)
                .image(backgroundImage)
                .build();
        getChildren().add(background);
        foregroundImage = new Image(rootDir + IMAGES + foregroundFileName);
        foreground = ImageViewBuilder.create()
                .id("verreSat")
                .layoutX(LAYOUT_X)
                .layoutY(LAYOUT_Y)
                .pickOnBounds(true)
                .image(foregroundImage)
                .build();
        getChildren().add(foreground);
    }

    public void initMenu() {
       
    }
}
