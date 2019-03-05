/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.collada.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;
import bzh.terrevirtuelle.navisu.kml.KmlComponentServices;
import bzh.terrevirtuelle.navisu.widgets.locator.LocatorController;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Vec4;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 *
 * @author serge
 * @date Dec 20, 2016
 */
public class ColladaComponentController {

    protected WorldWindow wwd;
    private static ColladaComponentController INSTANCE = null;
    private final LayersManagerServices layersManagerServices;
    private final KmlComponentServices kmlComponentServices;
    protected final String GROUP = "Navigation";
    private final String NAME_0 = "Mobiles";
    protected Properties properties;
    protected String CONFIG_FILE_NAME = System.getProperty("user.home") + "/.navisu/config/config.properties";

    // protected String PROPERTIES_FILE_NAME = "properties/navigation.properties";
    protected RenderableLayer mobilesLayer;
    protected String filename;
    protected StackPane root;
    LocatorController locatorController;
    ColladaRoot colladaRoot;

    /*
    public static ColladaComponentController getInstance(LayersManagerServices layersManagerServices,
            KmlComponentServices kmlComponentServices, String filename, StackPane root) {
        if (INSTANCE == null) {
            INSTANCE = new ColladaComponentController(layersManagerServices,
                    kmlComponentServices, filename, root);
        }
        return INSTANCE;
    }
     */
    public ColladaComponentController(LayersManagerServices layersManagerServices,
            KmlComponentServices kmlComponentServices,
            String filename, StackPane root) {
        this.layersManagerServices = layersManagerServices;
        this.kmlComponentServices = kmlComponentServices;
        this.filename = filename;
        this.mobilesLayer = layersManagerServices.getLayer(GROUP, NAME_0);
        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
            Logger.getLogger(ColladaComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        colladaRoot = kmlComponentServices.openColladaFile(mobilesLayer, filename);
        

        //Prevoir une Alert si pas d'init
        String latStr = properties.getProperty("latitude");
        String lonStr = properties.getProperty("longitude");
        if (latStr == null || lonStr == null) {
            alert("Warning", "Bad configuration", "Latitude and longitude must be initialised");
        } else {
            colladaRoot.setPosition(Position.fromDegrees(new Double(latStr.trim()), new Double(lonStr.trim()), 1000.0));
            String headerStr = properties.getProperty("heading");
            if (headerStr != null) {
                colladaRoot.setHeading(Angle.fromDegrees(new Double(headerStr.trim())));
            }
            locatorController = new LocatorController(colladaRoot);
            Platform.runLater(() -> {
                root.getChildren().add(locatorController);
            });
        }
    }

    public ColladaRoot getColladaRoot() {
        return colladaRoot;
    }

    private void alert(String title, String header, String text) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(header);
            Text s = new Text(text);
            s.setWrappingWidth(650);
            alert.getDialogPane().setContent(s);
            alert.show();
        });

    }
}
