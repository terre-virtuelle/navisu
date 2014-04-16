package bzh.terrevirtuelle.navisu.locators.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObjectCUDProcessor;
import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import bzh.terrevirtuelle.navisu.locators.view.GShip;
import bzh.terrevirtuelle.navisu.locators.view.ShipTypeColor;
import gov.nasa.worldwind.layers.Layer;

/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 19:13
 */
public class ShipProcessor
        implements GObjectCUDProcessor {

    protected final GeoLayer<Layer> layer;
    protected TShip tShip;
    protected GShip gShip;

    public ShipProcessor(GeoLayer<Layer> layer) {
        this.layer = layer;
    }

    public ShipProcessor(GeoLayer<Layer> layer, TShip tShip) {
        this.layer = layer;
        this.tShip = tShip;
    }

    @Override
    public GObject processCreated(int id, TObject input) {

        tShip = (TShip) input;
        gShip = new GShip(id, tShip);
        return gShip;
    }

    @Override
    public GObject processUpdated(int id, TObject input, GObject output) {
        tShip = (TShip) input;
        gShip = (GShip) output;

        gShip.setLocation(tShip.getLocation());
        gShip.setCog(tShip.getOrientation().getOrientationDegree());
        gShip.getAttributes().setInteriorMaterial(ShipTypeColor.VIEW.get(tShip.getType()));
        gShip.getAttributes().setOutlineMaterial(ShipTypeColor.VIEW.get(tShip.getType()));
        return output;
    }

    @Override
    public GObject processDeleted(int id, TObject input, GObject output) {
        // Nothing to do here
        return output;
    }

    @Override
    public GeoLayer<Layer> getLayer() {
        return this.layer;
    }

    @Override
    public Class<? extends TObject> getType() {
        return TShip.class;
    }
/*
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
        shipShape[2] = longitude + .0005;
        shipShape[3] = latitude - .00075;
        shipShape[4] = longitude - .0005;
        shipShape[5] = latitude - .00075;
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
    */
}
