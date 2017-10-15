/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.sounds.impl.controller;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.WorldMapLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwindx.examples.ClickAndGoSelectListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Oct 15, 2017
 */
public class BathySoundsComponentController {

    protected WorldWindow wwd;
    protected String fileName = "";
    private final String LAYER_NAME = "Highway";
    private final String GROUP_NAME = "Navigation";
    protected LayersManagerServices layersManagerServices;
    protected InstrumentDriverManagerServices instrumentDriverManagerServices;
    RenderableLayer layer;

    public BathySoundsComponentController(LayersManagerServices layersManagerServices, 
            InstrumentDriverManagerServices instrumentDriverManagerServices,
            String fileName) {
        this.layersManagerServices = layersManagerServices;
        this.instrumentDriverManagerServices=instrumentDriverManagerServices;
        this.fileName = fileName;
        layer = layersManagerServices.getLayer(GROUP_NAME, LAYER_NAME);
        wwd = GeoWorldWindViewImpl.getWW();
        wwd.addSelectListener(new ClickAndSoundSelectListener(wwd, PointPlacemark.class));
    }

    public void on() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            lines.stream().filter((l) -> (!l.equals(""))).map((l) -> l.split(",")).map((values) -> {
                double lat = Double.valueOf(values[0]);
                double lon = Double.valueOf(values[1]);
                String image = values[2];
                String text = values[3];
                String sound = values[4];
                PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(lat, lon, 0));
                placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
                placemark.setLabelText(text);
                return placemark;
            }).forEachOrdered((placemark) -> {
                layer.addRenderable(placemark);
            });

        } catch (IOException ex) {
            Logger.getLogger(BathySoundsComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
