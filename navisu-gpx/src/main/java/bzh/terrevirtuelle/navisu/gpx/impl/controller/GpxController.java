/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.gpx.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.widgets.textArea.TextAreaController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.formats.gpx.GpxReader;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.MarkerLayer;
import gov.nasa.worldwind.pick.PickedObject;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.markers.BasicMarker;
import gov.nasa.worldwind.render.markers.BasicMarkerAttributes;
import gov.nasa.worldwind.render.markers.BasicMarkerShape;
import gov.nasa.worldwind.render.markers.Marker;
import gov.nasa.worldwind.util.WWIO;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.application.Platform;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Serge Morvan
 * @date 17 nov. 2014 NaVisu project
 */
public class GpxController {

    private static GpxController INSTANCE;
    protected String path;
    protected WorldWindow wwd;
    private final List<Layer> layers;
    private TextAreaController textAreaController;

    private GpxController(GuiAgentServices guiAgentServices) {
        layers = new ArrayList<>();
        wwd = GeoWorldWindViewImpl.getWW();
        Platform.runLater(() -> {
            textAreaController = new TextAreaController();
            textAreaController.getDataTextArea().setWrapText(true);
            textAreaController.getTitleText().setText("Track positions \n");
            textAreaController.setVisible(false);
            guiAgentServices.getRoot().getChildren().add(textAreaController);
        });
        addListeners();
    }

    private void addListeners() {
        wwd.addSelectListener((SelectEvent event) -> {
            PickedObject po = event.getTopPickedObject();
            if (po != null && po.getObject() instanceof BasicMarker) {
                if (event.getEventAction().equals(SelectEvent.LEFT_CLICK)) {
                    Platform.runLater(() -> {
                        Position position = po.getPosition();
                        double lat = position.getLatitude().getDegrees();
                        double lon = position.getLongitude().getDegrees();
                        String ns = "N";
                        if (lat < 0) {
                            ns = "S";
                        }
                        String ew = "E";
                        if (lon < 0) {
                            ew = "W";
                        }
                        textAreaController.getDataTextArea()
                                .appendText("Latitude : " + String.format("%.4f", Math.abs(lat)) + "° " + ns
                                        + "     Longitude : " + String.format("%.4f", Math.abs(lon)) + "° " + ew + "\n");
                        textAreaController.setVisible(true);
                    });
                    event.consume();
                }
            }
        });
    }

    public static GpxController getInstance(GuiAgentServices guiAgentServices) {
        if (INSTANCE == null) {
            INSTANCE = new GpxController(guiAgentServices);
        }
        return INSTANCE;
    }

    public final List<Layer> init(String path) {
        this.path = path;
        try {
            GpxReader reader = new GpxReader();
            reader.readStream(WWIO.openFileOrResourceStream(path, this.getClass()));
            Iterator<Position> positions = reader.getTrackPositionIterator();
            //  List<Track> tracks = reader.getTracks();//TODO

            BasicMarkerAttributes attrs
                    = new BasicMarkerAttributes(Material.RED, BasicMarkerShape.SPHERE, 50.d);
            // max value for MarkerSize about 50
            attrs.setMaxMarkerSize(1000.0);
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
