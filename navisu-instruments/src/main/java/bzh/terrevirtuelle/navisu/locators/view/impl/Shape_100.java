/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.view.impl;

import bzh.terrevirtuelle.navisu.locators.model.TShip;
import bzh.terrevirtuelle.navisu.locators.view.Shape;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.ShapeAttributes;

/**
 *
 * @author Serge
 */
public class Shape_100
        extends Shape_1
        implements Shape {

    public Shape_100(TShip tShip, ShapeAttributes sa, LatLon latlon, double d) {
        super(tShip, sa, latlon, d);
    }

}
