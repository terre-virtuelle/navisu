/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.view;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;

/**
 *
 * @author Serge
 */
public class GArea
        implements GObject {

    protected final int id;
    protected TShip tShip;
    protected Shape shape;

    public GArea(int id, int shapeId, TShip ship) {
        this.id = id;
        this.tShip = ship;
    }

    @Override
    public int getID() {
        return this.id;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Get the value of ship
     *
     * @return the value of ship
     */
    public TShip getShip() {
        return tShip;
    }

    /**
     * Set the value of ship
     *
     * @param ship new value of ship
     */
    public void setShip(TShip ship) {
        this.tShip = ship;
    }

    @Override
    public void setLocation(Location location) {
        shape.setLocation(location);
    }

    public ShapeAttributes getAttributes() {
        return shape.getAttributes();
    }

    @Override
    public Renderable[] getRenderables() {
        return shape.getRenderables();
    }

    public void setCog(double cog) {
        shape.setRotation(cog);
    }

    @Override
    public Object getClone() {
        return this;
    }

    @Override
    public String toString() {
        return "GArea{" + "id=" + id + ", tShip=" + tShip + ", shape=" + shape + '}';
    }

}
