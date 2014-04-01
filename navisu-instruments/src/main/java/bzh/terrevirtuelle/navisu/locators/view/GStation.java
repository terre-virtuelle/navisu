package bzh.terrevirtuelle.navisu.locators.view;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.locators.model.TStation;
import gov.nasa.worldwind.render.Renderable;

/**
 * NaVisu
 *
 * @author Serge
 * @date 01/04/2014 18:49
 */
public class GStation
        implements GObject {

    protected final int id;
    protected TStation tStation;
    protected Shape shape;

    public GStation(int id, TStation tStation) {
        this.id = id;
        this.tStation = tStation;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setLocation(Location location) {
        shape.setLocation(location);
    }

    @Override
    public Renderable[] getRenderables() {
        return shape.getRenderables();
    }

    @Override
    public Object getClone() {
        return this;
    }

    @Override
    public String toString() {
        return "GStation{" + "id=" + id + ", tStation=" + tStation + ", shape=" + shape + '}';
    }

}
