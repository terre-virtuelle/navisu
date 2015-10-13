/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.controller;

import gov.nasa.worldwind.render.SurfaceShape;

/**
 * NaVisu
 *
 * @date 13 oct. 2015
 * @author Serge Morvan
 */
public abstract class S57Behavior {

    protected S57Controller s57Controller;
    protected SurfaceShape shape;
    protected double range;//distance d'influence en m

    /**
     * Get the value of s57Controller
     *
     * @return the value of s57Controller
     */
    public S57Controller getS57Controller() {
        return s57Controller;
    }

    /**
     * Set the value of s57Controller
     *
     * @param s57Controller new value of s57Controller
     */
    public void setS57Controller(S57Controller s57Controller) {
        this.s57Controller = s57Controller;
    }

    public SurfaceShape getShape() {
        return shape;
    }

    public void setShape(SurfaceShape shape) {
        this.shape = shape;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }


    public abstract void doIt(double distance, double azimuth);

}
