package bzh.terrevirtuelle.navisu.locators.view;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import bzh.terrevirtuelle.navisu.locators.view.impl.Shape_0;
import bzh.terrevirtuelle.navisu.locators.view.impl.Shape_1;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.Arrays;
import java.util.List;

/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 18:49
 */
public class GShip
        implements GObject {

    protected final int id;
    protected TShip tShip;
    protected Shape shape;

    public GShip(int id, TShip ship) {
        this.id = id;
        this.tShip = ship;
        if (tShip.getShapeId() == 0) {
            shape = new Shape_0(tShip,makeAttributes(),
                    makePositionList(initShape(tShip.getLatitude(), tShip.getLongitude())));
        } else {
            shape = new Shape_1(tShip, makeAttributes(),
                    new LatLon(Angle.fromDegrees(tShip.getLatitude()), Angle.fromDegrees(tShip.getLongitude())),
                    40.0);
        }
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

    protected final ShapeAttributes makeAttributes() {
        final ShapeAttributes pathAttrs = new BasicShapeAttributes();
        pathAttrs.setOutlineMaterial(Material.BLACK);
        pathAttrs.setOutlineOpacity(0.8);
        pathAttrs.setOutlineWidth(1);
        pathAttrs.setInteriorMaterial(ShipTypeColor.VIEW.get(tShip.getType()));
        pathAttrs.setDrawInterior(true);
        pathAttrs.setInteriorOpacity(1.0);
        return pathAttrs;
    }

    private double[] initShape(double latitude, double longitude) {
        double[] shipShape = new double[6];
        shipShape[0] = longitude;
        shipShape[1] = latitude + 0.00075;
        shipShape[2] = longitude + 0.0005;
        shipShape[3] = latitude - 0.00075;
        shipShape[4] = longitude - 0.0005;
        shipShape[5] = latitude - 0.00075;
        return shipShape;
    }

    protected final List<Position> makePositionList(double[] src) {
        int numCoords = src.length / 2;
        Position[] array = new Position[numCoords];

        for (int i = 0; i < numCoords; i++) {
            double lonDegrees = src[2 * i];
            double latDegrees = src[2 * i + 1];
            array[i] = Position.fromDegrees(latDegrees, lonDegrees, 100);
        }
        return Arrays.asList(array);
    }

    @Override
    public Object getClone() {
        return this;
    }

    @Override
    public String toString() {
        return "GShip{" + "id=" + id + ", tShip=" + tShip + ", shape=" + shape + '}';
    }
}
