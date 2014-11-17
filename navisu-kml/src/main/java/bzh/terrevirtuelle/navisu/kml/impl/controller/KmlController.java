/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.kml.impl.controller;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.formats.gpx.GpxReader;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.MarkerLayer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.kml.KMLRoot;
import gov.nasa.worldwind.ogc.kml.impl.KMLController;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.markers.BasicMarker;
import gov.nasa.worldwind.render.markers.BasicMarkerAttributes;
import gov.nasa.worldwind.render.markers.BasicMarkerShape;
import gov.nasa.worldwind.render.markers.Marker;
import gov.nasa.worldwind.util.WWIO;
import gov.nasa.worldwindx.examples.kml.KMLApplicationController;
import gov.nasa.worldwindx.examples.util.BalloonController;
import gov.nasa.worldwindx.examples.util.HotSpotController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

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
            layer.setName(path);
            layer.addRenderable(kmlController);
            layers.add(layer);
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(KmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return layers;
    }

    
}
