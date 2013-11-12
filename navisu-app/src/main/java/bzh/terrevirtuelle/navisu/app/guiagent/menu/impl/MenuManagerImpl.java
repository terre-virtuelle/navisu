package bzh.terrevirtuelle.navisu.app.guiagent.menu.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.menu.DefaultMenuEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManager;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.capcaval.c3.component.ComponentState;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator.tr;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */
public class MenuManagerImpl implements MenuManager, MenuManagerServices, ComponentState {

    protected static final Logger LOGGER = Logger.getLogger(MenuManagerImpl.class.getName());

    protected MenuBar menuBar;
    protected Map<String, Menu> menuMap;

    @Override
    public void componentInitiated() {

        this.menuBar = new MenuBar();
        this.menuBar.setUseSystemMenuBar(true);

        this.menuMap = new HashMap<>();

        for(DefaultMenuEnum defaultMenu : DefaultMenuEnum.getAll()) {

            String key = defaultMenu.getKey();
            this.createMenu(key, tr(key));
        }
    }

    @Override
    public void createMenu(String menuKey, String menuName) {

        if(!this.menuMap.containsKey(menuKey)) {

            // Create the menuBar
            Menu menu = new Menu(menuName);

            // Hold the menuBar in the map
            this.menuMap.put(menuKey, menu);

            // Add it to the menuBar bar
            this.menuBar.getMenus().add(menu);
        }
        else {
            LOGGER.warning("Menu with key \"" + menuKey + "\" already exists.");
        }
    }

    @Override
    public void addMenuItem(String menuKey, String menuName, MenuItem item) {

        if(!this.menuMap.containsKey(menuKey)) {
            LOGGER.info("Menu with key \"" + menuKey + "\" does not exists. It will be created.");
            this.createMenu(menuKey, menuName);
        }

        // Get the menu
        Menu menu = this.menuMap.get(menuKey);

        // Add the menu item
        menu.getItems().add(item);
    }

    @Override
    public void addMenuItem(DefaultMenuEnum defMenu, MenuItem item) {

        Menu menu = this.menuMap.get(defMenu.getKey());
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
