package bzh.terrevirtuelle.navisu.app.guiagent.menu.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuBar;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuBarServices;
import bzh.terrevirtuelle.navisu.core.utility.Checker;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.capcaval.c3.component.ComponentState;

import java.util.HashMap;
import java.util.Map;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */
public class MenuBarImpl implements MenuBar, MenuBarServices, ComponentState {

    protected javafx.scene.control.MenuBar menuBar;

    protected Map<String, Menu> menuMap;

    @Override
    public void componentInitiated() {

        this.menuBar = new javafx.scene.control.MenuBar();

        this.menuBar.setUseSystemMenuBar(true);

        this.menuMap = new HashMap<>();
    }

    @Override
    public void createMenu(String menuName) {

        Checker.keyNotExistsInMap(this.menuMap, menuName, "Menu \"" + menuName + "\" already exists in the menuBar bar.");

        // Create the menuBar
        Menu menu = new Menu(menuName);

        // Hold the menuBar in the map
        this.menuMap.put(menuName, menu);

        // Add it to the menuBar bar
        this.menuBar.getMenus().add(menu);
    }

    @Override
    public void addMenuItem(String menuName, MenuItem item) {

        Checker.keyExistsInMap(this.menuMap, menuName, "Menu \"" + menuName + "\" does not exists in the menuBar bar. Please, create it before.");

        // Get the menu
        Menu menu = this.menuMap.get(menuName);

        // Add the menu item
        menu.getItems().add(item);
    }

    @Override
    public Display<Node> getDisplayService() {
        return Display.factory.newDisplayNode(this.menuBar);
    }

    @Override
    public void componentStarted() {}

    @Override
    public void componentStopped() {}
}
