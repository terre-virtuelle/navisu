/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.sounds.impl.controller;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    protected RenderableLayer layer;
    protected Map<Point3D, String> soundMap;
    protected Map<Point3D, String> imageMap;
    ClickAndSoundSelectListener clickAndSoundSelectListener;

    protected static final String SOUND_DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    protected static final String IMAGE_DATA_PATH = System.getProperty("user.dir").replace("\\", "/");

    public BathySoundsComponentController(LayersManagerServices layersManagerServices,
            InstrumentDriverManagerServices instrumentDriverManagerServices,
            String fileName) {
        this.layersManagerServices = layersManagerServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        this.fileName = fileName;
        layer = layersManagerServices.getLayer(GROUP_NAME, LAYER_NAME);
        wwd = GeoWorldWindViewImpl.getWW();
        clickAndSoundSelectListener
                = new ClickAndSoundSelectListener(instrumentDriverManagerServices, wwd, PointPlacemark.class);
        wwd.addSelectListener(clickAndSoundSelectListener);
        soundMap = new HashMap<>();
        imageMap = new HashMap<>();
    }

    public void on() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            lines.stream().filter((l) -> (!l.equals(""))).map((l) -> l.split(",")).map((values) -> {
                double lat = Double.valueOf(values[0]);
                double lon = Double.valueOf(values[1]);
                String image = IMAGE_DATA_PATH + "/data/sounds/bathy/images/" + values[2];
                String text = values[3];
                String sound = "/data/sounds/bathy/sounds/" + values[4];
                soundMap.put(new Point3D(lat, lon), SOUND_DATA_PATH + sound.trim());
                
                PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(lat, lon, 0));
                PointPlacemarkAttributes attrs=new PointPlacemarkAttributes();
                attrs.setImageAddress(image);
                placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
                placemark.setLabelText(text);
                placemark.setAttributes(attrs);
                return placemark;
            }).forEachOrdered((placemark) -> {
                layer.addRenderable(placemark);
            });
            clickAndSoundSelectListener.setSoundMap(soundMap);
        } catch (IOException ex) {
            Logger.getLogger(BathySoundsComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
