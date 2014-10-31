/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.catalog.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.catalog.controller.loader.M_COVR_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.charts.controller.loader.ShapefileLoader;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.S57Object;
import bzh.terrevirtuelle.navisu.topology.surveyZone.controller.SurveyZoneController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.layers.Layer;
//import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class ChartS57CatalogController {

    private static final ChartS57CatalogController INSTANCE;
    protected String path;
    private File file;
    private Map<String, String> acronyms;
    private Map<String, Map<Long, S57Object>> geos;
    private static final List<Layer> layers = new ArrayList<>();
    protected WorldWindow wwd;
    protected Globe globe;
    private final boolean isDisplay = false;
    private ShapefileLoader loader;
    private SurveyZoneController surveyZoneController;

    static {
        INSTANCE = new ChartS57CatalogController();
    }

    public ChartS57CatalogController() {
        //  surveyZoneController = new SurveyZoneController();
        wwd = GeoWorldWindViewImpl.getWW();
        globe = GeoWorldWindViewImpl.getWW().getModel().getGlobe();
        System.setProperty("file.encoding", "UTF-8");
        initAcronymsMap();
    }

    private void initAcronymsMap() {
        acronyms = new HashMap<>();
        String tmp;
        String[] tmpTab;
        try {
            BufferedReader input = new BufferedReader(
                    new FileReader("properties/s57AcronymClasses.txt"));
            while ((tmp = input.readLine()) != null) {
                tmpTab = tmp.split(",");
                acronyms.put(tmpTab[0], tmpTab[1]);
            }
        } catch (IOException ex) {
            Logger.getLogger(ChartS57CatalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public static ChartS57CatalogController getInstance() {
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

    private String fileToString(String fileName) {
        Path textFile = FileSystems.getDefault().getPath("data/shp/shp_0", fileName);
        List<String> read = null;
        try {
            read = Files.readAllLines(textFile, StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {

            Logger.getLogger(ChartS57CatalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.join("\n", read);
    }

    public SurveyZoneController getSurveyZoneController() {
        return surveyZoneController;
    }

}
