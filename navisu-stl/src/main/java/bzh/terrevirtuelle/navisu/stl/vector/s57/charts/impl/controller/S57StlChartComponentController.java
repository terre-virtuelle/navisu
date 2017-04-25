/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.S57ChartComponentController;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.M_NSYS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.TOPMAR_ShapefileLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.BUOYAGE_Stl_ShapefileLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.BaseLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.DEPARE_Stl_ShapefileLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.ElevationLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.RefLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.SLCONS_Stl_ShapefileLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.TextureLoader;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.render.Polygon;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Apr 24, 2017
 */
public class S57StlChartComponentController
        extends S57ChartComponentController {

    protected static boolean created = false;
    protected String outDirname;
    protected String outFilename;
    protected String outPathname;
    protected double tileSide;
    protected int ptsCounts;
    protected double bottom;
    protected double magnification;
    protected double scaleLatFactor;
    protected double scaleLonFactor;
    protected Polygon polyEnveloppe;
    protected Geometry geometryEnveloppe;

    public S57StlChartComponentController() {

    }

    public void compute(String outDirname, String outFilename, String outPathname,
            double scaleLatFactor, double scaleLonFactor, 
            double magnification, double tileSide,
            int ptsCounts, double bottom,
            Polygon polyEnveloppe, Geometry geometryEnveloppe) {
        this.outDirname = outDirname;
        this.outFilename = outFilename;
        this.outPathname = outPathname;
        this.scaleLatFactor = scaleLatFactor;
        this.scaleLonFactor = scaleLonFactor;
        this.magnification = magnification;
        this.tileSide=tileSide;
        this.ptsCounts=ptsCounts;
        this.bottom=bottom;
        this.polyEnveloppe = polyEnveloppe;
        this.geometryEnveloppe = geometryEnveloppe;
        
        writeInitOutFile(outPathname);
        writeTexture(outDirname, polyEnveloppe);
        writeElevation(outPathname, polyEnveloppe);
        writeS57Charts();
        writeBase(outPathname);
        //   writeRef(polyEnveloppe, outPathname);
        writeEndOutFile(outPathname);
    }


    private void writeInitOutFile(String filename) {
        String txt;
        //  try {
        txt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"
                + "<!DOCTYPE X3D PUBLIC \"ISO//Web3D//DTD X3D 3.0//EN\" "
                + "\"http://www.web3d.org/specifications/x3d-3.0.dtd\">\n"
                + "<X3D profile='Immersive' version='3.0'  "
                + "xmlns:xsd='http://www.w3.org/2001/XMLSchema-instance'"
                + " xsd:noNamespaceSchemaLocation =' "
                + "http://www.web3d.org/specifications/x3d-3.0.xsd '> \n"
                + "<head>\n"
                + "<meta name='title' content='NaVisu S57'/> \n"
                + "<meta name='author' content='" + System.getProperty("user.name") + "'/>\n"
                + "<meta name='created' content='" + new Date() + "'/>\n"
                + "<meta name='generator' content='NaVisu'/>\n"
                + "<meta name='license' content=' ../../license.html'/>\n"
                + "</head>\n"
                + "<NavigationInfo type='\"EXAMINE\" \"WALK\" \"FLY\" \"ANY\"'/>\n"
                + "<Scene>\n";
        try {
            Files.write(Paths.get(filename), txt.getBytes(), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(S57StlChartComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeElevation(String outFilename, Polygon polygon) {
        ElevationLoader elevationLoader = new ElevationLoader(polygon, tileSide, ptsCounts, bottom, magnification);
        write(outFilename, elevationLoader.compute());

    }

    private void writeTexture(String outDir, Polygon polygon) {
        TextureLoader exportImageOrElevations = new TextureLoader(outDir, polygon);
        exportImageOrElevations.doSaveImage();
    }

    private void writeBase(String outFilename) {
        BaseLoader l = new BaseLoader();
        write(outFilename, l.compute());
    }

    private void writeRef(String outFilename, Polygon polygon) {
        RefLoader l = new RefLoader(polygon);
        write(outFilename, l.compute());
    }

    private void write(String outFilename, String str) {
        try {
            Files.write(Paths.get(outFilename), str.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(DEPARE_Stl_ShapefileLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private void writeEndOutFile(String filename) {
        String txt = " </Scene>\n"
                + "</X3D> ";
        try {
            Files.write(Paths.get(filename), txt.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(DEPARE_Stl_ShapefileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void writeS57Charts() {
        geos = new HashMap<>();
        File[] listOfFiles;
        if (file != null && file.isDirectory()) {
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
                        //   load(new DEPARE_Stl_ShapefileLoader(OUT_FILE, polyEnveloppe), "DEPARE", "DEPARE", "/");
                        break;
                    case "PONTON.shp":
                        //    load(new PONTON_Stl_ShapefileLoader(OUT_PATH, polyEnveloppe), "HARBOUR", "PONTON", "/");

                        break;
                    case "SLCONS.shp":
                        SLCONS_Stl_ShapefileLoader slConsStlShapefileLoader = new SLCONS_Stl_ShapefileLoader(outPathname, polyEnveloppe);
                        load(slConsStlShapefileLoader, "HARBOUR", "SLCONS", "/");
                        String resultSl = slConsStlShapefileLoader.compute();
                        if (resultSl != null) {
                            write(outPathname, resultSl);
                        }
                        break;
                    case "BCNCAR.shp":
                        BUOYAGE_Stl_ShapefileLoader buoyageStlShapefileLoaderCar
                                = new BUOYAGE_Stl_ShapefileLoader(geometryEnveloppe, polyEnveloppe,
                                        scaleLatFactor, scaleLonFactor, tileSide,
                                        DEV, BUOYAGE_PATH, topMarks, marsys, "BCNCAR", null);
                        load(buoyageStlShapefileLoaderCar, "BUOYAGE", "BCNCAR", "/");
                        String resultCar = buoyageStlShapefileLoaderCar.compute();
                        if (resultCar != null) {
                            write(outPathname, resultCar);
                        }
                        break;
                    case "BOYCAR.shp":
                        buoyageStlShapefileLoaderCar
                                = new BUOYAGE_Stl_ShapefileLoader(geometryEnveloppe, polyEnveloppe,
                                        scaleLatFactor, scaleLonFactor, tileSide,
                                        DEV, BUOYAGE_PATH, topMarks, marsys, "BCNCAR", null);
                        load(buoyageStlShapefileLoaderCar, "BUOYAGE", "BCNCAR", "/");
                        resultCar = buoyageStlShapefileLoaderCar.compute();
                        if (resultCar != null) {
                            write(outPathname, resultCar);
                        }
                        break;
                    case "BCNLAT.shp":
                        BUOYAGE_Stl_ShapefileLoader buoyageStlShapefileLoaderLat
                                = new BUOYAGE_Stl_ShapefileLoader(geometryEnveloppe, polyEnveloppe,
                                        scaleLatFactor, scaleLonFactor, tileSide,
                                        DEV, BUOYAGE_PATH, topMarks, marsys, "BCNLAT", null);
                        load(buoyageStlShapefileLoaderLat, "BUOYAGE", "BCNLAT", "/");
                        String resultLat = buoyageStlShapefileLoaderLat.compute();
                        if (resultLat != null) {
                            write(outPathname, resultLat);
                        }
                        break;
                    case "BOYLAT.shp":
                        buoyageStlShapefileLoaderLat
                                = new BUOYAGE_Stl_ShapefileLoader(geometryEnveloppe, polyEnveloppe,
                                        scaleLatFactor, scaleLonFactor, tileSide,
                                        DEV, BUOYAGE_PATH, topMarks, marsys, "BCNLAT", null);
                        load(buoyageStlShapefileLoaderLat, "BUOYAGE", "BCNLAT", "/");
                        resultLat = buoyageStlShapefileLoaderLat.compute();
                        if (resultLat != null) {
                            write(outPathname, resultLat);
                        }
                        break;
                    default:
                }
            }
            /*
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
         
            try {
                for (File f : listOfFiles) {
                    String s = f.getName();
                    switch (s) {
                        case "ACHARE.shp":
                            load(new ACHARE_ShapefileLoader("ACHARE", new Color(2, 200, 184), 0.4, true), "AREA", "ACHARE", "/");
                            break;
                        
                        case "BCNISD.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BCNISD", s57Controllers), "BUOYAGE", "BCNISD", "/");
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
                        
                        case "BOYISD.shp":
                            load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BOYISD", s57Controllers), "BUOYAGE", "BOYISD", "/");
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
