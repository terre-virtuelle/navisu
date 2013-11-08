package bzh.terrevirtuelle.navisu.app.guiagent.menu;

import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */
public interface MenuBarServices extends ComponentService {

    void createMenu(String name);

    void addMenuItem(String menu, MenuItem item);

    Display<Node> getDisplayService();
}
