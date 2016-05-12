/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.common.view.targets.impl;


import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.common.view.targets.Shape;

import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceCircle;
import javafx.scene.control.Button;

/**
 *
 * @author Serge
 */
public class Shape_0
        extends SurfaceCircle
        implements Shape {

    Button button;
    Ship ship;
    boolean first = true;

    public Shape_0(Ship ship, ShapeAttributes sa, LatLon latlon, double d) {
        super(sa, latlon, d);
        this.ship = ship;
    }

    @Override
    public void setPosition(Position position) {
        moveTo(position);
    }

    @Override
    public void setRotation(double cog) {
        // Nothing todo
    }

    @Override
    public Renderable[] getRenderables() {

        return new Renderable[]{this};
    }

    @Override
    public String toString() {
        return "Shape_1{" + super.toString() + '}';
    }
  
    @Override
    public Ship getShip() {
        return ship;
    }
}
