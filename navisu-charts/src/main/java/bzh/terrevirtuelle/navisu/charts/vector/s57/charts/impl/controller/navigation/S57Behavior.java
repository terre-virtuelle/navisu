/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;

/**
 * NaVisu
 *
 * @date 13 oct. 2015
 * @author Serge Morvan
 */
public abstract class S57Behavior {

    protected S57Controller s57Controller;
    protected WorldWindow wwd;

    public S57Behavior() {
        wwd = GeoWorldWindViewImpl.getWW();
    }

    public S57Behavior(S57Controller s57Controller) {
        this.s57Controller = s57Controller;
    }

    public void setS57Controller(S57Controller s57Controller) {
        System.out.println("S57Behavior setS57Controller " + s57Controller);
        this.s57Controller = s57Controller;
    }

    /**
     * Get the value of s57Controller
     *
     * @return the value of s57Controller
     */
    public S57Controller getS57Controller() {
        return s57Controller;
    }

    public abstract void doIt(double distance, double azimuth);

}
