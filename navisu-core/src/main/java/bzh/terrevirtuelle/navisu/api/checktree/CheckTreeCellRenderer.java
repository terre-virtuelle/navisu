package bzh.terrevirtuelle.navisu.api.checktree;

import bzh.terrevirtuelle.navisu.api.checktree.model.CheckTreeItemModel;
import bzh.terrevirtuelle.navisu.api.checktree.model.TreeItemModel;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * NaVisu
 *
 * @author tibus
 * @param <T>
 * @date 10/12/2013 21:08
 */
public class CheckTreeCellRenderer<T extends TreeItemModel> extends TreeCell<T> {

    protected HBox container;

    protected CheckBox checkBox;
    protected ImageView icon;

    protected ContextMenu ctxMenu = new ContextMenu();

    protected boolean firstTime = true;

    protected TreeItemModel item;

    public CheckTreeCellRenderer() {

        this.container = new HBox();

        this.checkBox = new CheckBox();
        this.checkBox.setOnAction((event) -> {

            if(Selectable.isSelectable(this.item)) {

                CheckTreeItemModel selectableModel = CheckTreeItemModel.cast(this.item);
                selectableModel.setSelected(this.checkBox.isSelected());

                selectableModel.getOnSelectListeners().forEach((e) -> {
                    e.on(selectableModel);
                });
            }
        });
        
        this.icon = new ImageView();
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
                    menuItem.setOnAction((e) -> action.callback().on(item));
                    this.ctxMenu.getItems().addAll(menuItem);
                });

                if(Selectable.isSelectable(item)) {
                    this.container.getChildren().add(this.checkBox);
                }
                
                if(item.hasIcon()) {
                    this.icon.setImage(item.icon());
                    this.container.getChildren().add(this.icon);
                }
            }

            if(Selectable.isSelectable(this.item)) {
                this.checkBox.setSelected(CheckTreeItemModel.cast(this.item).selected());
            }

            setText(item.text());
            setGraphic(this.container);
            setContextMenu(this.ctxMenu);
        }
        else {
            setText(null);
            setGraphic(null);
        }
    }
}
