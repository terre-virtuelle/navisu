/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geodesy.impl;

import bzh.terrevirtuelle.navisu.geodesy.Orientation;

/**
 *
 * @author Serge
 */
public class OrientationImpl
        implements Orientation {

    protected double orientation;

    public OrientationImpl() {
        orientation = 0.0;
    }

    public OrientationImpl(double orientation) {
        if (orientation < Orientation.MIN_ORIENTATION || orientation > Orientation.MAX_ORIENTATION) {
            throw new IllegalArgumentException("Orientation value must be between "
                    + Orientation.MIN_ORIENTATION + " to "
                    + Orientation.MAX_ORIENTATION + "degrees");
        }
        this.orientation = orientation;
    }

    /**
     * Get the value of orientation
     *
     * @return the value of orientation
     */
    public double getOrientationDegree() {
        return orientation;
    }

}
