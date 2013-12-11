package bzh.terrevirtuelle.navisu.api.checktree;

import javafx.scene.control.TreeView;

/**
 * NaVisu
 *
 * @author tibus
 * @date 10/12/2013 21:07
 */
public class CheckTree<T extends CheckTreeItem> extends TreeView<T> {

    public CheckTree() {

        this.setCellFactory((tree) ->
            new CheckTreeCell()
        );
    }
}
