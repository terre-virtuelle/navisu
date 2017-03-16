/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.impl.controller;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.impl.S57GlobalCatalogImpl;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.kml.KMLRoot;
import gov.nasa.worldwind.ogc.kml.impl.KMLController;
import gov.nasa.worldwind.ogc.kml.impl.KMLSurfacePolygonImpl;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.xml.stream.XMLStreamException;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Chart;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.geom.LatLon;
import java.util.HashSet;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class S57GlobalCatalogController
        implements SelectListener {

    protected WorldWindow wwd;
    private static S57GlobalCatalogController INSTANCE = null;
    private static final String SEP = Pattern.quote(System.getProperty("file.separator"));
    protected String path;
    protected String fileName;
    private Set<Map.Entry<String, Object>> entries;
    private final List<Layer> layers;
    private S57GlobalCatalogImpl component;
    private S57Chart s57Chart;
    protected Set<NavigationData> s57ChartSet;
    protected WKTReader wktReader;
    protected Geometry geometry = null;
    protected GuiAgentServices guiAgentServices;
    protected LayersManagerServices layersManagerServices;

    protected KeyCode keyCode;
    protected KMLSurfacePolygonImpl polygon;
    protected RenderableLayer kmlLayer;

    private S57GlobalCatalogController(GuiAgentServices guiAgentServices,
            LayersManagerServices layersManagerServices) {
        this.guiAgentServices = guiAgentServices;
        this.layersManagerServices = layersManagerServices;
        this.layers = new ArrayList<>();
        wwd = GeoWorldWindViewImpl.getWW();
        this.wwd.addSelectListener(this);
        s57ChartSet = new HashSet<>();
        wktReader = new WKTReader();
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            keyCode = event.getCode();
        });
    }

    public static S57GlobalCatalogController getInstance(GuiAgentServices guiAgentServices,
            LayersManagerServices layersManagerServices) {
        if (INSTANCE == null) {
            INSTANCE = new S57GlobalCatalogController(guiAgentServices, layersManagerServices);
        }

        return INSTANCE;
    }

    public final List<Layer> init(String path) {
        fileName = path;
        String[] tab = fileName.split(SEP);
        fileName = tab[tab.length - 1];
        tab = fileName.split("\\.");
        fileName = tab[0];
        this.path = path;
        try {
            KMLRoot document = KMLRoot.createAndParse(path);
            KMLController kmlController = new KMLController(document);
            kmlLayer = new RenderableLayer();
            kmlLayer.setName(fileName);
            kmlLayer.addRenderable(kmlController);
            layers.add(kmlLayer);
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(S57GlobalCatalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return layers;
    }

    @Override
    public void selected(SelectEvent event) {
        if (event.isRightClick()) {
            Object topObject = event.getTopObject();
            if (topObject != null) {
                if (topObject.getClass() == KMLSurfacePolygonImpl.class) {
                    polygon = (KMLSurfacePolygonImpl) topObject;
                    entries = polygon.getEntries();
                    entries.stream().forEach((e) -> {
                        if (e.getKey().contains("DisplayName")) {
                            String filename = e.getValue() + ".000";
                            if (component.getFiles() != null) {
                                Path filepath = component.getFiles().get(filename);
                                if (filepath != null) {
                                    if (keyCode == KeyCode.F1) {
                                        InstrumentDriver instrument = component.openFile("S57Stl", filepath.toString());
                                        keyCode = null;
                                        if (instrument != null) {
                                            instrument.showGUI(polygon);
                                        }
                                    } else {
                                        component.loadFile(filepath.toString());
                                    }
                                    s57Chart = new S57Chart();
                                    String number = filepathToNumber(filepath.toString());
                                    s57Chart.setNumber(number);

                                    s57Chart.setId(numberToId(number));
                                    String wkt = locationsToWKT(polygon.getLocations());
                                    if (wkt != null) {
                                        s57Chart.setGeometry(wkt);
                                    }
                                    s57ChartSet.add(s57Chart);
                                } else {
                                    System.out.println("La carte: " + filename + " n'est pas dans votre catalogue");
                                }
                            } else {
                                System.out.println(Translator.tr("chart.error"));
                            }
                        }
                    });
                }
            }
        }
    }

    public void setS57GlobalCatalogImpl(S57GlobalCatalogImpl s57GlobalCatalogImpl) {
        this.component = s57GlobalCatalogImpl;
    }

    private String locationsToWKT(Iterable<? extends LatLon> locations) {
        String[] tab;
        String result = "POLYGON((";
        List<String> locList = new ArrayList<>();
        for (LatLon l : locations) {
            tab = l.toString().split(",");
            if (tab.length == 3) {
                tab[0] = tab[0].replace("(", "");
                tab[0] = tab[0].replace("°", "");
                tab[1] = tab[1].replace("°", "");
                locList.add(tab[1].trim() + " " + tab[0].trim());
            }
        }
        for (int i = 0; i < locList.size() - 1; i++) {
            result += locList.get(i) + ",";
        }
        result += locList.get(locList.size() - 1) + "))";
        return result;
    }

    private String filepathToNumber(String filepath) {
        String result = null;
        String[] tab = null;
        if (OS.isWindows()) {
            tab = filepath.split(Pattern.quote("\\"));
        } else if (OS.isLinux()) {
            tab = filepath.split(Pattern.quote("/"));
        }
        if (tab != null && tab.length != 0) {
            result = tab[tab.length - 1];
        }
        return result;
    }

    private long numberToId(String number) {
        StringBuilder result = new StringBuilder();
        number.chars().mapToObj(i -> (char) i).filter(c -> Character.isDigit(c)).forEach(c -> result.append(c));
        return Long.parseLong(result.toString());
    }

    public Set<NavigationData> getS57Charts() {
        return s57ChartSet;
    }

}
