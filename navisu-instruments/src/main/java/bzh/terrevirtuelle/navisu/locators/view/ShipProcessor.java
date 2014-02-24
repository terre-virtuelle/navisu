package bzh.terrevirtuelle.navisu.locators.view;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObjectCUDProcessor;
import bzh.terrevirtuelle.navisu.core.model.iobject.TObject;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;

import java.util.Arrays;
import java.util.List;

/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 19:13
 */
public class ShipProcessor
        implements GObjectCUDProcessor {

    protected final GeoLayer<Layer> layer;

    public ShipProcessor(GeoLayer<Layer> layer) {
        this.layer = layer;
    }

    @Override
    public GObject processCreated(int id, TObject input) {

        TShip tShip = (TShip) input;

        double lat = tShip.getLatitude();
        double lon = tShip.getLongitude();

        Position position = Position.fromDegrees(lat, lon);
        List<Position> positions = this.makePositionList(this.initTriangleShape(lat, lon));

        GShip gShip = new GShip(id, positions, position, tShip.getCog());
        gShip.setPathAttrs(this.makeAttributes());

        return gShip;
    }

    @Override
    public GObject processUpdated(int id, TObject input, GObject output) {

        GShip gShip = (GShip) output;

        gShip.setLocation(input.getLocation());
//gShip.setCog(input.get);
        return output;
    }

    @Override
    public GObject processDeleted(int id, TObject input, GObject output) {
        // Nothing to do here
        return output;
    }

    private ShapeAttributes makeAttributes() {
        final ShapeAttributes pathAttrs = new BasicShapeAttributes();
        pathAttrs.setOutlineMaterial(Material.BLACK);
        pathAttrs.setOutlineOpacity(0.8);
        pathAttrs.setOutlineWidth(1);
        pathAttrs.setInteriorMaterial(Material.GREEN);
        pathAttrs.setDrawInterior(true);
        pathAttrs.setInteriorOpacity(1.0);
        return pathAttrs;
    }

    private double[] initTriangleShape(double latitude, double longitude) {
        double[] shipShape = new double[6];
        shipShape[0] = longitude;
        shipShape[1] = latitude + 0.0015;
        shipShape[2] = longitude + .001;
        shipShape[3] = latitude - .0015;
        shipShape[4] = longitude - .001;
        shipShape[5] = latitude - .0015;
        return shipShape;
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
    public GeoLayer<Layer> getLayer() {
        return this.layer;
    }

    @Override
    public Class<? extends TObject> getType() {
        return TShip.class;
    }
}
