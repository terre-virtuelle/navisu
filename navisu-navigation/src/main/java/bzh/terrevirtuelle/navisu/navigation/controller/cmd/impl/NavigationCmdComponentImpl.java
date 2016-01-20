/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.cmd.impl;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.domain.camera.model.Camera;
import bzh.terrevirtuelle.navisu.instruments.camera.CameraComponentServices;
import bzh.terrevirtuelle.navisu.navigation.controller.cmd.catalog.ArCommand;
import org.capcaval.c3.component.ComponentState;
import bzh.terrevirtuelle.navisu.navigation.controller.cmd.NavigationCmdComponent;
import bzh.terrevirtuelle.navisu.navigation.controller.cmd.NavigationCmdComponentServices;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 7 mai 2015
 * @author Serge Morvan
 */
public class NavigationCmdComponentImpl
        implements NavigationCmdComponent, NavigationCmdComponentServices, ComponentState {

    @UsedService
    CameraComponentServices cameraComponentServices;
    @UsedService
    S57ChartComponentServices s57ChartComponentServices;

    @Override
    public void componentInitiated() {

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void init() {

    }

    @Override
    public void doIt(ArCommand navigationCmd) {
      //  doIt(navigationCmd.getNavigationData());
    }

    private void doIt(Camera navigationData){
        
    }
}
