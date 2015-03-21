package bzh.terrevirtuelle.navisu.app.guiagent.dock;

import bzh.terrevirtuelle.navisu.app.guiagent.menu.*;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */
public interface DockManagerServices extends ComponentService {

    void setMenuComponent(MenuBar menu);

    void createMenu(String menuKey, String menuName);

    void addMenuItem(String menuKey, String menuName, MenuItem item);
    void addMenuItem(DefaultMenuEnum menu, MenuItem item);
}
