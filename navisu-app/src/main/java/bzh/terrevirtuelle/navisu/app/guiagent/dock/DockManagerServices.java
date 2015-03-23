package bzh.terrevirtuelle.navisu.app.guiagent.dock;

import bzh.terrevirtuelle.navisu.widgets.dock.Dock;
import bzh.terrevirtuelle.navisu.widgets.dock.DockItem;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 22 mars 2015
 * @author Serge Morvan
 */ 
public interface DockManagerServices extends ComponentService {

    void setDockComponent(Dock dock);

    void createMenu(String menuKey, String menuName);

    void addDockItem(String menuKey, String menuName, DockItem item);
    void addDockItem(DefaultDockEnum menu, DockItem item);
}
