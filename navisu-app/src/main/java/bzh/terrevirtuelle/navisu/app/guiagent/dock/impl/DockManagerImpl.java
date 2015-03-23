package bzh.terrevirtuelle.navisu.app.guiagent.dock.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.dock.DefaultDockEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.dock.DockManager;
import bzh.terrevirtuelle.navisu.app.guiagent.dock.DockManagerServices;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import org.capcaval.c3.component.ComponentState;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import  bzh.terrevirtuelle.navisu.widgets.dock.Dock;
import bzh.terrevirtuelle.navisu.widgets.dock.DockItem;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */
public class DockManagerImpl implements DockManager, DockManagerServices, ComponentState {

    protected static final Logger LOGGER = Logger.getLogger(DockManagerImpl.class.getName());

    protected MenuBar menuBar;
    protected Map<String, Menu> menuMap;

    @Override
    public void componentInitiated() {

        // instance menu map
        this.menuMap = new HashMap<>();
    }

    @Override
    public void setDockComponent(Dock dock) {
/*
        this.menuBar = menu;

        if(this.menuBar.getMenus().size() > 0) {
            this.menuBar.getMenus().clear();
        }

        for(DefaultMenuEnum defaultMenu : DefaultMenuEnum.getAll()) {

            String key = defaultMenu.getKey();
             this.createMenu(key, Translator.tr(key));
        }
        */
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
          //  LOGGER.log(Level.WARNING, "Menu with key \"{0}\" already exists.", menuKey);
        }
    }
/*
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
*/
    @Override
    public void componentStarted() {}

    @Override
    public void componentStopped() {}

    @Override
    public void addDockItem(String menuKey, String menuName, DockItem item) {
    }

    @Override
    public void addDockItem(DefaultDockEnum menu, DockItem item) {
   }
}
