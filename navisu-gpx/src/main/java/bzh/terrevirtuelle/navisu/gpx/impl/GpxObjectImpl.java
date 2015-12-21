/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.gpx.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.gpx.GpxObject;
import bzh.terrevirtuelle.navisu.gpx.GpxObjectServices;
import bzh.terrevirtuelle.navisu.gpx.impl.controller.GpxController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.Layer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author Serge Morvan
 * @date 17 nov. 2014 NaVisu project
 */
public class GpxObjectImpl
        implements GpxObject, GpxObjectServices, Driver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    private static final String NAME = "GPX";
    private static final String EXTENSION_0 = ".gpx";
    protected static final String GROUP = "GPX files";

    protected List<Layer> layers;
    protected static final Logger LOGGER = Logger.getLogger(GpxObjectImpl.class.getName());
    protected WorldWindow wwd;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String[] getExtensions() {
        return new String[]{"*" + EXTENSION_0};
    }

    @Override
    public void openFile(String file) {
        this.open(null, file);
    }

    @Override
    public boolean canOpen(String file) {

        boolean canOpen = false;

        if (file.toLowerCase().endsWith(EXTENSION_0)) {
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

    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {

        LOGGER.log(Level.INFO, "Opening {0} ...", fileName);
        GpxController controller = GpxController.getInstance(guiAgentServices);
        layers = controller.init(fileName);
        layers.stream().filter((l) -> (l != null)).map((l) -> {
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(l));
            return l;
        }).forEach((l) -> {
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
        });
    }

    @Override
    public Driver getDriver() {
        return this;
    }

    @Override
    public void componentInitiated() {
        layerTreeServices.createGroup(GROUP);
        wwd = GeoWorldWindViewImpl.getWW();
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
