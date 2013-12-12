package bzh.terrevirtuelle.navisu.api.checktree.model;

import bzh.terrevirtuelle.navisu.api.checktree.Action;
import bzh.terrevirtuelle.navisu.api.checktree.model.impl.TreeItemModelImpl;

import java.util.List;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/12/2013 22:20
 */
public interface TreeItemModel {

    String text();
    void setText(String text);

    List<Action> getActions();
    void         addActions(Action... actions);

    public static TreeItemModel create(String text, Action... actions) {
        return new TreeItemModelImpl(text, actions);
    }
}
