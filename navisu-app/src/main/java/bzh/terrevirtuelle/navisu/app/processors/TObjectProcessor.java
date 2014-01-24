package bzh.terrevirtuelle.navisu.app.processors;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObjectCUDProcessor;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.impl.GObjectImpl;
import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;

/**
 * NaVisu
 *
 * @author tibus
 * @date 05/01/2014 20:25
 */
public class TObjectProcessor implements GObjectCUDProcessor {

    protected RenderableLayer layer = new RenderableLayer();

    @Override
    public GeoLayer<Layer> getLayer() {
        return GeoLayer.factory.newWorldWindGeoLayer(this.layer);
    }

    @Override
    public GObject processCreated(int id, TObject input) {

        final GObject gObject = new GObjectImpl(
                id,
                input.getLocation().getLatitudeDegree(),
                input.getLocation().getLongitudeDegree()
        );

        return gObject;
    }

    @Override
    public GObject processUpdated(int id, TObject input, GObject output) {

        // Very simple...
        output.setLocation(input.getLocation());

        return output;
    }

    @Override
    public GObject processDeleted(int id, TObject input, GObject output) {

        // Nothing to do...

        return output;
    }

    @Override
    public Class<? extends TObject> getType() {
        return TObject.class;
    }
}
