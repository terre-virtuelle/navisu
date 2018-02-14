package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.Layer;
import java.util.List;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;
import bzh.terrevirtuelle.navisu.instruments.transponder.impl.events.TransponderActivateEvent;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.S57DBComponent;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.S57DBComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.S57DBComponentController;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class S57DBComponentImpl
        implements S57DBComponent, S57DBComponentServices, Driver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    S57GlobalCatalogServices s57GlobalCatalogServices;

    ComponentManager cm;
    ComponentEventSubscribe<TransponderActivateEvent> transponderActivateEvent;

    private static final String NAME = "S57DB";

    protected S57DBComponentController s57DBComponentController;
    protected Layer layer;
    
    protected static final Logger LOGGER = Logger.getLogger(S57DBComponentImpl.class.getName());
    protected WorldWindow wwd;
    protected String userDirPath = null;
    protected String shpDir;
    protected String dir;

    @SuppressWarnings("unchecked")
    @Override
    public void componentInitiated() {
        wwd = GeoWorldWindViewImpl.getWW();
       }

    @Override
    public boolean canOpen(String category, String file) {
        boolean canOpen = false;
        if (category.equals(NAME)) {
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

    @SuppressWarnings("unchecked")
    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {

    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Driver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() {

    }

    @Override
    public void componentStopped() {

    }

    @Override
    public List<Buoyage> getBuoyage(String database, String user, String passwd,
            Buoyage buoy, double lat0, double lon0, double lat1, double lon1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getExtensions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
