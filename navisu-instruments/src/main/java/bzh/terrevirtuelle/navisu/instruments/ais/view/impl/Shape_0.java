/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.view.impl;

import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.instruments.ais.view.Shape;
import bzh.terrevirtuelle.navisu.instruments.locators.model.TShip;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;

/**
 *
 * @author Serge
 */
public class Shape_0
        extends Polygon
        implements Shape {

    Ship ship;

    public Shape_0(Ship ship, ShapeAttributes sa, Iterable<? extends Position> itrbl) {
        super(itrbl);
        this.ship = ship;
        setAttributes(sa);
    }

    @Override
    public void setLocation(Location location) {
        moveTo(new Position(Angle.fromDegrees(location.getLatitudeDegree()),
                Angle.fromDegrees(location.getLongitudeDegree()), 15));
    }

    @Override
    public Renderable[] getRenderables() {
        return new Renderable[]{this};
    }

    @Override
    public void setRotation(double cog) {
        super.setRotation(-cog);
    }

    @Override
    public String toString() {
        return "Shape_0{" + super.toString() + '}';
    }

    @Override
    public Ship getShip() {
        return ship;
    }

}
