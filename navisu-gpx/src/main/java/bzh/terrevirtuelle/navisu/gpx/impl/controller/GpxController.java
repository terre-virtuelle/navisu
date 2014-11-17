/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.gpx.impl.controller;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.formats.gpx.GpxReader;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.MarkerLayer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.kml.KMLRoot;
import gov.nasa.worldwind.ogc.kml.impl.KMLController;
import gov.nasa.worldwind.pick.PickedObject;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.markers.BasicMarker;
import gov.nasa.worldwind.render.markers.BasicMarkerAttributes;
import gov.nasa.worldwind.render.markers.BasicMarkerShape;
import gov.nasa.worldwind.render.markers.Marker;
import gov.nasa.worldwind.util.WWIO;
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
 *
 * @author Serge Morvan
 * @date 17 nov. 2014 NaVisu project
 */
public class GpxController {

    private static final GpxController INSTANCE;
    protected String path;
    protected WorldWindow wwd;
    private final List<Layer> layers;

    static {
        INSTANCE = new GpxController();
    }

    private GpxController() {
        layers = new ArrayList<>();
        wwd = GeoWorldWindViewImpl.getWW();
        wwd.addSelectListener((SelectEvent event) -> {
            if (event.getTopObject() != null) {
                if (event.getTopPickedObject().getParentLayer() instanceof MarkerLayer) {
                    PickedObject po = event.getTopPickedObject();
                    System.out.printf("Track position %s, %s, size = %f\n",
                            po.getValue(AVKey.PICKED_OBJECT_ID).toString(),
                            po.getPosition(), (Double) po.getValue(AVKey.PICKED_OBJECT_SIZE));
                }
            }
        });
    }

    public static GpxController getInstance() {
        return INSTANCE;
    }

    public final List<Layer> init(String path) {
        this.path = path;
        try {
            GpxReader reader = new GpxReader();
            reader.readStream(WWIO.openFileOrResourceStream(path, this.getClass()));
            Iterator<Position> positions = reader.getTrackPositionIterator();

            BasicMarkerAttributes attrs
                    = new BasicMarkerAttributes(Material.RED, BasicMarkerShape.SPHERE, 1d);

            ArrayList<Marker> markers = new ArrayList<>();
            while (positions.hasNext()) {
                markers.add(new BasicMarker(positions.next(), attrs));
            }

            MarkerLayer layer = new MarkerLayer(markers);
            layer.setOverrideMarkerElevation(true);
            layer.setElevation(0);
            layer.setEnablePickSizeReturn(true);
            layers.add(layer);
            return layers;
        } catch (ParserConfigurationException | SAXException | IOException e) {
        }
        return layers;
    }
}
