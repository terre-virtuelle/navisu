/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.wms.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.wms.WMS;
import bzh.terrevirtuelle.navisu.wms.WMSServices;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVListImpl;
import gov.nasa.worldwind.ogc.wms.WMSCapabilities;
import java.net.URI;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @date 4 mars 2015
 * @author Serge Morvan
 */
public class WMSImpl
        implements WMS, WMSServices,  ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    protected static final String GROUP = "WMS";
    protected WorldWindow wwd = null;
    protected final URI serverURI = null;

    @UsedService
    @Override
    public void componentInitiated() {
        wwd = GeoWorldWindViewImpl.getWW();
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

   

    @Override
    public void load(String server) {
    }

    @Override
    public Driver getDriver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    protected static class LayerInfo {

        protected WMSCapabilities caps;
        protected AVListImpl params = new AVListImpl();

        protected String getTitle() {
            return params.getStringValue(AVKey.DISPLAY_NAME);
        }

        protected String getName() {
            return params.getStringValue(AVKey.LAYER_NAMES);
        }

        protected String getAbstract() {
            return params.getStringValue(AVKey.LAYER_ABSTRACT);
        }
    }

}
