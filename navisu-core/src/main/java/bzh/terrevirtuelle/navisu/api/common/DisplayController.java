package bzh.terrevirtuelle.navisu.api.common;

import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.scene.layout.Region;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 19:59
 */
public interface DisplayController<V extends Display<Region>> {

    V getView();
}
