/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.commands.impl;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.GpsPlotterServices;
import bzh.terrevirtuelle.navisu.navigation.camera.CameraComponentServices;
import org.capcaval.c3.component.ComponentState;
import bzh.terrevirtuelle.navisu.navigation.controller.commands.NavigationCmdComponent;
import bzh.terrevirtuelle.navisu.navigation.controller.commands.NavigationCmdComponentServices;
import bzh.terrevirtuelle.navisu.navigation.camera.impl.controller.CameraCmd;
import bzh.terrevirtuelle.navisu.navigation.controller.commands.NavigationCmd;
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
    @UsedService
    GpsPlotterServices gpsPlotterServices;

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

        navigationCmdMap.put("NaVigationDataSetCmd", NaVigationDataSetCmd.getInstance());
        navigationCmdMap.put("OwnerShipCmd", OwnerShipCmd.getInstance(gpsPlotterServices));
    }

    @Override
    public NavigationDataSet doIt(String cmd, NavigationData navigationData) {
        NavigationCmd tmp = navigationCmdMap.get(cmd.trim());
        if (tmp != null) {
            return tmp.doIt(navigationData);
        }
        return new NavigationDataSet();
    }

    @Override
    public NavigationDataSet doIt(String cmd, String arg) {
        NavigationCmd tmp = navigationCmdMap.get(cmd.trim());
        if (tmp != null) {
            return tmp.doIt(arg);
        }
        return new NavigationDataSet();
    }
}
