package bzh.terrevirtuelle.navisu.locators.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObjectCUDProcessor;
import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.locators.model.TStation;
import bzh.terrevirtuelle.navisu.locators.view.GStation;
import gov.nasa.worldwind.layers.Layer;

/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 19:13
 */
public class StationProcessor
        implements GObjectCUDProcessor {

    protected final GeoLayer<Layer> layer;
    protected TStation tStation;
    protected GStation gStation;

    public StationProcessor(GeoLayer<Layer> layer) {
        this.layer = layer;
    }

    public StationProcessor(GeoLayer<Layer> layer, TStation tStation) {
        this.layer = layer;
        this.tStation = tStation;
    }

    @Override
    public GObject processCreated(int id, TObject input) {

        tStation = (TStation) input;
        gStation = new GStation(id, tStation);
        return gStation;
    }

    @Override
    public GObject processUpdated(int id, TObject input, GObject output) {
        tStation = (TStation) input;
        gStation = (GStation) output;
        gStation.setLocation(tStation.getLocation());
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
        return TStation.class;
    }

}
