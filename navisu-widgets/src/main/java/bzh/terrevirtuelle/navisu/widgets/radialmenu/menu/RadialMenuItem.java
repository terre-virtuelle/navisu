package bzh.terrevirtuelle.navisu.widgets.radialmenu.menu;

import bzh.terrevirtuelle.navisu.widgets.radialmenu.item.RadialItem;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


/**
 * NaVisu
 *
 * @author Jordan Mens
 */
public class RadialMenuItem extends RadialItem {

    private ObjectProperty<RadialItem> parentItem;

    public RadialMenuItem() {}

    public RadialMenuItem(EventHandler<MouseEvent> callback) {
        super(callback);
    }


    public final ObjectProperty<RadialItem> parentItemProperty() {
        if (parentItem == null) {
            parentItem = new SimpleObjectProperty<>(this, "parentItem");
        }
        return parentItem;
    }

    public final RadialItem getParentItem() {
        return parentItemProperty().get();
    }

    public final void setParentItem(RadialMenuContainer radialItem) {
        this.parentItemProperty().set(radialItem);
    }
}
