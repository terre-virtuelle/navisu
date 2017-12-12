/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.geodesy.impl;

import bzh.terrevirtuelle.navisu.geodesy.Ellipsoid;
import bzh.terrevirtuelle.navisu.geodesy.Geodesy;
import bzh.terrevirtuelle.navisu.geodesy.GeodesyServices;
import org.capcaval.c3.component.ComponentState;

/**
 *
 * @author serge
 * @date Dec 12, 2017
 */
public class GeodesyImpl 
implements Geodesy, GeodesyServices, ComponentState {
private final static Ellipsoid REFERENCE = Ellipsoid.WGS84;//default
    private final static double KM_TO_METER = 1000;

    
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
