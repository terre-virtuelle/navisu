/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.commands.impl;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.GpsPlotterServices;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmd;

/**
 *
 * @author serge
 * @date Mar 15, 2016
 *
 */
public class OwnerShipCmd
        implements NavigationCmd {
    
    private static OwnerShipCmd INSTANCE;
    private NavigationDataSet navigationDataSet;
    private final GpsPlotterServices gpsPlotterServices;
    
    public static OwnerShipCmd getInstance(GpsPlotterServices gpsPlotterServices) {
        if (INSTANCE == null) {
            INSTANCE = new OwnerShipCmd(gpsPlotterServices);
        }
        return INSTANCE;
    }
    
    private OwnerShipCmd(GpsPlotterServices gpsPlotterServices) {
        this.gpsPlotterServices = gpsPlotterServices;
    }
    
    @Override
    public NavigationDataSet doIt(String arg) {
        navigationDataSet = new NavigationDataSet();
        Ship ship = gpsPlotterServices.getOwnerShip();
        System.out.println(ship);
        navigationDataSet.add(ship);
        System.out.println(navigationDataSet);
        return navigationDataSet;
    }
}
