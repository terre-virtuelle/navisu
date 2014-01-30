/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.world.ais.locator.view;

import bzh.terrevirtuelle.navisu.ship.Ship;
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
    private final double cog;
    private double[] shipShape;
    private ShapeAttributes pathAttrs;

    public ShipViewFactory(Ship ship) {
        this.latitude = ship.getLatitude();
        this.longitude = ship.getLongitude();
        this.cog = ship.getCog();
    }

    public ShipView build() {
        initShape();
        makeAttributes();
        ShipView shipView = new ShipView(makePositionList(shipShape), latitude, longitude, cog);
        shipView.setAttributes(pathAttrs);
        shipView.setEnableBatchPicking(true);
        return shipView;
    }

    private void initShape() {
        shipShape = new double[6];
        shipShape[0] = longitude;
        shipShape[1] = latitude + 0.0015;
        shipShape[2] = longitude + .001;
        shipShape[3] = latitude - .0015;
        shipShape[4] = longitude - .001;
        shipShape[5] = latitude - .0015;
    }

    private void makeAttributes() {
        pathAttrs = new BasicShapeAttributes();
        pathAttrs.setOutlineMaterial(Material.BLACK);
        pathAttrs.setOutlineOpacity(0.8);
        pathAttrs.setOutlineWidth(1);
        pathAttrs.setInteriorMaterial(Material.GREEN);
        pathAttrs.setDrawInterior(true);
        pathAttrs.setInteriorOpacity(1.0);
        
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
