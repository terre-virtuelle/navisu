package bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject;

import bzh.terrevirtuelle.navisu.core.model.processor.TObjectCUDProcessor;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import gov.nasa.worldwind.layers.Layer;

/**
 * NaVisu
 *
 * @author tibus
 * @date 05/01/2014 19:50
 */
public interface GObjectCUDProcessor extends TObjectCUDProcessor<GObject> {

    GeoLayer<Layer> getLayer();
}
