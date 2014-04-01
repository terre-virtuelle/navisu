package bzh.terrevirtuelle.navisu.app.guiagent.geoview;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObjectCUDProcessor;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.LayerManager;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWindView;
import gov.nasa.worldwind.layers.Layer;
import javafx.scene.Node;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */
public interface GeoViewServices extends ComponentService {

    LayerManager<Layer> getLayerManager();
    Display<Node> getDisplayService();

    void registerProcessor(final GObjectCUDProcessor processor);
 
}
