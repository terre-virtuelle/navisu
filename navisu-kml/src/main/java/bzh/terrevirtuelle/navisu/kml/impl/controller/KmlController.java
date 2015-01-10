/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.kml.impl.controller;

import bzh.terrevirtuelle.navisu.kml.impl.controller.wwj.KMLApplicationController;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.kml.KMLAbstractFeature;
import gov.nasa.worldwind.ogc.kml.KMLRoot;
import gov.nasa.worldwind.ogc.kml.impl.KMLController;
import gov.nasa.worldwind.util.WWIO;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwindx.examples.util.BalloonController;
import gov.nasa.worldwindx.examples.util.HotSpotController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class KmlController {

    protected HotSpotController hotSpotController;
    protected KMLApplicationController kmlAppController;
    protected BalloonController balloonController;
    protected WorldWindow wwd;
    private static final KmlController INSTANCE;
    protected String path;

    private final List<Layer> layers;

    static {
        INSTANCE = new KmlController();
    }

    private KmlController() {
        layers = new ArrayList<>();
        wwd = GeoWorldWindViewImpl.getWW();
        // Add a controller to handle input events on the layer selector and on browser balloons.
        this.hotSpotController = new HotSpotController(wwd);

        // Add a controller to handle common KML application events.
        this.kmlAppController = new KMLApplicationController(wwd);

        // Add a controller to display balloons when placemarks are clicked. We override the method addDocumentLayer
        // so that loading a KML document by clicking a KML balloon link displays an entry in the on-screen layer
        // tree.
        this.balloonController = new BalloonController(wwd) {
            @Override
            protected void addDocumentLayer(KMLRoot document) {
                //addKMLLayer(document);
            }
        };
        // Give the KML app controller a reference to the BalloonController so that the app controller can open
        // KML feature balloons when feature's are selected in the on-screen layer tree.
        this.kmlAppController.setBalloonController(balloonController);
    }

    public static KmlController getInstance() {
        return INSTANCE;
    }

    public final List<Layer> init(String path) {
        this.path = path;
        try {
            KMLRoot document = KMLRoot.createAndParse(path);
            KMLController kmlController = new KMLController(document);
            RenderableLayer layer = new RenderableLayer();
            // Set the document's display name
             document.setField(AVKey.DISPLAY_NAME,formName(this.path, document));
            layer.setName(path);
            layer.addRenderable(kmlController);
            layers.add(layer);
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(KmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return layers;
    }

    protected static String formName(Object kmlSource, KMLRoot kmlRoot)
    {
        KMLAbstractFeature rootFeature = kmlRoot.getFeature();

        if (rootFeature != null && !WWUtil.isEmpty(rootFeature.getName()))
            return rootFeature.getName();

        if (kmlSource instanceof File)
            return ((File) kmlSource).getName();

        if (kmlSource instanceof URL)
            return ((URL) kmlSource).getPath();

        if (kmlSource instanceof String && WWIO.makeURL((String) kmlSource) != null)
            return WWIO.makeURL((String) kmlSource).getPath();

        return "KML Layer";
    }
}
