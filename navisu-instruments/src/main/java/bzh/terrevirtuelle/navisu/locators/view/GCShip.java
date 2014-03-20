package bzh.terrevirtuelle.navisu.locators.view;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceCircle;

/**
 * NaVisu
 *
 * @author serge
 * @date 9/04/2014 18:49
 */
public class GCShip extends GShip
        implements GObject {

    protected SurfaceCircle circle;
    
    public GCShip(int id, TShip ship, SurfaceCircle circle) {
        super(id, ship);
        this.circle = circle;
    }

    public GCShip(int id, TShip ship, double radius) {
        super(id, ship);
        circle = new SurfaceCircle(new LatLon(Angle.fromDegrees(ship.getLatitude()),
                Angle.fromDegrees(ship.getLongitude())), radius);
        this.circle.setEnableBatchPicking(true);
        this.circle.moveTo(new Position(Angle.fromDegrees(ship.getLatitude()),
                Angle.fromDegrees(ship.getLongitude()),
                100.0));
    }

    @Override
    public void setLocation(Location location) {
        this.circle.moveTo(new Position(Angle.fromDegrees(location.getLatitudeDegree()),
                Angle.fromDegrees(location.getLongitudeDegree()), 100));
      //  System.out.println(getID()+"  " + location.getLatitudeDegree()
      //  + "  " + location.getLongitudeDegree());
    }

    @Override
    public Renderable[] getRenderables() {
        return new Renderable[]{
            this.circle};
    }

    @Override
    public void setCog(double cog) {
        //nothing to do
    }

    public void setPathAttrs(ShapeAttributes pathAttrs) {
        this.circle.setAttributes(pathAttrs);
    }

    @Override
    public Object getClone() {
        GCShip clone = new GCShip(this.id, this.ship, this.circle);
        return clone;
    }
}
