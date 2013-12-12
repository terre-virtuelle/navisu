package bzh.terrevirtuelle.navisu.api.checktree.model;

import bzh.terrevirtuelle.navisu.api.checktree.Action;
import bzh.terrevirtuelle.navisu.api.checktree.Callback;
import bzh.terrevirtuelle.navisu.api.checktree.Selectable;
import bzh.terrevirtuelle.navisu.api.checktree.model.impl.CheckTreeItemModelImpl;

import java.util.List;

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
        return new CheckTreeItemModelImpl(text, selected, actions);
    }

    public static CheckTreeItemModel cast(TreeItemModel orig) {

        if(Selectable.isSelectable(orig)) {
            return (CheckTreeItemModel) orig;
        }

        throw new ClassCastException("CheckTreeItemModel is not selectable !");
    }
}
