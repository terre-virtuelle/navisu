/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.locators.view;

import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.instruments.locators.model.TShip;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;

/**
 *
 * @author Serge
 */
public interface Shape {

    void setLocation(Location location);

    default void setRotation(double cog) {
    }

    Renderable[] getRenderables();

    TShip getShip();

    public ShapeAttributes getAttributes();
}
