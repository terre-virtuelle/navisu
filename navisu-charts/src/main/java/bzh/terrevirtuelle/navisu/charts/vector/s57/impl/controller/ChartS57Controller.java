/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.DEPARE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.DEPCNT_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.BUOYAGE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.LIGHTS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.M_NSYS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.NAVLNE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.OBSTRN_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.AREA_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.OBSTRN_CNT_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.PONTON_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.SLCONS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.SOUNDG_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.TOPMAR_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.UWTROC_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.WRECKS_CNT_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader.WRECKS_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.view.LightView;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.view.Lights;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.S57Object;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.parameters.COLOUR;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.parameters.COLOUR_NAME;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.Light;
import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.layers.AirspaceLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.render.GlobeAnnotation;
import gov.nasa.worldwind.render.Material;
import java.awt.Color;
//import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
public class ChartS57Controller {

    private static final ChartS57Controller INSTANCE;
    protected String path;
    private File file;
    //  private File fileDepare;
    private Map<String, String> acronyms;
    private Map<String, Map<Long, S57Object>> geos;
    private static final List<Layer> layers = new ArrayList<>();
    private final List<AirspaceLayer> airspaceLayers = new ArrayList<>();
    private AirspaceLayer airspaceTmpLayer;
    private ShapefileLoader loader;
    protected WorldWindow wwd;
    protected Globe globe;
    private boolean isDisplay = false;
    private GlobeAnnotation tooltipAnnotation;
    private final String boyagePath = "bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo";
    private final Map<Pair, String> topMarks;
    private String marsys;

    static {
        INSTANCE = new ChartS57Controller();
    }

