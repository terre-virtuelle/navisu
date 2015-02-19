/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.local.impl.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.local.impl.controller.loader.M_COVR_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.ShapefileLoader;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Object;
import bzh.terrevirtuelle.navisu.widgets.surveyZone.controller.SurveyZoneController;
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
public class S57LocalCatalogController {

    private static final S57LocalCatalogController INSTANCE;
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
        INSTANCE = new S57LocalCatalogController();
    }

    public S57LocalCatalogController() {
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

    public static S57LocalCatalogController getInstance() {
        return INSTANCE;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public List<Layer> parse(String filename) {
        loader = new M_COVR_ShapefileLoader();
        File tmp = new File(filename + "\\M_COVR.shp");
        List<Layer> la = loader.createLayersFromSource(tmp);
        la.stream().forEach((l) -> {
            l.setName("M_COVR");
        });
        layers.addAll(la);
        return layers;
    }
}
