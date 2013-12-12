package bzh.terrevirtuelle.navisu.api.checktree;

import bzh.terrevirtuelle.navisu.api.checktree.model.TreeItemModel;
import javafx.scene.control.TreeView;

/**
 * NaVisu
 *
 * @author tibus
 * @date 10/12/2013 21:07
 */
public class CheckTree<T extends TreeItemModel> extends TreeView<T> {

    public CheckTree() {

        this.setCellFactory((tree) ->
            new CheckTreeCellRenderer()
        );
    }
}
