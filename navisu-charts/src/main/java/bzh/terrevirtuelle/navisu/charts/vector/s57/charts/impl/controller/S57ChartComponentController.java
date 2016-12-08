/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
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
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.COALNE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.DAYMAR_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.DOCARE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.LAKE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.LANDMARK_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.LayerShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.OBSTRN_CNT_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.PONTON_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.SLCONS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.SOUNDG_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.TOPMAR_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.TSSBND_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.UNSARE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.UWTROC_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.WRECKS_CNT_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.WRECKS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Behavior;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57BuoyageController;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.S57LightView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.S57Lights;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Object;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.COLOR;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.COLOR_NAME;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Light;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.widgets.surveyZone.controller.SurveyZoneController;
import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller.events.TransponderActivateEvent;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.SurfacePolylines;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class S57ChartComponentController {

    ComponentManager cm;
    ComponentEventSubscribe<TransponderActivateEvent> transponderActivateEvent;

    private LayersManagerServices layersManagerServices;
    private GeoViewServices geoViewServices;
    private GuiAgentServices guiAgentServices;

    private final String BUOYAGE_PATH = "bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo";
    private static S57ChartComponentController INSTANCE = null;
    protected String path;
    private File file;
    private Map<String, String> acronyms;
    private Map<String, Map<Long, S57Object>> geos;
    private static final List<Layer> LAYERS = Collections.synchronizedList(new ArrayList<>());
    private final List<RenderableLayer> airspaceLayers = new ArrayList<>();
    private RenderableLayer airspaceTmpLayer;
    private LIGHTS_ShapefileLoader loader;
    private final List<Light> lightList;
    private final List<Light> lightDisplayedList;
    private final List<SurfacePolylines> coastalSurfacePolylinesList;
    protected Scene scene;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected Globe globe = GeoWorldWindViewImpl.getWW().getModel().getGlobe();
    private boolean isDisplay = false;
    private final Map<Pair<Double, Double>, String> topMarks;
    private String marsys;
    private SurveyZoneController surveyZoneController;
    private RenderableLayer transponderZoneLayer;
    private final String NAME = "Transponder";
    protected final String GROUP = "Navigation";
    private final boolean DEV = false;
    //private final boolean DEV = true;
    private final Set<S57Controller> s57Controllers = new HashSet<>();
    boolean first = true;

    public static S57ChartComponentController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new S57ChartComponentController();
        }
        return INSTANCE;
    }

    private S57ChartComponentController() {
        topMarks = new HashMap<>();
        lightList = new ArrayList<>();
        lightDisplayedList = new ArrayList<>();
        coastalSurfacePolylinesList = new ArrayList<>();
        System.setProperty("file.encoding", "UTF-8");
        initAcronymsMap();
        // addImageListeners();//Test recuparation d'une image sur clic
    }

    public void subscribe() {
        transponderActivateEvent.subscribe((TransponderActivateEvent) (RenderableLayer layer, List<NavigationData> target) -> {
            target.stream().forEach((t) -> {
                s57Controllers.stream().filter((sc) -> (sc.getNavigationData().getId() == t.getId())).map((sc) -> {
                    sc.setLayer(layer);
                    return sc;
                }).forEach((sc) -> {
                    sc.activate();
                });
            });
        });
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
            Logger.getLogger(S57ChartComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RenderableLayer getLayer(String name) {
        Layer layer = null;
        for (Layer l : LAYERS) {
            if (l.getName().equals(name)) {
                layer = l;
            }
        }
        return (RenderableLayer) layer;
    }

    public final void init(String path) {
        this.path = path;
        file = new File(path);
        initGeosMap();
    }

    public List<RenderableLayer> getAirspaceLayers() {
        return airspaceLayers;
    }

    public List<Layer> getLayers() {
        return LAYERS;
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
        }
    }

    @SuppressWarnings("unchecked")
    private void loadLights() {
        loader = new LIGHTS_ShapefileLoader();
        loader.createLayersFromSource(new File(path + "/LIGHTS.shp"));
        RenderableLayer la = ((LIGHTS_ShapefileLoader) loader).getAirspaceLayer();
        if (airspaceTmpLayer == null) {
            airspaceTmpLayer = new RenderableLayer();
            airspaceTmpLayer.setName("LIGHTS_1");
            airspaceLayers.add(airspaceTmpLayer);
            geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(airspaceTmpLayer));
        }
        airspaceLayers.add(la);
        addLightListeners();

    }

    private void load(LayerShapefileLoader loader, String group, String acronym, String sep) {
        RenderableLayer l = getLayer(group);
        if (l == null) {
            l = new RenderableLayer();
            l.setName(group);
            if (acronym.contains("DEPARE")
                    || acronym.contains("OBSTRN")) {
                l.setPickEnabled(false);
            }
            LAYERS.add(l);
        }
        loader.setLayer(l);
        loader.createLayerFromSource(new File(path + sep + acronym + ".shp"));
    }

    private void load(LayerShapefileLoader loader, String acronym, String sep) {
        RenderableLayer l = new RenderableLayer();
        loader.setLayer(l);
        loader.createLayerFromSource(new File(path + sep + acronym + ".shp"));
        for (Renderable r : l.getRenderables()) {
            coastalSurfacePolylinesList.add((SurfacePolylines) r);
        }
    }

    public SurveyZoneController getSurveyZoneController() {
        return surveyZoneController;
    }

    public void setSurveyZoneController(SurveyZoneController surveyZoneController) {
        this.surveyZoneController = surveyZoneController;
    }

    private void addImageListeners() {
        wwd.addSelectListener((SelectEvent event) -> {
            Object o = event.getTopObject();
            if (event.isLeftClick() && o != null) {
                if (o.getClass().equals(PointPlacemark.class)) {
                    showImage((PointPlacemark) o);
                }
            }
        });
    }

    private void addLightListeners() {
        scene = guiAgentServices.getScene();
        lightList.addAll(loader.getLights());
        scene.setOnKeyPressed((KeyEvent t) -> {
            KeyCode key = t.getCode();
            if (key == KeyCode.L) {
                lightList.stream().forEach((l) -> {
                    showLightSectors(l);
                });
            }
        });

        wwd.addSelectListener((SelectEvent event) -> {
            S57LightView lightView;
            Object o = event.getTopObject();
            if (event.isLeftClick() && o != null) {
                if (o.getClass().getInterfaces().length != 0) {
                    if (o.getClass().getInterfaces()[0].equals(S57Lights.class)) {
                        lightView = ((S57LightView) o);
                        airspaceTmpLayer.removeAllRenderables();
                        lightDisplayedList.clear();
                        if (isDisplay == false) {
                            isDisplay = true;
                            Light data = lightView.getLight();
                            showLightSectors(data);
                        } else {
                            isDisplay = false;
                        }
                    }
                }
            }
        });
    }

    private void showLightSectors(Light data) {
        if (!lightDisplayedList.contains(data)) {
            S57LightView lightView;
            if (data != null
                    && data.getSectorLimitOne() != null
                    && data.getSectorLimitTwo() != null
                    && data.getValueOfNominalRange() != null) {
                lightView = new S57LightView();
                lightView.setTmp(true);
                double lat = data.getLatitude();
                double lon = data.getLongitude();
                double elevation = globe.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon));
                lightView.setCenter(new LatLon(Angle.fromDegrees(lat),
                        Angle.fromDegrees(lon)));
                double range = new Double(data.getValueOfNominalRange());
                lightView.setRadii(0.0, range * 1852);
                lightView.getAttributes().setInteriorOpacity(0.2);
                lightView.getAttributes().setOutlineOpacity(0.2);
                lightView.setHighlightAttributes(new BasicShapeAttributes(lightView.getAttributes()));
                lightView.getHighlightAttributes().setInteriorOpacity(1.0);
                lightView.setAltitude(elevation + 35);
                lightView.setAzimuths(Angle.fromDegrees(new Float(data.getSectorLimitOne()) + 180),
                        Angle.fromDegrees(new Float(data.getSectorLimitTwo()) + 180));

                S57Controller dummy = new S57BuoyageController(new S57Behavior() {
                    @Override
                    public void doIt(double distance, double azimuth) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                }, data, 0);
                for (S57Controller s57c : s57Controllers) {
                    if (s57c.equals(dummy) && s57c.getClass().getSimpleName().equals("S57BuoyageController")) {
                        System.out.println(((Buoyage) s57c.getNavigationData()).getObjectName());
                    }
                }

                String label = "Light \n"
                        + "Lat : " + Double.toString(lat) + "\n"
                        + "Lon : " + Double.toString(lon) + "\n"
                        + "Color : " + COLOR_NAME.getColor(data.getColour()) + "\n"
                        + (data.getSignalPeriod() != null ? "Period : " + data.getSignalPeriod() + " s" + "\n" : "")
                        + (data.getHeight() != null ? "Height : " + data.getHeight() + " m" + "\n" : "")
                        + (data.getValueOfNominalRange() != null ? "Nominal range : " + data.getValueOfNominalRange() + " Nm" + "\n" : "")
                        + "Sect1 : " + data.getSectorLimitOne() + "\n"
                        + "Sect2 : " + data.getSectorLimitTwo() + "\n";
                lightView.setValue(AVKey.DISPLAY_NAME, label);
                lightView.getAttributes().setDrawOutline(true);

                // Si la couleur est blanche, la vue est jaune
                if (data.getColour().contains("1")) {
                    lightView.getAttributes().setInteriorMaterial(new Material(COLOR.ATT.get("6")));
                    lightView.getHighlightAttributes().setInteriorMaterial(new Material(COLOR.ATT.get("6")));
                    lightView.getAttributes().setOutlineMaterial(new Material(COLOR.ATT.get("6")));
                } else {
                    lightView.getAttributes().setInteriorMaterial(new Material(COLOR.ATT.get(data.getColour())));
                    lightView.getHighlightAttributes().setInteriorMaterial(new Material(COLOR.ATT.get(data.getColour())));
                    lightView.getAttributes().setOutlineMaterial(new Material(COLOR.ATT.get(data.getColour())));
                }
                airspaceTmpLayer.addRenderable(lightView);
                lightDisplayedList.add(data);
            }
        }
    }

    private void showImage(PointPlacemark pointPlacemark) {
        //Test recuperation de l'image d'un landmark
        /*
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
                    PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(landmark.getLatitude(), landmark.getLongitude(), 0));
                    placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
                    try {
                        ImageIO.write((BufferedImage) image, "bmp", new File("data/images/minou.jpg"));
                    } catch (IOException ex) {
                        Logger.getLogger(S57ChartComponentController.class.getName()).log(Level.SEVERE, null, ex);
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
         */
    }

    public Set<S57Controller> getS57Controllers() {
        return s57Controllers;
    }

    public void setTransponderActivateEvent(ComponentEventSubscribe<TransponderActivateEvent> aisActivateES) {
        this.transponderActivateEvent = aisActivateES;
    }

    public void setGeoViewServices(GeoViewServices geoViewServices) {
        this.geoViewServices = geoViewServices;
    }

    public void setLayersManagerServices(LayersManagerServices layersManagerServices) {
        this.layersManagerServices = layersManagerServices;
    }

    public LayersManagerServices getLayersManagerServices() {
        return layersManagerServices;
    }

    public void setGuiAgentServices(GuiAgentServices guiAgentServices) {
        this.guiAgentServices = guiAgentServices;
    }

    public List<SurfacePolylines> getCoastalSurfacePolylinesList() {
        return coastalSurfacePolylinesList;
    }

}
