/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.S57ChartComponentController;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.ACHARE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.AREA_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.BRIDGE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.BUOYAGE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.CBLSUB_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.COALNE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.DAYMAR_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.DEPARE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.DEPCNT_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.DOCARE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.LAKE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.LANDMARK_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.M_NSYS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.NAVLNE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.OBSTRN_CNT_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.OBSTRN_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.PONTON_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.SLCONS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.SOUNDG_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.TOPMAR_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.TSSBND_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.UNSARE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.UWTROC_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.WRECKS_CNT_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.WRECKS_ShapefileLoader;
import java.awt.Color;
import java.io.File;
import java.util.HashMap;

/**
 *
 * @author serge
 * @date Feb 26, 2017
 */
public class S57StlComponentController
        extends S57ChartComponentController {

    private static S57StlComponentController INSTANCE = null;

    public static S57StlComponentController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new S57StlComponentController();
        }
        return INSTANCE;
    }

    @Override
    protected void initGeosMap() {
        geos = new HashMap<>();
        File[] listOfFiles;
        File tmp;

        if (file.isDirectory()) {
            listOfFiles = file.listFiles();
            // Context variables
            for (File f : listOfFiles) {
                String s = f.getName();
                if (s.equals("M_NSYS.shp")) {
                    M_NSYS_ShapefileLoader nsys = new M_NSYS_ShapefileLoader();
                    nsys.createLayersFromSource(new File(path + "/M_NSYS.shp"));
                    marsys = nsys.getMarsys();
                }
                if (s.equals("TOPMAR.shp")) {
                    load(new TOPMAR_ShapefileLoader(topMarks), "BUOYAGE", "TOPMAR", "/");
                }
            }

            // DEPARE in background
            for (File f : listOfFiles) {
                String s = f.getName();
                switch (s) {
                    case "DEPARE.shp":
                        load(new DEPARE_ShapefileLoader(), "DEPARE", "DEPARE", "/");
                        break;
                    default:
                }
            }
            
            for (File f : listOfFiles) {
                String s = f.getName();
                switch (s) {
                    case "RESARE.shp":
                        load(new AREA_ShapefileLoader("RESARE", new Color(197, 69, 195), 0.2, false), "AREA", "RESARE", "/");
                        break;
                    case "UNSARE.shp":
                        load(new UNSARE_ShapefileLoader(), "AREA", "UNSARE", "/");
                        break;
                    default:
                }
            }
            /*
            try {
                for (File f : listOfFiles) {
                    String s = f.getName();
                    switch (s) {
                        case "ACHARE.shp":
                            load(new ACHARE_ShapefileLoader("ACHARE", new Color(2, 200, 184), 0.4, true), "AREA", "ACHARE", "/");
                            break;
                        case "BCNCAR.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BCNCAR", s57Controllers), "BUOYAGE", "BCNCAR", "/");
                            break;
                        case "BCNISD.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BCNISD", s57Controllers), "BUOYAGE", "BCNISD", "/");
                            break;
                        case "BCNLAT.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BCNLAT", s57Controllers), "BUOYAGE", "BCNLAT", "/");
                            break;
                        case "BCNSAW.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BCNSAW", s57Controllers), "BUOYAGE", "BCNSAW", "/");
                            break;
                        case "BCNSPP.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BCNSPP", s57Controllers), "BUOYAGE", "BCNSPP", "/");
                            break;
                        case "BRIDGE.shp":
                            load(new BRIDGE_ShapefileLoader(), "BUILDING", "BRIDGE", "/");
                            break;
                        case "BOYCAR.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BOYCAR", s57Controllers), "BUOYAGE", "BOYCAR", "/");
                            break;
                        case "BOYISD.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BOYISD", s57Controllers), "BUOYAGE", "BOYISD", "/");
                            break;
                        case "BOYLAT.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BOYLAT", s57Controllers), "BUOYAGE", "BOYLAT", "/");
                            break;
                        case "BOYSAW.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BOYSAW", s57Controllers), "BUOYAGE", "BOYSAW", "/");
                            break;
                        case "BOYSPP.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BOYSPP", s57Controllers), "BUOYAGE", "BOYSPP", "/");
                            break;
                        case "COALNE.shp":
                            load(new COALNE_ShapefileLoader(), "COALNE", "/");
                            break;
                        case "CBLSUB.shp":
                            load(new CBLSUB_ShapefileLoader(), "CBLSUB", "CBLSUB", "/");
                            break;
                        case "DAYMAR.shp":
                            load(new DAYMAR_ShapefileLoader(marsys), "BUOYAGE", "DAYMAR", "/");
                            break;
                        case "DEPCNT.shp":
                            load(new DEPCNT_ShapefileLoader(), "BATHYMETRY", "DEPCNT", "/");
                            break;
                        case "DOCARE.shp":
                            load(new DOCARE_ShapefileLoader(), "AREA", "DOCARE", "/");
                            break;
                        case "DGRARE.shp":
                            load(new AREA_ShapefileLoader("DGRARE", new Color(7, 149, 24), 0.0, false), "AREA", "DGRARE", "/");
                            break;
                        case "FAIRWY.shp":
                            load(new AREA_ShapefileLoader("FAIRWY", new Color(7, 141, 29), 0.0, false), "NAVIGATION", "FAIRWY", "/");
                            break;
                        case "LAKARE.shp":
                            load(new LAKE_ShapefileLoader("LAKARE", new Color(9, 13, 33), 1.0), "EARTH", "LAKARE", "/");
                            break;
                        case "LNDMRK.shp":
                            load(new LANDMARK_ShapefileLoader(DEV, marsys, "LNDMRK"), "BUILDING", "LNDMRK", "/");
                            break;
                        case "MIPARE.shp":
                            load(new AREA_ShapefileLoader("MIPARE", new Color(1, 5, 105), 0.0, false), "AREA", "MIPARE", "/");
                            break;
                        case "MORFAC.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "MORFAC", s57Controllers), "BUOYAGE", "MORFAC", "/");
                            break;
                        case "M_SREL.shp":
                            load(new AREA_ShapefileLoader("M_SREL", new Color(0, 255, 0), 0.0, false), "AREA", "M_SREL", "/");
                            break;
                        case "NAVLNE.shp":
                            load(new NAVLNE_ShapefileLoader(), "NAVIGATION", "NAVLNE", "/");
                            break;
                        case "OBSTRN.shp":
                            load(new OBSTRN_CNT_ShapefileLoader(), "DANGERS", "OBSTRN", "/");
                            load(new OBSTRN_ShapefileLoader(), "DANGERS", "OBSTRN", "/");
                            break;
                        case "PONTON.shp":
                            load(new PONTON_ShapefileLoader(), "HARBOUR", "PONTON", "/");
                            break;
                        case "SEAARE.shp":
                            load(new AREA_ShapefileLoader("SEAARE", new Color(0, 246, 232), 0.0, false), "AREA", "SEAARE", "/");
                            break;
                        case "SLCONS.shp":
                            load(new SLCONS_ShapefileLoader(), "HARBOUR", "SLCONS", "/");
                            break;
                        case "SOUNDG.shp":
                            load(new SOUNDG_ShapefileLoader(), "BATHYMETRY", "SOUNDG", "/soundg/");
                            break;
                        case "TSSBND.shp":
                            load(new TSSBND_ShapefileLoader(), "NAVIGATION", "TSSBND", "/");
                            break;
                        case "UWTROC.shp":
                            load(new UWTROC_ShapefileLoader(), "DANGERS", "UWTROC", "/");
                            break;
                        case "WRECKS.shp":
                            load(new WRECKS_CNT_ShapefileLoader(), "DANGERS", "WRECKS", "/");
                            load(new WRECKS_ShapefileLoader(), "DANGERS", "WRECKS", "/");
                            break;
                        case "LIGHTS.shp":
                            loadLights();
                            break;
                        default:
                    }

                    if (s.contains(".shp")) {
                        geos.put(s.replace(".shp", ""), new HashMap<>());
                    }
                }

            } catch (Exception e) {
                System.out.println("eee : " + e);
            }
*/
        }
        
    }
       
}
