/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.impl.S57GlobalCatalogImpl;
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
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.geom.LatLon;
import java.util.HashSet;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class S57GlobalCatalogController
        implements SelectListener {

    protected WorldWindow wwd;
    private static final S57GlobalCatalogController INSTANCE;
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

    static {
        INSTANCE = new S57GlobalCatalogController();
    }

    private S57GlobalCatalogController() {
        layers = new ArrayList<>();
        wwd = GeoWorldWindViewImpl.getWW();
        this.wwd.addSelectListener(this);
        s57ChartSet = new HashSet<>();
        wktReader = new WKTReader();
    }

    public static S57GlobalCatalogController getInstance() {
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
            RenderableLayer layer = new RenderableLayer();
            layer.setName(fileName);
            layer.addRenderable(kmlController);
            layers.add(layer);
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
                    KMLSurfacePolygonImpl polygon = (KMLSurfacePolygonImpl) topObject;
                    entries = polygon.getEntries();
                    entries.stream().forEach((e) -> {
                        if (e.getKey().contains("DisplayName")) {
                            String filename = e.getValue() + ".000";
                            if (component.getFiles() != null) {
                                Path filepath = component.getFiles().get(filename);
                                if (filepath != null) {
                                    component.loadFile(filepath.toString());
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
                                System.out.println("");
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
        String[] tab = filepath.split("/");
        if (tab.length != 0) {
            result = tab[tab.length - 1];
        }
        return result;
    }

    private long numberToId(String number) {
        String[] tab = number.split("\\.");
        if (tab.length == 2) {
            tab[0] = tab[0].substring(2);
        }
        return Long.parseLong(tab[0]);
    }

    public Set<NavigationData> getS57Charts() {
        return s57ChartSet;
    }
}
