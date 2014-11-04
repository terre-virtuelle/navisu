/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.impl.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.impl.controller.loader.M_COVR_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.ShapefileLoader;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.S57Object;
import bzh.terrevirtuelle.navisu.topology.surveyZone.controller.SurveyZoneController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.layers.Layer;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Serge Morvan
 * @date 02/11/2014 12:49
 */
public class S57CatalogController {

    private static final S57CatalogController INSTANCE;
    protected String path;
    private File file;
    private Map<String, String> acronyms;
    private Map<String, Map<Long, S57Object>> geos;
    private static final List<Layer> layers = new ArrayList<>();
    protected WorldWindow wwd;
    protected Globe globe;
    private ShapefileLoader loader;
    private SurveyZoneController surveyZoneController;

    static {
        INSTANCE = new S57CatalogController();
    }

    public S57CatalogController() {
       // surveyZoneController = new SurveyZoneController();
        wwd = GeoWorldWindViewImpl.getWW();
        globe = GeoWorldWindViewImpl.getWW().getModel().getGlobe();
        System.setProperty("file.encoding", "UTF-8");
    }

    public Layer getLayer(String name) {
        Layer layer = null;
        for (Layer l : layers) {
            if (l.getName().equals(name)) {
                layer = l;
            }
        }
        return layer;
    }

    public static S57CatalogController getInstance() {
        return INSTANCE;
    }

    public final void init(String path) {
        this.path = path;
        file = new File(path);
        initGeosMap();
    }

    public List<Layer> getLayers() {
        return layers;
    }

    private void initGeosMap() {
        geos = new HashMap<>();
        File[] listOfFiles;
        File tmp;
        if (file.isDirectory()) {
            listOfFiles = file.listFiles();
            // Context variables
            for (File f : listOfFiles) {
                String s = f.getName();
                // Contour de la carte
                if (s.equals("M_COVR.shp")) {
                    loader = new M_COVR_ShapefileLoader();
                   // loader = new ShapefileLoader();
                    tmp = new File(path + "/M_COVR.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("M_COVR");
                    });
                    layers.addAll(la);
                }
                if (s.contains(".shp")) {
                    geos.put(s.replace(".shp", ""), new HashMap<>());
                }
            }
        }

    }
}
