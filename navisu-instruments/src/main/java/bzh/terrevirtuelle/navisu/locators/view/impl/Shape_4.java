/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.view.impl;

import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.locators.view.StationShape;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.Renderable;

/**
 *
 * @author Serge
 */
public class Shape_4
        extends PointPlacemark
        implements StationShape {

    public Shape_4(Position pstn) {
        super(pstn);
    }

    @Override
    public void setLocation(Location location) {
        moveTo(new Position(Angle.fromDegrees(location.getLatitudeDegree()),
                Angle.fromDegrees(location.getLongitudeDegree()), 100));
    }

    @Override
    public Renderable[] getRenderables() { 
        return new Renderable[]{this};
    }

    @Override
    public void setRotation(double cog) {

    }

    @Override
    public String toString() {
        return "Shape_0{" + super.toString() + '}';
    }
  
}
