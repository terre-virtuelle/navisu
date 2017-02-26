/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.S57ChartComponentController;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.S57StlComponent;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.S57StlComponentServices;
import gov.nasa.worldwind.WorldWindow;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author serge
 * @date Feb 25, 2017
 */
public class S57StlComponentImpl
        implements S57StlComponent, S57StlComponentServices, Driver, ComponentState {

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
    @UsedService
    S57ChartComponentServices s57ChartComponentServices;

    private static final String NAME = "S57Stl";
    protected static final String GROUP = "S57Stl charts";
    private static final String EXTENSION_0 = ".000";
    private static final String EXTENSION_1 = ".001";
    private static final String EXTENSION_2 = ".002";
    private static final String EXTENSION_3 = ".003";
    protected S57ChartComponentController s57ChartComponentController;
    protected WorldWindow wwd;

    @Override
    public boolean canOpen(String category, String file) {
        boolean canOpen = false;
        if (category.equals(NAME) && (
                file.toLowerCase().endsWith(EXTENSION_0)
                || file.toLowerCase().endsWith(EXTENSION_1)
                || file.toLowerCase().endsWith(EXTENSION_2)
                || file.toLowerCase().endsWith(EXTENSION_3))) {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public void open(ProgressHandle pHandle, String... files) {
        System.out.println("S57StlComponentImpl open");
        s57ChartComponentServices.open(pHandle, files);
        s57ChartComponentController = s57ChartComponentServices.getS57ChartComponentController();
        System.out.println("S57StlComponentImpl "+s57ChartComponentController);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String[] getExtensions() {
        return new String[]{"*" + EXTENSION_0,
            "*" + EXTENSION_1,
            "*" + EXTENSION_2,
            "*" + EXTENSION_3
        };
    }

    @Override
    public void openChart(String file) {
        System.out.println("file " + file);
        this.open(null, file);
    }

    @Override
    public Driver getDriver() {
        return this;
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
