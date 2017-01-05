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
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Vec4;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.StackPane;

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
    protected String PROPERTIES_FILE_NAME = "properties/navigation.properties";
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
            properties.load(new FileInputStream(PROPERTIES_FILE_NAME));
        } catch (IOException ex) {
            Logger.getLogger(ColladaComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colladaRoot = kmlComponentServices.openColladaFile(mobilesLayer, filename);
        colladaRoot.setModelScale(new Vec4(new Double(properties.getProperty("scale").trim())));
        colladaRoot.setPosition(Position.fromDegrees(new Double(properties.getProperty("latitude").trim()),
                new Double(properties.getProperty("longitude").trim()), 1000.0));
        colladaRoot.setHeading(Angle.fromDegrees(new Double(properties.getProperty("heading").trim())));
        locatorController = new LocatorController(colladaRoot);

        Platform.runLater(() -> {
            root.getChildren().add(locatorController);
        });
    }

    public ColladaRoot getColladaRoot() {
        return colladaRoot;
    }

}
