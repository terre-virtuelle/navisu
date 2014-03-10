package bzh.terrevirtuelle.navisu.locators.view;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceCircle;

import java.util.List;

/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 18:49
 */
public class GPShip extends GShip
        implements GObject {

    protected Polygon polygon;

    public GPShip(int id, TShip ship, Polygon polygon) {
        super(id, ship);
        this.polygon = polygon;
    }

    public GPShip(int id, TShip ship, List<Position> positions) {
        super(id, ship);
        this.polygon = new Polygon(positions);
        this.polygon.setEnableBatchPicking(true);
        this.polygon.moveTo(new Position(Angle.fromDegrees(ship.getLatitude()),
                Angle.fromDegrees(ship.getLongitude()),
                1000.0));
        this.polygon.setRotation(-ship.getCog());
    }

    

    @Override
    public void setLocation(Location location) {
        this.polygon.moveTo(Position.fromDegrees(location.getLatitudeDegree(),
                location.getLongitudeDegree(), 100));
    }

    @Override
    public Renderable[] getRenderables() {
        return new Renderable[]{
            this.polygon};
    }

    @Override
    public void setCog(double cog) {
        this.polygon.setRotation(-cog);
    }

    public void setPathAttrs(ShapeAttributes pathAttrs) {
        this.polygon.setAttributes(pathAttrs);
    }

    @Override
    public Object getClone() {

        GPShip clone = new GPShip(this.id, this.ship, this.polygon);

        return clone;
    }
}
