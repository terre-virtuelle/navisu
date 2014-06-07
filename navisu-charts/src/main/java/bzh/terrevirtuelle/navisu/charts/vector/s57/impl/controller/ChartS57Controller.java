/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.S57Object;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class ChartS57Controller {

    private static final ChartS57Controller INSTANCE;
    protected String path;
    private File file;
    private File fileDepare;
    private Map<String, String> acronyms;
    private Map<String, Map<Long, S57Object>> geos;
    private List<Layer> layers;

    static {
        INSTANCE = new ChartS57Controller();
    }

    public ChartS57Controller() {
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
            Logger.getLogger(ChartS57Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ChartS57Controller getInstance() {
        return INSTANCE;
    }

    public final void init(String path) {
        this.path = path;
        file = new File(path);
        //  fileDepare = new File(path + "/DEPARE.shp");
        initGeosMap();
    }

    private void initGeosMap() {
        geos = new HashMap<>();

        File[] listOfFiles;

        if (file.isDirectory()) {
            listOfFiles = file.listFiles();
            for (File f : listOfFiles) {
                String s = f.getName();
                if (s.equals("DEPARE.shp")) {
                    fileDepare = f;
                    System.out.println("s : " + s);
                }
                if (s.contains(".shp")) {
                    geos.put(s.replace(".shp", ""), new HashMap<>());
                }
            }
        }
    }

    public List<Layer> makeShapefileLayers() {

        ShapefileLoader loader = new DEPARE_ShapefileLoader();

        List<Layer> l =  loader.createLayersFromSource(this.fileDepare);
//        loader = null;
//        fileDepare = null;
 //       File index = new File("data/shp");
//        for(File f: index.listFiles()) f.delete();
        return l;
    }

}
