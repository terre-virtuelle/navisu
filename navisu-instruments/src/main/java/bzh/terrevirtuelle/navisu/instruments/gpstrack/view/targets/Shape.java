/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gpstrack.view.targets;

import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;

/**
 *
 * @author Serge
 */
public interface Shape {

    void setPosition(Position position);

    default void setRotation(double cog) {
    }

    Renderable[] getRenderables();

    Ship getShip();

    ShapeAttributes getAttributes();

    default void setAttributes(ShapeAttributes shapeAttributes) {

    }
}
