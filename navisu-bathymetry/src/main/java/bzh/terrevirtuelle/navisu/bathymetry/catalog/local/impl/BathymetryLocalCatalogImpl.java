/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.catalog.local.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.bathymetry.catalog.local.BathymetryLocalCatalog;
import bzh.terrevirtuelle.navisu.bathymetry.catalog.local.BathymetryLocalCatalogServices;
import bzh.terrevirtuelle.navisu.bathymetry.catalog.local.impl.controller.BathymetryLocalCatalogController;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;

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
 * @date 10 nov. 2014 NaVisu project
 */
public class BathymetryLocalCatalogImpl
        implements BathymetryLocalCatalog, BathymetryLocalCatalogServices, Driver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayerTreeServices layerTreeServices;

    private static final String NAME = "Bathymetry catalog";
    private static final String EXTENSION_0 = ".txt";
    protected static final String GROUP = "Bathymetry catalog";

    protected List<Layer> layers;
    protected static final Logger LOGGER = Logger.getLogger(BathymetryLocalCatalogImpl.class.getName());
    protected WorldWindow wwd;

    @Override
    public void openFile(String file) {
        this.open(null, file);
    }

    @Override
    public boolean canOpen(String category, String file) {
        boolean canOpen = false;

        if (category.contains(NAME) && file.toLowerCase().endsWith(EXTENSION_0)) {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public void open(ProgressHandle progressHandle, String... files) {
        for (String file : files) {
            this.handleOpenFile(progressHandle, file);
        }
    }

    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {
        LOGGER.log(Level.INFO, "Opening {0} ...", fileName);
        BathymetryLocalCatalogController bathymetryLocalCatalogController = BathymetryLocalCatalogController.getInstance();
        layers = bathymetryLocalCatalogController.init(fileName);
        layers.stream().filter((l) -> (l != null)).map((l) -> {
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(l));
            return l;
        }).forEach((l) -> {
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
        });
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String[] getExtensions() {
        return new String[]{"*" + EXTENSION_0};
    }

    @Override
    public void componentInitiated() {
        layerTreeServices.createGroup(GROUP);
        wwd = GeoWorldWindViewImpl.getWW();
       
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
    public Driver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() { /* Nothing to do here */ }

    @Override
    public void componentStopped() { /* Nothing to do here */ }
}
