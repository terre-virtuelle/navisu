package bzh.terrevirtuelle.navisu.locators.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObjectCUDProcessor;
import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import bzh.terrevirtuelle.navisu.locators.view.GPShip;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;

import java.util.Arrays;
import java.util.List;

/**
 * NaVisu
 *
 * @author Serge
 * @date 19/02/2014 19:13
 */
public class PShipProcessor
        extends ShipProcessor
        implements GObjectCUDProcessor {

    GPShip gShip;

    public PShipProcessor(GeoLayer<Layer> layer) {
        super(layer);
    }

    public PShipProcessor(GeoLayer<Layer> layer, TShip tShip) {
        super(layer, tShip);
    }

    @Override
    public GObject processCreated(int id, TObject input) {

        tShip = (TShip) input;

        List<Position> bounds = this.makePositionList(this.initShape(tShip.getLatitude(), tShip.getLongitude()));

        gShip = new GPShip(id, tShip, bounds);
        gShip.setPathAttrs(this.makeAttributes());

        return gShip;
    }

    private double[] initShape(double latitude, double longitude) {
        double[] shipShape = new double[6];
        shipShape[0] = longitude;
        shipShape[1] = latitude + 0.0015;
        shipShape[2] = longitude + .001;
        shipShape[3] = latitude - .0015;
        shipShape[4] = longitude - .001;
        shipShape[5] = latitude - .0015;
        return shipShape;
    }

    protected List<Position> makePositionList(double[] src) {
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
