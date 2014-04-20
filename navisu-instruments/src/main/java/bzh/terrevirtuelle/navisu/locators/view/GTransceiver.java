package bzh.terrevirtuelle.navisu.locators.view;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.locators.model.TTransceiver;
import bzh.terrevirtuelle.navisu.locators.view.impl.Shape_4;
import gov.nasa.worldwind.render.Renderable;

/**
 * NaVisu
 *
 * @author Serge
 * @date 01/04/2014 18:49
 */
public class GTransceiver
        implements GObject {

    protected final int id;
    protected TTransceiver tStation;
    protected Shape_4 shape;

    public GTransceiver(int id, TTransceiver tStation) {
        this.id = id;
        this.tStation = tStation;
        tStation.setGTransceiver(this);
   //     shape = new Shape_1(Position.fromDegrees(tStation.getLatitude(), tStation.getLongitude()));
   
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

    public Shape_4 getShape() {
        return shape;
    }

}
