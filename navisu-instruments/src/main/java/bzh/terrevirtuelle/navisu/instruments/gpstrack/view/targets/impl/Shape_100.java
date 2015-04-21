/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gpstrack.view.targets.impl;

import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.view.targets.Shape;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.ShapeAttributes;

/**
 *
 * @author Serge
 */
public class Shape_100
        extends Shape_0
        implements Shape {

    public Shape_100(Ship ship, ShapeAttributes sa, LatLon latlon, double d) {
        super(ship, sa, latlon, d);
    }

}
