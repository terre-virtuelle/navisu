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
        gShip.getAttributes().setInteriorMaterial(ShipTypeColor.MATERIAL.get(tShip.getShipType()));
        gShip.getAttributes().setOutlineMaterial(ShipTypeColor.MATERIAL.get(tShip.getShipType()));
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
}
