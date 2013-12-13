package bzh.terrevirtuelle.navisu.api.checktree.model;

import bzh.terrevirtuelle.navisu.api.checktree.Action;
import bzh.terrevirtuelle.navisu.api.common.Callback;
import bzh.terrevirtuelle.navisu.api.common.Selectable;
import bzh.terrevirtuelle.navisu.api.checktree.model.impl.CheckTreeItemModelImpl;

import java.util.List;
import javafx.scene.image.Image;

/**
 * NaVisu
 *
 * @author tibus
 * @date 12/12/2013 22:35
 */
public interface CheckTreeItemModel extends TreeItemModel, Selectable {

    void setOnSelect(Callback<CheckTreeItemModel>... cb);
    List<Callback<CheckTreeItemModel>> getOnSelectListeners();


    public static CheckTreeItemModel create(String text, boolean selected, Action... actions) {
        return new CheckTreeItemModelImpl(text, null, selected, actions);
    }
    
    public static CheckTreeItemModel create(String text, Image icon, boolean selected, Action... actions) {
        return new CheckTreeItemModelImpl(text, icon, selected, actions);
    }

    public static CheckTreeItemModel cast(TreeItemModel orig) {

        if(Selectable.isSelectable(orig)) {
            return (CheckTreeItemModel) orig;
        }

        throw new ClassCastException("CheckTreeItemModel is not selectable !");
    }
}
