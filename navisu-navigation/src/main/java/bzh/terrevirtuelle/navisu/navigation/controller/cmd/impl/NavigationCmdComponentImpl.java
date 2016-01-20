/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.cmd.impl;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.instruments.camera.CameraComponentServices;
import org.capcaval.c3.component.ComponentState;
import bzh.terrevirtuelle.navisu.navigation.controller.cmd.NavigationCmdComponent;
import bzh.terrevirtuelle.navisu.navigation.controller.cmd.NavigationCmdComponentServices;
import bzh.terrevirtuelle.navisu.navigation.controller.cmd.catalog.CameraCmd;
import bzh.terrevirtuelle.navisu.navigation.controller.cmd.catalog.NavigationCmd;
import java.util.HashMap;
import java.util.Map;
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

    private CameraCmd cameraCmd;
    private Map<String, NavigationCmd> navigationCmdMap;

    public NavigationCmdComponentImpl() {
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

    @Override
    public void init() {
        navigationCmdMap = new HashMap<>();
        
        cameraCmd = CameraCmd.getInstance();
        cameraCmd.setCameraComponentServices(cameraComponentServices);
        
        navigationCmdMap.put("CameraCmd", cameraCmd);
    }

    @Override
    public NavigationData doIt(String cmd, NavigationData navigationData) {
       return  ((NavigationCmd)navigationCmdMap.get(cmd)).doIt(navigationData);
    }

}
