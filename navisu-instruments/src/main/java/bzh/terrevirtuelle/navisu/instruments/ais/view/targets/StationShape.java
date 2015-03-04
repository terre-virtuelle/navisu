/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.view.targets;

import bzh.terrevirtuelle.navisu.geodesy.Location;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;

/**
 *
 * @author Serge
 */
public interface StationShape {

    public void setLocation(Location location);

    public void setRotation(double cog);

    public Renderable[] getRenderables();

    public PointPlacemarkAttributes getAttributes();

}
