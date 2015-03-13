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
    private S57GlobalCatalogImpl s57GlobalCatalogImpl;

    static {
        INSTANCE = new S57GlobalCatalogController();
    }

    private S57GlobalCatalogController() {
        layers = new ArrayList<>();
        wwd = GeoWorldWindViewImpl.getWW();
        this.wwd.addSelectListener(this);
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
                    //System.out.println("topObject " + ((KMLSurfacePolygonImpl) topObject).getEntries());
                    entries = ((KMLSurfacePolygonImpl) topObject).getEntries();
                    entries.stream().forEach((e) -> {
                        if (e.getKey().contains("DisplayName")) {
                            String filename = e.getValue() + ".000";
                            if (s57GlobalCatalogImpl.getFiles() != null) {
                                Path filepath = s57GlobalCatalogImpl.getFiles().get(filename);
                                if (filepath != null) {
                                    s57GlobalCatalogImpl.loadFile(filepath.toString());
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
        this.s57GlobalCatalogImpl = s57GlobalCatalogImpl;
    }

}
