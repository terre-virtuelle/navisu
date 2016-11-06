/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.common.view;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.netcdf.meteo.impl.view.MeteoNetCDFViewer;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.ScreenImage;
import gov.nasa.worldwind.render.Size;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;

/**
 *
 * @author serge
 */
public class WwjButtonController {

    private final GuiAgentServices guiAgentServices;
    private final RenderableLayer layer;
    private final WorldWindow wwd;
    private ScreenImage screenImage;
    private final Scene scene;
    private final String side;
    private final int xOffSet;
    private final int yOffSet;
    private final String filenameOn;
    private final String filenameOff;
    private BufferedImage imageOn;
    private BufferedImage imageOff;
    private boolean first = true;

    public WwjButtonController(GuiAgentServices guiAgentServices, RenderableLayer layer,
            String side, String filenameOn, String filenameOff, 
            int xOffSet, int yOffSet) {
        this.layer = layer;
        this.guiAgentServices = guiAgentServices;
        this.side = side;
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;
        this.filenameOn = filenameOn;
        this.filenameOff = filenameOff;
        wwd = GeoWorldWindViewImpl.getWW();
        this.layer.setPickEnabled(true);
        scene = guiAgentServices.getScene();
        init();
        actionEvents();
        positionEvents();
    }

    private void init() {
        screenImage = new ScreenImage();
        screenImage.setValue("Side", side);
        try {
            //U B non traite encore
            imageOn = ImageIO.read(new File(filenameOn));
            imageOff = ImageIO.read(new File(filenameOff));
        } catch (IOException ex) {
            Logger.getLogger(WwjButtonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        screenImage.setImageSource(imageOn);
        screenImage.setSize(new Size(Size.EXPLICIT_DIMENSION,
                40, AVKey.PIXELS, Size.EXPLICIT_DIMENSION, 40, AVKey.PIXELS));
        switch (side) {
            case "R":
                screenImage.setScreenLocation(new Point((int) (scene.getWidth() - xOffSet),
                        (int) (scene.getHeight() - yOffSet)));
                break;
            case "L":
                screenImage.setScreenLocation(new Point((int) (xOffSet),
                        (int) (scene.getHeight() - yOffSet)));
                break;
            case "U":
                screenImage.setScreenLocation(new Point((int) (scene.getWidth() - xOffSet),
                        (int) (scene.getHeight() - yOffSet)));
                break;
            case "B":
                screenImage.setScreenLocation(new Point((int) (xOffSet),
                        (int) (scene.getHeight() - yOffSet)));
                break;
        }
        layer.addRenderable(screenImage);
    }

    private void positionEvents() {
        StackPane centerStackPane = guiAgentServices.getCenterStackPane();
        //U B non traite encore
        centerStackPane.widthProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) -> {
            switch (side) {
                case "R":
                    screenImage.setScreenLocation(new Point((int) (scene.getWidth() - xOffSet - guiAgentServices.getLeftBorderPane().getWidth()),
                            (int) (scene.getHeight() - yOffSet)));
                    break;
                case "L":
                    screenImage.setScreenLocation(new Point((int) xOffSet,
                            (int) (scene.getHeight() - yOffSet)));
                    break;
                case "U":
                    screenImage.setScreenLocation(new Point((int) (scene.getWidth() - xOffSet - guiAgentServices.getLeftBorderPane().getWidth()),
                            (int) (scene.getHeight() - yOffSet)));
                    break;
                case "B":
                    screenImage.setScreenLocation(new Point((int) (xOffSet - guiAgentServices.getLeftBorderPane().getWidth()),
                            (int) (scene.getHeight() - yOffSet)));
            }
        });
        centerStackPane.heightProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) -> {
            //  screenImage.setScreenLocation(new Point((int) (scene.getWidth() - xOffSet - guiAgentServices.getLeftBorderPane().getWidth()),
            //          (int) (scene.getHeight() - yOffSet)));

            switch (side) {
                case "R":
                    screenImage.setScreenLocation(new Point((int) (scene.getWidth() - xOffSet - guiAgentServices.getLeftBorderPane().getWidth()),
                            (int) (scene.getHeight() - yOffSet)));
                    break;
                case "L":
                    screenImage.setScreenLocation(new Point((int) xOffSet,
                            (int) (scene.getHeight() - yOffSet)));
                    break;
                case "U":
                    screenImage.setScreenLocation(new Point((int) (scene.getWidth() - xOffSet - guiAgentServices.getLeftBorderPane().getWidth()),
                            (int) (scene.getHeight() - yOffSet)));
                    break;
                case "B":
                    screenImage.setScreenLocation(new Point((int) (xOffSet - guiAgentServices.getLeftBorderPane().getWidth()),
                            (int) (scene.getHeight() - yOffSet)));
            }
        });

    }

    private void actionEvents() {
        wwd.addSelectListener((SelectEvent event) -> {
            if (event.getEventAction().equals(SelectEvent.LEFT_CLICK)) {
                Object o = event.getTopObject();
                if (o.getClass().getName().equalsIgnoreCase("gov.nasa.worldwind.render.ScreenImage")) {
                    ScreenImage obj = (gov.nasa.worldwind.render.ScreenImage) o;
                    if (obj.getValue("Side").equals(side)) {
                        if (first == true) {
                            screenImage.setImageSource(imageOff);
                            first = false;
                        } else {
                            screenImage.setImageSource(imageOn);
                            first = true;
                        }
                        // ((gov.nasa.worldwind.render.ScreenImage) o).setScreenLocation(new Point(event.getMouseEvent().getX(),
                        //         event.getMouseEvent().getY()));
                        System.out.println("***************");
                        event.consume();
                    }
                }
            }
        });
    }
    /*
    public String print(Function<MeteoNetCDFViewer, String> f){
        return f.apply(t);
    }
*/
}