    public ChartS57Controller() {
        wwd = GeoWorldWindViewImpl.getWW();
        globe = GeoWorldWindViewImpl.getWW().getModel().getGlobe();
        topMarks = new HashMap<>();
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
            Logger.getLogger(ChartS57Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            // Ces deux shp doivent etre appelees en premier
            for (File f : listOfFiles) {
                String s = f.getName();
                if (s.equals("M_NSYS.shp")) {
                    M_NSYS_ShapefileLoader nsys = new M_NSYS_ShapefileLoader();
                    nsys.createLayersFromSource(new File(path + "/M_NSYS.shp"));
                    marsys = nsys.getMarsys();
                }

                if (s.equals("TOPMAR.shp")) {
                    new TOPMAR_ShapefileLoader(topMarks).createLayersFromSource(new File(path + "/TOPMAR.shp"));
                }
            }
            for (File f : listOfFiles) {
                String s = f.getName();
                if (s.equals("DEPARE.shp")) {
                    loader = new DEPARE_ShapefileLoader();
                    tmp = new File(path + "/DEPARE.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("DEPARE");
                    });
                    layers.addAll(la);
                }
                if (s.equals("DEPCNT.shp")) {
                    loader = new DEPCNT_ShapefileLoader();
                    tmp = new File(path + "/DEPCNT.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("DEPCNT");
                    });
                    layers.addAll(la);
                }
                // Contour de la carte
                /*
                 if (s.equals("M_COVR.shp")) {
                 loader = new M_COVR_ShapefileLoader();
                 tmp = new File(path + "/M_COVR.shp");
                 List<Layer> la = loader.createLayersFromSource(tmp);
                 la.stream().forEach((l) -> {
                 l.setName("M_COVR");
                 });
                 layers.addAll(la);
                 }
                 */
                if (s.equals("BCNCAR.shp")) {
                    loader = new BUOYAGE_ShapefileLoader(boyagePath, topMarks, marsys, "BCNCAR");
                    tmp = new File(path + "/BCNCAR.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("BCNCAR");
                    });
                    layers.addAll(la);
                }
                if (s.equals("BCNLAT.shp")) {
                    // loader = new BCNLAT_ShapefileLoader();
                    loader = new BUOYAGE_ShapefileLoader(boyagePath, topMarks, marsys, "BCNLAT");
                    tmp = new File(path + "/BCNLAT.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);

                    la.stream().forEach((l) -> {
                        l.setName("BCNLAT");
                    });
                    layers.addAll(la);
                }
                if (s.equals("BCNSPP.shp")) {
                    loader = new BUOYAGE_ShapefileLoader(boyagePath, topMarks, marsys, "BCNSPP");
                    tmp = new File(path + "/BCNSPP.shp");

                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("BCNSPP");
                    });
                    layers.addAll(la);
                }
                if (s.equals("BCNISD.shp")) {
                    loader = new BUOYAGE_ShapefileLoader(boyagePath, topMarks, marsys, "BCNISD");
                    tmp = new File(path + "/BCNISD.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("BCNISD");
                    });
                    layers.addAll(la);
                }
                if (s.equals("BCNSAW.shp")) {
                    loader = new BUOYAGE_ShapefileLoader(boyagePath, topMarks, marsys, "BCNSAW");
                    tmp = new File(path + "/BCNSAW.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("BCNSAW");
                    });
                    layers.addAll(la);
                }
                if (s.equals("BOYCAR.shp")) {
                    loader = new BUOYAGE_ShapefileLoader(boyagePath, topMarks, marsys, "BOYCAR");
                    tmp = new File(path + "/BOYCAR.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("BOYCAR");
                    });
                    layers.addAll(la);
                }
                if (s.equals("BOYLAT.shp")) {
                    loader = new BUOYAGE_ShapefileLoader(boyagePath, topMarks, marsys, "BOYLAT");
                    tmp = new File(path + "/BOYLAT.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);

                    la.stream().forEach((l) -> {
                        l.setName("BOYLAT");
                    });
                    layers.addAll(la);
                }
                if (s.equals("BOYISD.shp")) {
                    loader = new BUOYAGE_ShapefileLoader(boyagePath, topMarks, marsys, "BOYISD");
                    tmp = new File(path + "/BOYISD.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);

                    la.stream().forEach((l) -> {
                        l.setName("BOYISD");
                    });
                    layers.addAll(la);
                }
                if (s.equals("BOYSAW.shp")) {
                    loader = new BUOYAGE_ShapefileLoader(boyagePath, topMarks, marsys, "BOYSAW");
                    tmp = new File(path + "/BOYSAW.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);

                    la.stream().forEach((l) -> {
                        l.setName("BOYSAW");
                    });
                    layers.addAll(la);
                }
                if (s.equals("BOYSPP.shp")) {
                    loader = new BUOYAGE_ShapefileLoader(boyagePath, topMarks, marsys, "BOYSPP");
                    tmp = new File(path + "/BOYSPP.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("BOYSPP");
                    });
                    layers.addAll(la);
                }
                if (s.equals("DGRARE.shp")) {
                    loader = new AREA_ShapefileLoader("DGRARE", new Color(7, 149, 24));
                    tmp = new File(path + "/DGRARE.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("DGRARE");
                    });
                    layers.addAll(la);
                }
                if (s.equals("SEAARE.shp")) {
                    loader = new AREA_ShapefileLoader("SEAARE", new Color(0, 246, 232));
                    tmp = new File(path + "/SEAARE.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("SEAARE");
                    });
                    layers.addAll(la);
                }
                if (s.equals("RESARE.shp")) {
                    loader = new AREA_ShapefileLoader("RESARE", new Color(197, 69, 195));
                    tmp = new File(path + "/RESARE.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("RESARE");
                    });
                    layers.addAll(la);
                }
                /*
                if (s.equals("M_SREL.shp")) {
                    loader = new AREA_ShapefileLoader("M_SREL", new Color(255, 0, 0));
                    tmp = new File(path + "/M_SREL.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("M_SREL");
                    });
                    layers.addAll(la);
                }
                        */
                if (s.equals("MIPARE.shp")) {
                    loader = new AREA_ShapefileLoader("MIPARE", new Color(1, 5, 105));
                    tmp = new File(path + "/MIPARE.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("MIPARE");
                    });
                    layers.addAll(la);
                }
                if (s.equals("FAIRWY.shp")) {
                    loader = new AREA_ShapefileLoader("FAIRWY", new Color(7, 141, 29));
                    tmp = new File(path + "/FAIRWY.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("FAIRWY");
                    });
                    layers.addAll(la);
                }
                if (s.equals("NAVLNE.shp")) {
                    loader = new NAVLNE_ShapefileLoader();
                    tmp = new File(path + "/NAVLNE.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("NAVLNE");
                        //  ((RenderableLayer) l).addRenderable(tooltipAnnotation);
                    });
                    layers.addAll(la);

                }
                if (s.equals("SLCONS.shp")) {
                    loader = new SLCONS_ShapefileLoader();
                    tmp = new File(path + "/SLCONS.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("SLCONS");
                        //  ((RenderableLayer) l).addRenderable(tooltipAnnotation);
                    });
                    layers.addAll(la);

                }
                if (s.equals("PONTON.shp")) {
                    loader = new PONTON_ShapefileLoader();
                    tmp = new File(path + "/PONTON.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("PONTON");
                        //    ((RenderableLayer) l).addRenderable(tooltipAnnotation);
                    });
                    layers.addAll(la);

                }
                if (s.equals("WRECKS.shp")) {
                    loader = new WRECKS_CNT_ShapefileLoader();
                    tmp = new File(path + "/WRECKS.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("WRECKS");
                    });
                    layers.addAll(la);
                    loader = new WRECKS_ShapefileLoader();
                    tmp = new File(path + "/WRECKS.shp");
                    la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("WRECKS");
                    });
                    layers.addAll(la);
                }

                if (s.equals("OBSTRN.shp")) {
                    loader = new OBSTRN_CNT_ShapefileLoader();
                    
                    tmp = new File(path + "/OBSTRN.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("OBSTRN");
                    });
                    layers.addAll(la);

                    loader = new OBSTRN_ShapefileLoader();
                    tmp = new File(path + "/OBSTRN.shp");
                    la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("OBSTRN");
                    });
                    layers.addAll(la);
                }

                if (s.equals("UWTROC.shp")) {
                    loader = new UWTROC_ShapefileLoader();
                    //loader = new Template_ShapefileLoader();
                    tmp = new File(path + "/UWTROC.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("UWTROC");
                    });
                    layers.addAll(la);
                }
                if (s.equals("SOUNDG.shp")) {
                    loader = new SOUNDG_ShapefileLoader();
                    tmp = new File(path + "/soundg/SOUNDG.shp");
                    List<Layer> la = loader.createLayersFromSource(tmp);
                    la.stream().forEach((l) -> {
                        l.setName("SOUNDG");
                    });
                    layers.addAll(la);
                }

                if (s.equals("LIGHTS.shp")) {

                    loader = new LIGHTS_ShapefileLoader();
                    tmp = new File(path + "/LIGHTS.shp");
                    loader.createLayersFromSource(tmp);
                    AirspaceLayer la = ((LIGHTS_ShapefileLoader) loader).getAirspaceLayer();
                    la.setName("LIGHTS");
                    layers.add(la);
                    airspaceTmpLayer = new AirspaceLayer();
                    airspaceTmpLayer.setName("LIGHTS_TMP");
                    airspaceLayers.add(la);
                    airspaceLayers.add(airspaceTmpLayer);
                    wwd.addSelectListener((SelectEvent event) -> {
                        LightView lightView;
                        if (event.isLeftClick()
                                && event.getTopObject() != null
                                && event.getTopObject().getClass().getInterfaces() != null
                                && event.getTopObject().getClass().getInterfaces()[0].equals(Lights.class)) {
                            lightView = ((LightView) event.getTopObject());
                            if (lightView.isTmp() == true) {
                                airspaceTmpLayer.removeAllAirspaces();
                                isDisplay = false;
                            } else {
                                if (isDisplay == false) {
                                    isDisplay = true;
                                    airspaceTmpLayer.removeAllAirspaces();
                                    Light data = lightView.getLight();
                                    if (data.getSectorLimitOne() != null && data.getSectorLimitTwo() != null) {
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

                    });
                }
                if (s.contains(".shp")) {
                    geos.put(s.replace(".shp", ""), new HashMap<>());
                }

            }
        }

    }

    private void lightsDisplay() {

    }

    public List<Layer> makeShapefileLayers(File f) {

//        loader = null;
//        fileDepare = null;
        //       File index = new File("data/shp");
//        for(File f: index.listFiles()) f.delete();
        return null;
    }

}
