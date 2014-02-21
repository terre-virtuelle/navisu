/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.ais.view;

import bzh.terrevirtuelle.navisu.locators.view.ShipTypeColor;
import bzh.terrevirtuelle.navisu.ship.Ship;
import bzh.terrevirtuelle.navisu.locators.ais.view.impl.ShipDefaultViewImpl;
import bzh.terrevirtuelle.navisu.locators.ais.view.impl.ShipViewImpl;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Serge
 */
public class ShipViewFactory {

    private final double latitude;
    private final double longitude;
    private double cog;
    private final int type;
    private double[] shipShape;
    private ShapeAttributes shapeAttributes;
    private final int aisType;

    public ShipViewFactory(Ship ship, int aisType) {
        this.latitude = ship.getLatitude();
        this.longitude = ship.getLongitude();
        this.cog = ship.getCog();
        this.type = ship.getType();
        this.aisType = aisType;
    }

    public ShipView buildDefault() {
        ShipView shipView = new ShipDefaultViewImpl(new LatLon(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude)), 50.0);
        shipView.setAttributes(makeAttributes());
        //  shipView.setEnableBatchPicking(true);
        return shipView;
    }

    public ShipView build() {
        initTriangleShape();
        ShipView shipView = new ShipViewImpl(makePositionList(shipShape), latitude, longitude, cog);
        shipView.setAttributes(makeAttributes());
        return shipView;
    }

    private void initTriangleShape() {
        shipShape = new double[6];
        shipShape[0] = longitude;
        shipShape[1] = latitude + 0.0015;
        shipShape[2] = longitude + .001;
        shipShape[3] = latitude - .0015;
        shipShape[4] = longitude - .001;
        shipShape[5] = latitude - .0015;
    }

    private void initDefaultShape() {
        shipShape = new double[2];
        shipShape[0] = longitude;
        shipShape[1] = latitude;
        shipShape[2] = longitude;
        shipShape[3] = latitude;
    }

    private void initSquareShape() {
        shipShape = new double[10];
        shipShape[0] = longitude - .001;
        shipShape[1] = latitude + .0005;

        shipShape[2] = longitude + .001;
        shipShape[3] = latitude + .0005;

        shipShape[4] = longitude + .001;
        shipShape[5] = latitude - .0005;

        shipShape[6] = longitude - .001;
        shipShape[7] = latitude - .0005;

        shipShape[8] = longitude - .001;
        shipShape[9] = latitude - .0005;
    }

    private ShapeAttributes makeAttributes() {
        shapeAttributes = new BasicShapeAttributes();
        if (aisType != 4) {
            shapeAttributes.setOutlineMaterial(ShipTypeColor.VIEW.get(type));
            shapeAttributes.setInteriorMaterial(ShipTypeColor.VIEW.get(type));
        }
        if (aisType == 4) {
            shapeAttributes.setOutlineMaterial(Material.RED);
            shapeAttributes.setInteriorMaterial(Material.RED);
        }

        shapeAttributes.setOutlineOpacity(0.8);
        shapeAttributes.setOutlineWidth(1);
        shapeAttributes.setDrawInterior(true);
        shapeAttributes.setInteriorOpacity(1.0);
        return shapeAttributes;
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
}
