/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.bathy.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.stl.bathy.BathyStlComponent;
import bzh.terrevirtuelle.navisu.stl.bathy.BathyStlComponentServices;
import bzh.terrevirtuelle.navisu.stl.bathy.impl.controller.BathyStlComponentController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author serge
 * @date Feb 25, 2017
 */
public class BathyStlComponentImpl
        implements BathyStlComponent, BathyStlComponentServices,
        InstrumentDriver, ComponentState {

    final String NAME = "BathyStl";
    final String LAYER_NAME = "BathyShom";
    final String LIMIT = "100";
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    DatabaseServices databaseServices;

    protected static final String GROUP = "Bathymetry data";
    protected RenderableLayer layer;
    protected BathyStlComponentController bathyStlComponentController;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected static final Logger LOGGER = Logger.getLogger(BathyStlComponentImpl.class.getName());

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void componentInitiated() {
        layer = layersManagerServices.getLayer(GROUP, LAYER_NAME);
        bathyStlComponentController = BathyStlComponentController.getInstance(this,
                databaseServices, guiAgentServices,
                LIMIT, layer);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;
        if (!category.equals(NAME)) {
        } else {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public void on(String... files) {
        String[] tab = files;

    }

    
}
