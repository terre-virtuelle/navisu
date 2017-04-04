/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.app.guiagent.options.impl.controller.ConfigurationComponentController;
import bzh.terrevirtuelle.navisu.charts.util.WwjGeodesy;
import bzh.terrevirtuelle.navisu.charts.util.WwjJTS;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.impl.controller.S57GlobalCatalogController;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.S57ChartComponentController;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.M_NSYS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.TOPMAR_ShapefileLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.BUOYAGE_Stl_ShapefileLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.BaseLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.DEPARE_Stl_ShapefileLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.ElevationLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.PONTON_Stl_ShapefileLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.TextureLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.RefLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.SLCONS_Stl_ShapefileLoader;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.kml.impl.KMLSurfacePolygonImpl;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.gavaghan.geodesy.GeodeticCalculator;

/**
 *
 * @author serge
 * @date Feb 26, 2017
 */
public class S57StlComponentController
        extends S57ChartComponentController
        implements Initializable {

    private static final S57StlComponentController STL_INSTANCE = null;
    protected static final String ALARM_SOUND = "/data/sounds/pling.wav";
    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    protected String OUT_DIR = "privateData/x3d/";
    protected String OUT_FILE = "out.x3d";
    protected String OUT_PATH;
    protected InstrumentDriverManagerServices instrumentDriverManagerServices;
    protected LayerTreeServices layerTreeServices;
    protected static boolean created = false;
    protected RenderableLayer layer;
    private final String FXML = "configurationStlController.fxml";
    protected String viewgroupstyle = "configuration.css";
    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    private int line;
    private int column;
    protected WKTReader wktReader;
    protected Geometry geometry;
    private final GeodeticCalculator geoCalc;
    private KMLSurfacePolygonImpl polygon;
    List<? extends Position> positions;
    double latRangeMetric;
    double lonRangeMetric;
    double scaleLatFactor;
    double scaleLonFactor;
    double SQUARE_SIDE = 200;
    Polygon polyEnveloppe;
    protected Geometry geometryEnveloppe;
    boolean firstShow = true;
    @FXML
    public Group configgroup;
    @FXML
    public Pane view;
    @FXML
    public ImageView quit;
    @FXML
    public Button computeButton;
    @FXML
    private ChoiceBox<String> tileCB;

    public S57StlComponentController(GuiAgentServices guiAgentServices,
            LayerTreeServices layerTreeServices,
            LayersManagerServices layersManagerServices,
            InstrumentDriverManagerServices instrumentDriverManagerServices) {
        this.guiAgentServices = guiAgentServices;
        this.layersManagerServices = layersManagerServices;
        this.layerTreeServices = layerTreeServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        layer = layersManagerServices.getLayer(GROUP, NAME);
        geoCalc = new GeodeticCalculator();
        //  Layer buildings = new OSMBuildingsStlLayer();
        //  layerTreeServices.addGeoLayer("Buildings", buildings);
        // wwd.getModel().getLayers().add(buildings);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tileCB.setItems(FXCollections.observableArrayList("1", "4", "9", "25"));
        tileCB.setValue("1");

        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);
        });
        computeButton.setOnMouseClicked((MouseEvent event) -> {
            int tiles = Integer.parseInt(tileCB.getValue());
            line = column = (int) Math.sqrt(tiles);
            OUT_PATH = OUT_DIR + OUT_FILE;
            guiAgentServices.getJobsManager().newJob(OUT_PATH, (progressHandle) -> {
                displayTiles(polyEnveloppe, line, column);
                initParameters();
                writeInitOutFile(OUT_PATH);
                writeTexture(OUT_DIR, polyEnveloppe);
                writeElevation(OUT_PATH, polyEnveloppe);
                writeS57Charts();
                writeBase(OUT_PATH);
                //   writeRef(polyEnveloppe, OUT_PATH);
                writeEndOutFile(OUT_PATH);
                instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
            });
        });
    }

    @Override
    public void showGUI(KMLSurfacePolygonImpl polygon) {
        if (firstShow == true) {
            this.polygon = polygon;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(ConfigurationComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
            setTranslateX(300);
            setTranslateY(-150);
            String uri = CSS_STYLE_PATH + viewgroupstyle;
            configgroup.getStylesheets().add(uri);
            Platform.runLater(() -> {
                guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, this);
                guiAgentServices.getRoot().getChildren().add(this);
                firstShow = false;
            });
        }
        polyEnveloppe = displayChartBoudaries(polygon);
    }

    public Polygon displayChartBoudaries(KMLSurfacePolygonImpl polygon) {
        String result = WwjJTS.locationsToWKT(polygon.getLocations());
        WKTReader wkt = new WKTReader();
        Geometry geom = null;
        try {
            geom = wkt.read(result);
        } catch (ParseException ex) {
            Logger.getLogger(S57GlobalCatalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Polygon newPolygon = null;
        if (geom != null) {
            polyEnveloppe = WwjJTS.wktPolygonToPolygon(geom.getEnvelope());

            List<List<? extends Position>> positions1 = polyEnveloppe.getBoundaries();
            List<? extends Position> points = positions1.get(0);
            double x = WwjGeodesy.getDistanceM(points.get(0), points.get(1));
            double y = WwjGeodesy.getDistanceM(points.get(2), points.get(1));
            /*
            Un carre = la hauteur / nb
             */
            Position newPosition = WwjGeodesy.getPosition(points.get(1), 270, y * 1000);
            List<Position> newEnveloppe = new ArrayList<>();
            points.forEach((p) -> {
                newEnveloppe.add(p);
            });
            newEnveloppe.remove(0);
            newEnveloppe.add(0, newPosition);
            Position newP3 = new Position(newEnveloppe.get(3).getLatitude(),
                    newPosition.getLongitude(), 100.0);
            newEnveloppe.remove(3);
            newEnveloppe.add(3, newP3);

            newEnveloppe.remove(4);
            newEnveloppe.add(4, newPosition);
            newPolygon = new Polygon(newEnveloppe);

            ShapeAttributes normalAttributes = new BasicShapeAttributes();
            normalAttributes.setInteriorMaterial(Material.GREEN);
            normalAttributes.setOutlineOpacity(0.2);
            normalAttributes.setInteriorOpacity(0.2);
            normalAttributes.setOutlineMaterial(Material.GREEN);
            normalAttributes.setOutlineWidth(2);
            normalAttributes.setDrawOutline(true);
            normalAttributes.setDrawInterior(true);

            newPolygon.setAttributes(normalAttributes);

            layer.addRenderable(newPolygon);
            wwd.redrawNow();
        }
        return newPolygon;//l'enveloppe est reduite a un carre
    }

    private void displayTiles(Polygon polyEnveloppe, int line, int col) {
        Iterable<? extends LatLon> bounds = polyEnveloppe.getOuterBoundary();
        List<LatLon> listLatLon = new ArrayList<>();
        for (LatLon s : bounds) {
            listLatLon.add(s);
        }
        double latRange = listLatLon.get(0).getLatitude().getDegrees() - listLatLon.get(2).getLatitude().getDegrees();
        double lonRange = listLatLon.get(2).getLongitude().getDegrees() - listLatLon.get(0).getLongitude().getDegrees();

        latRange /= line;
        lonRange /= col;
        double orgLat = listLatLon.get(0).getLatitude().getDegrees();
        double orgLon = listLatLon.get(0).getLongitude().getDegrees();

        List<Polygon> tiles = new ArrayList<>();
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < col; j++) {
                List<Position> positions0 = new ArrayList<>();
                positions0.add(new Position(Angle.fromDegrees(orgLat - i * latRange), Angle.fromDegrees(orgLon + j * lonRange), 100.0));
                positions0.add(new Position(Angle.fromDegrees(orgLat - i * latRange), Angle.fromDegrees(orgLon + (j + 1) * lonRange), 100.0));
                positions0.add(new Position(Angle.fromDegrees(orgLat - (i + 1) * latRange), Angle.fromDegrees(orgLon + (j + 1) * lonRange), 100.0));
                positions0.add(new Position(Angle.fromDegrees(orgLat - (i + 1) * latRange), Angle.fromDegrees(orgLon + j * lonRange), 100.0));
                positions0.add(new Position(Angle.fromDegrees(orgLat - i * latRange), Angle.fromDegrees(orgLon + j * lonRange), 100.0));
                Polygon polygon1 = new Polygon(positions0);

                ShapeAttributes normalAttributes = new BasicShapeAttributes();
                normalAttributes.setInteriorMaterial(Material.RED);
                normalAttributes.setOutlineOpacity(0.5);
                normalAttributes.setInteriorOpacity(0.2);
                normalAttributes.setOutlineMaterial(Material.RED);
                normalAttributes.setOutlineWidth(2);
                normalAttributes.setDrawOutline(true);
                normalAttributes.setDrawInterior(true);
                normalAttributes.setEnableLighting(true);

                ShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
                highlightAttributes.setOutlineMaterial(Material.RED);
                highlightAttributes.setOutlineOpacity(1);
                highlightAttributes.setInteriorMaterial(Material.RED);
                highlightAttributes.setInteriorOpacity(0.2);

                polygon1.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
                polygon1.setAttributes(normalAttributes);
                polygon1.setHighlightAttributes(highlightAttributes);

                layer.addRenderable(polygon1);
                wwd.redrawNow();
            }
        }
    }

    private void initParameters() {
        positions = polyEnveloppe.getBoundaries().get(0);
        latRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(3).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(3).getLongitude().getDegrees()), 100));
        lonRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(1).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(1).getLongitude().getDegrees()), 100));
        scaleLatFactor = SQUARE_SIDE / latRangeMetric;
        scaleLonFactor = SQUARE_SIDE / lonRangeMetric;

        String wkt = WwjJTS.toPolygonWkt(positions);
        WKTReader wktReader0 = new WKTReader();

        if (wkt != null) {
            try {
                geometryEnveloppe = wktReader0.read(wkt);
            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(S57StlComponentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
                        SLCONS_Stl_ShapefileLoader slConsStlShapefileLoader = new SLCONS_Stl_ShapefileLoader(OUT_PATH, layer, polyEnveloppe);
                        load(slConsStlShapefileLoader, "HARBOUR", "SLCONS", "/");
                        String resultSl = slConsStlShapefileLoader.compute();
                        if (resultSl != null) {
                        //    write(OUT_PATH, resultSl);
                        }
                        break;
                    case "BCNCAR.shp":
                        BUOYAGE_Stl_ShapefileLoader buoyageStlShapefileLoaderCar
                                = new BUOYAGE_Stl_ShapefileLoader(geometryEnveloppe, polyEnveloppe,
                                        scaleLatFactor, scaleLonFactor, SQUARE_SIDE,
                                        DEV, BUOYAGE_PATH, topMarks, marsys, "BCNCAR", null);
                        load(buoyageStlShapefileLoaderCar, "BUOYAGE", "BCNCAR", "/");
                        String resultCar = buoyageStlShapefileLoaderCar.compute();
                        if (resultCar != null) {
                            write(OUT_PATH, resultCar);
                        }
                        break;
                    case "BOYCAR.shp":
                        buoyageStlShapefileLoaderCar
                                = new BUOYAGE_Stl_ShapefileLoader(geometryEnveloppe, polyEnveloppe,
                                        scaleLatFactor, scaleLonFactor, SQUARE_SIDE,
                                        DEV, BUOYAGE_PATH, topMarks, marsys, "BCNCAR", null);
                        load(buoyageStlShapefileLoaderCar, "BUOYAGE", "BCNCAR", "/");
                        resultCar = buoyageStlShapefileLoaderCar.compute();
                        if (resultCar != null) {
                            write(OUT_PATH, resultCar);
                        }
                        break;
                    case "BCNLAT.shp":
                        BUOYAGE_Stl_ShapefileLoader buoyageStlShapefileLoaderLat
                                = new BUOYAGE_Stl_ShapefileLoader(geometryEnveloppe, polyEnveloppe,
                                        scaleLatFactor, scaleLonFactor, SQUARE_SIDE,
                                        DEV, BUOYAGE_PATH, topMarks, marsys, "BCNLAT", null);
                        load(buoyageStlShapefileLoaderLat, "BUOYAGE", "BCNLAT", "/");
                        String resultLat = buoyageStlShapefileLoaderLat.compute();
                        if (resultLat != null) {
                            write(OUT_PATH, resultLat);
                        }
                        break;
                    case "BOYLAT.shp":
                        buoyageStlShapefileLoaderLat
                                = new BUOYAGE_Stl_ShapefileLoader(geometryEnveloppe, polyEnveloppe,
                                        scaleLatFactor, scaleLonFactor, SQUARE_SIDE,
                                        DEV, BUOYAGE_PATH, topMarks, marsys, "BCNLAT", null);
                        load(buoyageStlShapefileLoaderLat, "BUOYAGE", "BCNLAT", "/");
                        resultLat = buoyageStlShapefileLoaderLat.compute();
                        if (resultLat != null) {
                            write(OUT_PATH, resultLat);
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

    private void writeElevation(String outFilename, Polygon polygon) {
        ElevationLoader elevationLoader = new ElevationLoader(polygon);
        write(outFilename, elevationLoader.compute());

    }

    private void writeTexture(String outDir, Polygon polygon) {
        TextureLoader exportImageOrElevations = new TextureLoader(outDir, polyEnveloppe);
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

}
