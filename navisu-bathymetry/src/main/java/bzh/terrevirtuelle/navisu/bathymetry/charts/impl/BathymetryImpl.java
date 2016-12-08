/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.charts.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.bathymetry.charts.Bathymetry;
import bzh.terrevirtuelle.navisu.bathymetry.charts.BathymetryServices;
import bzh.terrevirtuelle.navisu.bathymetry.charts.impl.controller.BathymetryAnalyticController;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.Layer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author Serge
 */
public class BathymetryImpl
        implements Bathymetry, BathymetryServices, Driver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayerTreeServices layerTreeServices;

    private static final String NAME = "Bathymetry";
    private static final String EXTENSION_0 = ".glz";
    protected static final String GROUP = "Bathymetry";

    protected List<Layer> layers;
    protected static final Logger LOGGER = Logger.getLogger(BathymetryImpl.class.getName());
    protected WorldWindow wwd;

    @Override
    public Driver getDriver() {
        return this;
    }

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

    @SuppressWarnings("unchecked")
    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {
        LOGGER.log(Level.INFO, "Opening {0} ...", fileName);
        BathymetryAnalyticController bathymetryController = BathymetryAnalyticController.getInstance();
        layers.add(bathymetryController.init(fileName));
        layers.stream().filter((l) -> (l != null)).map((l) -> {
            String name = l.getName();
            //  if (name.contains(NAME)) {
            l.setPickEnabled(true);
            // } else {
            //     l.setPickEnabled(false);
            //}
            //  System.out.println("l : "+l);
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
        layers = new ArrayList<>();
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
