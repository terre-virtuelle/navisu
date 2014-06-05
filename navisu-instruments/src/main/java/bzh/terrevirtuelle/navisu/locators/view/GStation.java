package bzh.terrevirtuelle.navisu.locators.view;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.locators.model.TStation;
import bzh.terrevirtuelle.navisu.locators.view.impl.Shape_4;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
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
    protected Shape_4 shape;

    public GStation(int id, TStation tStation) {
        this.id = id;
        this.tStation = tStation;
        tStation.setGStation(this);
        shape = new Shape_4(Position.fromDegrees(tStation.getLatitude(), tStation.getLongitude()));
        shape.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
        attrs.setImageOffset(new Offset(24d, 0d, AVKey.PIXELS, AVKey.PIXELS));
        shape.setClipToHorizon(true);
        attrs.setImageAddress("img/emetteur_1.png");
        shape.setAttributes(attrs);
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

    public PointPlacemarkAttributes getAttributes() {
        return shape.getAttributes();
    }
}
