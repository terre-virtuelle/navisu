/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.ACHARE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.DEPARE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.DEPCNT_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.BUOYAGE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.LIGHTS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.M_NSYS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.NAVLNE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.OBSTRN_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.AREA_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.BRIDGE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.CBLSUB_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.DAYMAR_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.DOCARE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.LAKE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.LANDMARK_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.LayerShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.OBSTRN_CNT_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.PONTON_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.SLCONS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.SOUNDG_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.TOPMAR_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.TSSBND_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.UNSARE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.UWTROC_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.WRECKS_CNT_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.WRECKS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.LightView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.Lights;
import bzh.terrevirtuelle.navisu.charts.vector.s57.model.ExtendedBuoyage;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Object;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.COLOUR;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.COLOUR_NAME;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Light;
import bzh.terrevirtuelle.navisu.ontology.data.DataAccessServices;
import bzh.terrevirtuelle.navisu.widgets.surveyZone.controller.SurveyZoneController;
import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.layers.AirspaceLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class ChartS57Controller {

    private DataAccessServices dataAccessServices;

    private final String BUOYAGE_PATH = "bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo";
    private static final ChartS57Controller INSTANCE;
    protected String path;
    private File file;
    private Map<String, String> acronyms;
    private Map<String, Map<Long, S57Object>> geos;
    private static final List<Layer> layers = Collections.synchronizedList(new ArrayList());
    // private static final Set<Layer> layers = new HashSet<>();
    private final List<AirspaceLayer> airspaceLayers = new ArrayList<>();
    private AirspaceLayer airspaceTmpLayer;
    private ShapefileLoader loader;
    protected WorldWindow wwd;
    protected Globe globe;
    private boolean isDisplay = false;
    private final Map<Pair<Double, Double>, String> topMarks;
    private String marsys;
    private SurveyZoneController surveyZoneController;
    private final boolean DEV = false;
    //private final boolean DEV = true;
    private final List<ExtendedBuoyage> extendedBuoyages;

    static {
        INSTANCE = new ChartS57Controller();
    }

    public ChartS57Controller() {
        wwd = GeoWorldWindViewImpl.getWW();
        globe = GeoWorldWindViewImpl.getWW().getModel().getGlobe();
        topMarks = new HashMap<>();
        extendedBuoyages = new ArrayList<>();
        System.setProperty("file.encoding", "UTF-8");
        initAcronymsMap();
        addListeners();
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

    public RenderableLayer getLayer(String name) {
        Layer layer = null;
        for (Layer l : layers) {
            if (l.getName().equals(name)) {
                layer = l;
            }
        }
        return (RenderableLayer) layer;
    }

    public static ChartS57Controller getInstance() {
        return INSTANCE;
    }

    public final void init(String path) {
        this.path = path;
        file = new File(path);
        initGeosMap();
    }

    public List<AirspaceLayer> getAirspaceLayers() {
        return airspaceLayers;
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

            for (File f : listOfFiles) {
                String s = f.getName();
                switch (s) {
                    case "ACHARE.shp":
                        load(new ACHARE_ShapefileLoader("ACHARE", new Color(2, 200, 184), 0.4, true), "AREA", "ACHARE", "/");
                        break;
                    case "BCNCAR.shp":
                        load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BCNCAR", extendedBuoyages), "BUOYAGE", "BCNCAR", "/");
                        break;
                    case "BCNISD.shp":
                        load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BCNISD", extendedBuoyages), "BUOYAGE", "BCNISD", "/");
                        break;
                    case "BCNLAT.shp":
                        load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BCNLAT", extendedBuoyages), "BUOYAGE", "BCNLAT", "/");
                        break;
                    case "BCNSAW.shp":
                        load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BCNSAW", extendedBuoyages), "BUOYAGE", "BCNSAW", "/");
                        break;
                    case "BCNSPP.shp":
                        load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BCNSPP", extendedBuoyages), "BUOYAGE", "BCNSPP", "/");
                        break;
                    case "BRIDGE.shp":
                        load(new BRIDGE_ShapefileLoader(), "BUILDING", "BRIDGE", "/");
                        break;
                    case "BOYCAR.shp":
                        load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BOYCAR", extendedBuoyages), "BUOYAGE", "BOYCAR", "/");
                        break;
                    case "BOYISD.shp":
                        load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BOYISD", extendedBuoyages), "BUOYAGE", "BOYISD", "/");
                        break;
                    case "BOYLAT.shp":
                        load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BOYLAT", extendedBuoyages), "BUOYAGE", "BOYLAT", "/");
                        break;
                    case "BOYSAW.shp":
                        load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BOYSAW", extendedBuoyages), "BUOYAGE", "BOYSAW", "/");
                        break;
                    case "BOYSPP.shp":
                        load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "BOYSPP", extendedBuoyages), "BUOYAGE", "BOYSPP", "/");
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
                        load(new BUOYAGE_ShapefileLoader(DEV, BUOYAGE_PATH, topMarks, marsys, "MORFAC", extendedBuoyages), "BUOYAGE", "MORFAC", "/");
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
                    /*
                     case "LIGHTS.shp":
                     loadLights();
                     break; 
                     */
                    default:
                }

                if (s.contains(".shp")) {
                    geos.put(s.replace(".shp", ""), new HashMap<>());
                }

            }

        }

    }

    private void loadLights() {
        loader = new LIGHTS_ShapefileLoader();
        loader.createLayersFromSource(new File(path + "/LIGHTS.shp"));

        AirspaceLayer la = ((LIGHTS_ShapefileLoader) loader).getAirspaceLayer();
        la.setName("LIGHTS");
        layers.add(la);
        airspaceTmpLayer = new AirspaceLayer();
        airspaceTmpLayer.setName("LIGHTS_1");
        airspaceLayers.add(la);
        airspaceLayers.add(airspaceTmpLayer);

        wwd.addSelectListener((SelectEvent event) -> {
            LightView lightView;
            Object o = event.getTopObject();
            if (event.isLeftClick() && o != null) {
                if (o.getClass().getInterfaces().length != 0) {
                    if (o.getClass().getInterfaces()[0].equals(Lights.class)) {
                        lightView = ((LightView) o);
                        if (lightView.isTmp() == true) {
                            airspaceTmpLayer.removeAllAirspaces();
                            isDisplay = false;
                        } else {
                            if (isDisplay == false) {
                                isDisplay = true;
                                airspaceTmpLayer.removeAllAirspaces();
                                Light data = lightView.getLight();
                                if (data.getSectorLimitOne() != null
                                        && data.getSectorLimitTwo() != null
                                        && data.getValueOfNominalRange() != null) {
                                    lightView = new LightView();
                                    lightView.setTmp(true);
                                    double lat = data.getLat();
                                    double lon = data.getLon();
                                    double elevation = globe.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon));
                                    lightView.setCenter(new LatLon(Angle.fromDegrees(lat),
                                            Angle.fromDegrees(lon)));
                                    double range = new Double(data.getValueOfNominalRange());
                                    lightView.setRadii(0.0, range * 1852);
                                    lightView.getAttributes().setOpacity(0.2);
                                    lightView.getAttributes().setOutlineOpacity(0.2);
                                    lightView.setAltitude(elevation + 35);
                                    lightView.setAzimuths(Angle.fromDegrees(new Float(data.getSectorLimitOne()) + 180),
                                            Angle.fromDegrees(new Float(data.getSectorLimitTwo()) + 180));
                                    String label = "Light \n"
                                            + "Lat : " + Double.toString(lat) + "\n"
                                            + "Lon : " + Double.toString(lon) + "\n"
                                            + "Color : " + COLOUR_NAME.ATT.get(data.getColour()) + "\n"
                                            + (data.getSignalPeriod() != null ? "Period : " + data.getSignalPeriod() + " s" + "\n" : "")
                                            + (data.getHeight() != null ? "Height : " + data.getHeight() + " m" + "\n" : "")
                                            + (data.getValueOfNominalRange() != null ? "Nominal range : " + data.getValueOfNominalRange() + " Nm" + "\n" : "")
                                            + "Sect1 : " + data.getSectorLimitOne() + "\n"
                                            + "Sect2 : " + data.getSectorLimitTwo() + "\n";
                                    lightView.setValue(AVKey.DISPLAY_NAME, label);
                                    lightView.getAttributes().setDrawOutline(true);
                                    // Si la couleur est blanche, la vue est jaune
                                    if (data.getColour().contains("1")) {
                                        lightView.getAttributes().setMaterial(new Material(COLOUR.ATT.get("6")));
                                        lightView.getAttributes().setOutlineMaterial(new Material(COLOUR.ATT.get("6")));
                                    } else {
                                        lightView.getAttributes().setMaterial(new Material(COLOUR.ATT.get(data.getColour())));
                                        lightView.getAttributes().setOutlineMaterial(new Material(COLOUR.ATT.get(data.getColour())));
                                    }
                                    airspaceTmpLayer.addAirspace(lightView);
                                }
                            } else {
                                airspaceTmpLayer.removeAllAirspaces();
                                isDisplay = false;
                            }
                        }

                    }
                }
            }
        });

    }

    private void load(LayerShapefileLoader loader, String group, String acronym, String sep) {
        RenderableLayer l = getLayer(group);

        if (l == null) {
            l = new RenderableLayer();
            l.setName(group);
            if (acronym.contains("DEPARE")
                    || acronym.contains("OBSTRN")
                    || acronym.contains("LIGHTS")) {
                l.setPickEnabled(false);
            }
            layers.add(l);
        }
        loader.setLayer(l);
        loader.createLayerFromSource(new File(path + sep + acronym + ".shp"));
    }

    public SurveyZoneController getSurveyZoneController() {
        return surveyZoneController;
    }

    public void setSurveyZoneController(SurveyZoneController surveyZoneController) {
        this.surveyZoneController = surveyZoneController;
    }

    private void addListeners() {
        wwd.addSelectListener((SelectEvent event) -> {
            Object o = event.getTopObject();
            if (event.isLeftClick() && o != null) {
                if (o.getClass().equals(PointPlacemark.class)) {
                    showImage((PointPlacemark) o);
                }
            }
        });
    }

    public void setDataAccessServices(DataAccessServices dataAccessServices) {
        this.dataAccessServices = dataAccessServices;
    }

    private void showImage(PointPlacemark pointPlacemark) {
        String label = ((PointPlacemark) pointPlacemark).getLabelText();
        if (label != null && !"".equals(label)) {
            // Image image = dataAccessServices.queryImage(label);
            Object object = (pointPlacemark).getValue("Model");
            if (object.getClass().getName().contains("Landmark")) {

                Landmark landmark = (Landmark) object;
                if (landmark.getObjectName().contains("Minou")) {
                    dataAccessServices.test();
                    Image image = dataAccessServices.queryImage("Phare du Petit Minou");
                    PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
                    PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(landmark.getLat(), landmark.getLon(), 0));
                    placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
                    try {
                        ImageIO.write((BufferedImage) image, "bmp", new File("data/images/minou.jpg"));
                    } catch (IOException ex) {
                        Logger.getLogger(ChartS57Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    attrs.setImageAddress("data/images/minou.jpg");
                    attrs.setImageOffset(Offset.BOTTOM_CENTER);
                    attrs.setScale(0.6);//0.8
                    placemark.setAttributes(attrs);
                    RenderableLayer l = getLayer("BUOYAGE");
                    l.addRenderable(placemark);
                }
            }
        }
    }

    public List<ExtendedBuoyage> getExtendedBuoyageList() {
        return extendedBuoyages;
    }
    
}
