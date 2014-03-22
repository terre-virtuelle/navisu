package bzh.terrevirtuelle.navisu.locators.view;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;

/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 18:49
 */
public abstract class GShip implements GObject {

    protected final int id;
    protected TShip ship;
    protected Renderable[] renderables;

    public GShip(int id, TShip ship) {
        this.id = id;
        this.ship = ship;
    }

    @Override
    public int getID() {
        return this.id;
    }

    /**
     * Get the value of ship
     *
     * @return the value of ship
     */
    public TShip getShip() {
        return ship;
    }

    /**
     * Set the value of ship
     *
     * @param ship new value of ship
     */
    public void setShip(TShip ship) {
        this.ship = ship;
    }

    @Override
    public abstract void setLocation(Location location);

    public abstract ShapeAttributes getAttributes();

    @Override
    public Renderable[] getRenderables() {
        return renderables;
    }

    public abstract void setCog(double cog);

    @Override
    public abstract Object getClone();

}
