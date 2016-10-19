/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.server.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.navigation.controller.commands.NavigationCmdComponentServices;
import bzh.terrevirtuelle.navisu.navigation.server.NavigationServer;
import bzh.terrevirtuelle.navisu.navigation.server.NavigationServerServices;
import bzh.terrevirtuelle.navisu.navigation.server.impl.controller.NavigationServerController;
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

    private final String KEY_NAME = "NavigationServer";
    private NavigationServerController navigationServerController;
    protected static final Logger LOGGER = Logger.getLogger(NavigationServerImpl.class.getName());

    @Override
    public void componentInitiated() {

    }

    @Override
    public void init() {
        navigationServerController = NavigationServerController.getInstance();
        navigationServerController.setNavigationCmdComponentServices(navigationCmdComponentServices);
        navigationServerController.init();
    }

    @Override
    public void init(int port) {
        navigationServerController = NavigationServerController.getInstance();
        navigationServerController.setNavigationCmdComponentServices(navigationCmdComponentServices);
        navigationServerController.init(port);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {

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
