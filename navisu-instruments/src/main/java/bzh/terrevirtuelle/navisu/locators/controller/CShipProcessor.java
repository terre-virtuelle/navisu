package bzh.terrevirtuelle.navisu.locators.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObjectCUDProcessor;
import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import bzh.terrevirtuelle.navisu.locators.view.GCShip;
import bzh.terrevirtuelle.navisu.locators.view.GPShip;
import gov.nasa.worldwind.layers.Layer;

/**
 * NaVisu
 *
 * @author Serge
 * @date 19/02/2014 19:13
 */
public class CShipProcessor
        extends ShipProcessor
        implements GObjectCUDProcessor {

    GCShip gShip;

    public CShipProcessor(GeoLayer<Layer> layer) {
        super(layer);
    }

    public CShipProcessor(GeoLayer<Layer> layer, TShip tShip) {
        super(layer, tShip);
    }

    @Override
    public GObject processCreated(int id, TObject input) {

        tShip = (TShip) input;

        gShip = new GCShip(id, tShip, 20);
        gShip.setPathAttrs(this.makeAttributes());

        return gShip;
    }

}
