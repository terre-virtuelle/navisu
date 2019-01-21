/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.server.impl;

import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.driver.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.extensions.server.NavigationServer;
import bzh.terrevirtuelle.navisu.extensions.server.NavigationServerServices;
import bzh.terrevirtuelle.navisu.extensions.server.impl.controller.NavigationServerController;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmdComponentServices;
import java.util.logging.Logger;
import javafx.scene.input.KeyEvent;
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

    private final String KEY_NAME = "NavigationServer";
    private NavigationServerController controller;
    protected static final Logger LOGGER = Logger.getLogger(NavigationServerImpl.class.getName());

    @Override
    public void componentInitiated() {

    }

    @Override
    public void init() {
        controller = NavigationServerController.getInstance(guiAgentServices,
                driverManagerServices,
                webDriverManagerServices,
                instrumentDriverManagerServices,
                databaseDriverManagerServices,
                navigationCmdComponentServices);
        controller.init();
    }

    @Override
    public void init(int port) {
        controller = NavigationServerController.getInstance(guiAgentServices,
                driverManagerServices,
                webDriverManagerServices,
                instrumentDriverManagerServices,
                databaseDriverManagerServices,
                navigationCmdComponentServices);
        controller.init(port);
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
                init(9090);
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
