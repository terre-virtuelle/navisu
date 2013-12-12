package bzh.terrevirtuelle.navisu.api.checktree;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * NaVisu
 *
 * @author tibus
 * @date 10/12/2013 21:08
 */
public class CheckTreeCell<T extends CheckTreeItem> extends TreeCell<T> {

    protected HBox container;

    protected CheckBox checkBox;
    protected ImageView icon;

    protected ContextMenu ctxMenu = new ContextMenu();

    protected boolean firstTime = true;

    protected CheckTreeItem item;

    public CheckTreeCell() {

        this.container = new HBox();

        this.checkBox = new CheckBox();
        this.checkBox.setOnAction((e) -> {
            this.item.setSelected(this.checkBox.isSelected());
        });
        this.icon = new ImageView();

        this.container.getChildren().addAll(this.checkBox, this.icon);
    }

    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if(!empty) {

            if(firstTime) {
                this.item = item;
                firstTime = false;

                item.getActions().forEach((action) -> {
                    MenuItem menuItem = new MenuItem(action.name());
                    menuItem.setOnAction((e) -> action.callback().on());
                    this.ctxMenu.getItems().addAll(menuItem);
                });
            }

            this.setText(item.text());
            this.checkBox.setSelected(item.selected());

            setGraphic(this.container);

            setContextMenu(this.ctxMenu);
        }
    }
}
