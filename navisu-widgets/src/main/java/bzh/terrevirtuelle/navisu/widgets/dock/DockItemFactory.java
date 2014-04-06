package bzh.terrevirtuelle.navisu.widgets.dock;


import bzh.terrevirtuelle.navisu.widgets.dock.impl.DockItemImageImpl;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * NaVisu
 *
 * @author tibus
 * @date 10/03/2014 18:32
 */
public interface DockItemFactory {

    public static DockItem newImageItem(String name, String iconPath, EventHandler<MouseEvent> callback) {
        return new DockItemImageImpl(name, iconPath, callback);
    }
}
