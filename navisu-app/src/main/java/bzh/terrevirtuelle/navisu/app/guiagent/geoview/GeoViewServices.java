package bzh.terrevirtuelle.navisu.app.guiagent.geoview;

import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.scene.Node;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */
public interface GeoViewServices extends ComponentService {

    Display<Node> getDisplayService();
}
