package bzh.terrevirtuelle.navisu.kml.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;

import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.kml.KmlObject;
import bzh.terrevirtuelle.navisu.kml.KmlObjectServices;
import bzh.terrevirtuelle.navisu.kml.impl.controller.KmlController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;
import gov.nasa.worldwind.ogc.collada.impl.ColladaController;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class KmlObjectImpl
        implements KmlObject, KmlObjectServices, Driver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    private static final String NAME = "KML";
    private static final String EXTENSION_0 = ".kmz";
    private static final String EXTENSION_1 = ".kml";
    protected static final String GROUP = "KML files";

    protected List<Layer> layers;
    protected static final Logger LOGGER = Logger.getLogger(KmlObjectImpl.class.getName());
    protected WorldWindow wwd;
    protected ColladaRoot colladaRoot = null;

    @Override
    public void componentInitiated() {
        layerTreeServices.createGroup(GROUP);
        wwd = GeoWorldWindViewImpl.getWW();
        wwd.addPositionListener((PositionEvent event) -> {
            float altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
            if (altitude >= 3000) {
                //  clip();
            } else {
                //  unClip();
            }
        });
    }

    @Override
    public boolean canOpen(String category, String file) {

        boolean canOpen = false;

        if (category.equalsIgnoreCase(NAME)
                && (file.toLowerCase().endsWith(EXTENSION_0)
                || file.toLowerCase().endsWith(EXTENSION_1))) {
            canOpen = true;
        }

        return canOpen;
    }

    @Override
    public void open(ProgressHandle pHandle, String... files) {

        for (String file : files) {
            this.handleOpenFile(pHandle, file);
        }
    }

    @Override
    public ColladaRoot openColladaFile(RenderableLayer layer, String filename) {
        File file = new File(filename);
        try {
            colladaRoot = ColladaRoot.createAndParse(file);
            ColladaController colladaController = new ColladaController(colladaRoot);
            layer.addRenderable(colladaController);
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(KmlObjectImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return colladaRoot;
    }

    @SuppressWarnings("unchecked")
    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {
        LOGGER.log(Level.INFO, "Opening {0} ***", fileName);
        KmlController kmlController = KmlController.getInstance();
        layers = kmlController.init(fileName);
        layers.stream().filter((l) -> (l != null)).map((l) -> {
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(l));
            return l;
        }).forEach((l) -> {
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
        });
    }

    private void clip() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains(NAME))).forEach((l) -> {
                l.setEnabled(false);
            });
        }
    }

    private void unClip() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains(NAME))).forEach((l) -> {
                l.setEnabled(true);
            });
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String[] getExtensions() {
        return new String[]{"*" + EXTENSION_0, "*" + EXTENSION_1};
    }

    @Override
    public void openFile(String file) {
        this.open(null, file);
    }

    @Override
    public Driver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() {
        /* Nothing to do here */ }

    @Override
    public void componentStopped() {
        /* Nothing to do here */ }

}
