/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.view.impl;

import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.locators.view.Shape;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceCircle;

/**
 *
 * @author Serge
 */
public class Shape_1 extends SurfaceCircle
        implements Shape {

    public Shape_1(ShapeAttributes sa, LatLon latlon, double d) {
        super(sa, latlon, d);
    }

    @Override
    public void setLocation(Location location) {
        moveTo(new Position(Angle.fromDegrees(location.getLatitudeDegree()),
                Angle.fromDegrees(location.getLongitudeDegree()), 100));
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
}
