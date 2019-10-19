/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.server.impl;

import bzh.terrevirtuelle.navisu.agents.ship.ShipAgentServices;
import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.driver.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.extensions.server.NavigationServer;
import bzh.terrevirtuelle.navisu.extensions.server.NavigationServerServices;
import bzh.terrevirtuelle.navisu.extensions.server.impl.controller.NavigationServerController;
import bzh.terrevirtuelle.navisu.extensions.server.impl.controller.NavigationTcpServerController;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmdComponentServices;
import bzh.terrevirtuelle.navisu.kml.KmlComponentServices;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author Serge
 */
public class NavigationServerImpl
        implements NavigationServer, NavigationServerServices, InstrumentDriver, ComponentState {

    @UsedService
    NavigationCmdComponentServices navigationCmdComponentServices;
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    DriverManagerServices driverManagerServices;
    @UsedService
    WebDriverManagerServices webDriverManagerServices;
    @UsedService
    InstrumentDriverManagerServices instrumentDriverManagerServices;
    @UsedService
    DatabaseDriverManagerServices databaseDriverManagerServices;
    @UsedService
    KmlComponentServices kmlComponentServices;
    @UsedService
    ShipAgentServices shipAgentServices;
    @UsedService
    BathymetryDBServices bathymetryDBServices;
    @UsedService
    LayersManagerServices layersManagerServices;

    private final String KEY_NAME = "NavigationServer";
    private static final int PORT = 9090;

    private NavigationServerController controller;
    private NavigationTcpServerController tcpController;
    
    protected static final Logger LOGGER = Logger.getLogger(NavigationServerImpl.class.getName());

    @Override
    public void componentInitiated() {

    }
/*
    ShipAgentServices shipAgentServices,
            LayersManagerServices layersManagerServices,
            GuiAgentServices guiAgentServices,
            NavigationCmdComponentServices navigationCmdComponentServices,
            KmlComponentServices kmlComponentServices,
            BathymetryDBServices bathymetryDBServices
    */
    @Override
    public void init() {
        controller = NavigationServerController.getInstance(shipAgentServices,
                layersManagerServices,
                guiAgentServices, 
                navigationCmdComponentServices,
                kmlComponentServices,
                bathymetryDBServices);
        controller.init();
    }

    @Override
    public void init(int port) {
        controller = NavigationServerController.getInstance(shipAgentServices,
                layersManagerServices,
                guiAgentServices, 
                navigationCmdComponentServices,
                kmlComponentServices,
                bathymetryDBServices);
        controller.init(port);
    }

    
    @Override
    public void initTcpServer(int port) {
       controller = NavigationServerController.getInstance(shipAgentServices,
                layersManagerServices,
                guiAgentServices, 
                navigationCmdComponentServices,
                kmlComponentServices,
                bathymetryDBServices);
        controller.initTcpServer(port);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {

    }

    @Override
    public void on(String... files) {
        String[] cmd = files;
        if (cmd != null) {
            if (cmd[0].equals(KEY_NAME)) {
                init(PORT);
            }
        }
    }

    @Override
    public void off() {//TODO

    }

    @Override
    public boolean canOpen(String category) {
        return category.equals(KEY_NAME);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }
}
