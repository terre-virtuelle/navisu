package bzh.terrevirtuelle.navisu.app.guiagent.menu.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.menu.DefaultMenuEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManager;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.capcaval.c3.component.ComponentState;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import  bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import java.util.logging.Level;

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

        // instance menu map
        this.menuMap = new HashMap<>();
    }

    @Override
    public void setMenuComponent(MenuBar menu) {

        this.menuBar = menu;

        if(this.menuBar.getMenus().size() > 0) {
            this.menuBar.getMenus().clear();
        }

        for(DefaultMenuEnum defaultMenu : DefaultMenuEnum.getAll()) {

            String key = defaultMenu.getKey();
             this.createMenu(key, Translator.tr(key));
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
            LOGGER.log(Level.WARNING, "Menu with key \"{0}\" already exists.", menuKey);
        }
    }

    @Override
    public void addMenuItem(String menuKey, String menuName, MenuItem item) {

        if(!this.menuMap.containsKey(menuKey)) {
            LOGGER.log(Level.INFO, "Menu with key \"{0}\" does not exists. It will be created.", menuKey);
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
    public void componentStarted() {}

    @Override
    public void componentStopped() {}
}
