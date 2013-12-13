package bzh.terrevirtuelle.navisu.api.checktree;

import bzh.terrevirtuelle.navisu.api.checktree.model.TreeItemModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * NaVisu
 *
 * @author tibus
 * @date 10/12/2013 21:07
 * 
 * @param <T> 
 */
public class CheckTree<T extends TreeItemModel> extends TreeView<T> {

    protected final TreeItem<T> rootItem;
    
    public CheckTree() {

        this.rootItem = new TreeItem<>();
        this.setRoot(this.rootItem);
        this.setShowRoot(false);
        
        this.setCellFactory((tree) ->
            new CheckTreeCellRenderer()
        );
    }
    
    public TreeItem<T> root() {
        return this.rootItem;
    }
}
