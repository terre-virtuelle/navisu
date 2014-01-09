package bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.Renderable;

import javax.swing.*;

/**
 * NaVisu
 *
 * @author tibus
 * @date 05/01/2014 20:15
 */
public class GObjectImpl implements GObject {

    protected final int id;
    protected PointPlacemark placemark;

    public GObjectImpl(int id, double lat, double lon) {
        this.id = id;
        this.placemark = new PointPlacemark(Position.fromDegrees(lat, lon));
    }

    protected GObjectImpl(int id, PointPlacemark placemark) {
        this.id = id;
        this.placemark = placemark;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setLocation(Location location) {

        SwingUtilities.invokeLater(() ->
            this.placemark.moveTo(Position.fromDegrees(location.getLatitudeDegree(), location.getLongitudeDegree()))
        );
    }

    @Override
    public Renderable[] getRenderables() {
        return new Renderable[] { this.placemark };
    }

    @Override
    public Object getClone() {
        return new GObjectImpl(this.id, this.placemark);
    }
}
