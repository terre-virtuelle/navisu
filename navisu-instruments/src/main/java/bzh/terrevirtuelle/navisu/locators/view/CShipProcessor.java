package bzh.terrevirtuelle.navisu.locators.view;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObjectCUDProcessor;
import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
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

    public CShipProcessor(GeoLayer<Layer> layer) {
        super(layer);
    }

    @Override
    public GObject processCreated(int id, TObject input) {

        tShip = (TShip) input;
      
        GCShip gShip = new GCShip(id, tShip, 100);
        gShip.setPathAttrs(this.makeAttributes());

        return gShip;
    }

}
