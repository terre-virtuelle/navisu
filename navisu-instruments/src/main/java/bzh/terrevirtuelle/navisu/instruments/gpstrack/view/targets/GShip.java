package bzh.terrevirtuelle.navisu.instruments.gpstrack.view.targets;

import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.view.targets.impl.Shape3D_0;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.view.targets.impl.Shape_1;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.view.targets.impl.Shape_0;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
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
public class GShip {

    protected Ship ship;
    protected Shape shape;

    public GShip(Ship ship) {

        this.ship = ship;
        switch (ship.getNavigationalStatus()) {

            case 0:
                shape = new Shape_0(this.ship, makeAttributes(),
                        new LatLon(Angle.fromDegrees(this.ship.getLatitude()), Angle.fromDegrees(this.ship.getLongitude())),
                        40.0);
                break;
            case 1:
                shape = new Shape_1(this.ship, makeAttributes(),
                        makePositionList(initShape(this.ship.getLatitude(), this.ship.getLongitude())));
                break;
            case 36:
                shape = new Shape3D_0(this.ship, "data/collada/sail01.dae",
                        new LatLon(Angle.fromDegrees(this.ship.getLatitude()), Angle.fromDegrees(this.ship.getLongitude())),
                        40.0);
                break;
            default:
                shape = new Shape_0(this.ship, makeAttributes(),
                        new LatLon(Angle.fromDegrees(this.ship.getLatitude()), Angle.fromDegrees(this.ship.getLongitude())),
                        40.0);
        }
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
    public Ship getShip() {
        return ship;
    }

    /**
     * Set the value of ship
     *
     * @param ship new value of ship
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public ShapeAttributes getAttributes() {
        return shape.getAttributes();
    }

    public Renderable[] getRenderables() {
        return shape.getRenderables();
    }

    public void setCog(double cog) {
        shape.setRotation(cog);
    }

    public void update(Ship ship) {
      //  System.out.print(ship.getShipType()+ "   ");
     //   System.out.println("gShip "+this);
        shape.setPosition(new Position(Angle.fromDegrees(ship.getLatitude()),
                Angle.fromDegrees(ship.getLongitude()), 15));
        shape.setRotation(ship.getCog());
        ShapeAttributes pathAttrs = shape.getAttributes();
        System.out.println("2 "+ShipTypeColor.MATERIAL.get(ship.getShipType())+" "+ship.getShipType());
        pathAttrs.setOutlineMaterial(ShipTypeColor.MATERIAL.get(ship.getShipType()));
        
        pathAttrs.setInteriorMaterial(ShipTypeColor.MATERIAL.get(ship.getShipType()));
      //  System.out.println(ShipTypeColor.MATERIAL.get(ship.getShipType()));
     //   pathAttrs.setInteriorMaterial(Material.BLUE);
     //   System.out.println("pathAttrs "+ pathAttrs.getInteriorMaterial());
      //  shape.setAttributes(makeAttributes());
    }

    protected final ShapeAttributes makeAttributes() {
        final ShapeAttributes pathAttrs = new BasicShapeAttributes();
        System.out.println("3 "+ShipTypeColor.MATERIAL.get(ship.getShipType())+" "+ship.getShipType());
        pathAttrs.setOutlineMaterial(ShipTypeColor.MATERIAL.get(ship.getShipType()));
        pathAttrs.setOutlineOpacity(0.8);
        pathAttrs.setOutlineWidth(1);
        pathAttrs.setInteriorMaterial(ShipTypeColor.MATERIAL.get(ship.getShipType()));
        pathAttrs.setDrawInterior(true);
        pathAttrs.setInteriorOpacity(1.0);
        return pathAttrs;
    }

    private double[] initShape(double latitude, double longitude) {

        double[] shipShape = new double[10];
        shipShape[0] = longitude;
        shipShape[1] = latitude + 0.00150;
        shipShape[2] = longitude;
        shipShape[3] = latitude + 0.00075;
        shipShape[4] = longitude + 0.0005;
        shipShape[5] = latitude - 0.00075;
        shipShape[6] = longitude - 0.0005;
        shipShape[7] = latitude - 0.00075;
        shipShape[8] = longitude;
        shipShape[9] = latitude + 0.00075;
        return shipShape;
    }

    protected final List<Position> makePositionList(double[] src) {
        int numCoords = src.length / 2;
        Position[] array = new Position[numCoords];

        for (int i = 0; i < numCoords; i++) {
            double lonDegrees = src[2 * i];
            double latDegrees = src[2 * i + 1];
            array[i] = Position.fromDegrees(latDegrees, lonDegrees, 15);
        }
        return Arrays.asList(array);
    }

    @Override
    public String toString() {
        return "GShip{" + ", Ship=" + ship + ", shape=" + shape + '}';
    }
}
