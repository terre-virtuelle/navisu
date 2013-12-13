package bzh.terrevirtuelle.navisu.api.checktree.model;

import bzh.terrevirtuelle.navisu.api.checktree.Action;
import bzh.terrevirtuelle.navisu.api.checktree.model.impl.TreeItemModelImpl;

import java.util.List;
import javafx.scene.image.Image;

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

    Image icon();
    void  setIcon(Image image);
    boolean hasIcon();
    
    public static TreeItemModel create(String text, Action... actions) {
        return new TreeItemModelImpl(text, null, actions);
    }
    
    public static TreeItemModel create(String text, Image icon, Action... actions) {
        return new TreeItemModelImpl(text, icon, actions);
    }
}
