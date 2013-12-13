package bzh.terrevirtuelle.navisu.api.checktree.model.impl;

import bzh.terrevirtuelle.navisu.api.checktree.Action;
import bzh.terrevirtuelle.navisu.api.common.Callback;
import bzh.terrevirtuelle.navisu.api.checktree.model.CheckTreeItemModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;

/**
 * NaVisu
 *
 * @author tibus
 * @date 12/12/2013 22:35
 */
public class CheckTreeItemModelImpl extends TreeItemModelImpl implements CheckTreeItemModel {

    protected List<Callback<CheckTreeItemModel>> callbacks;
    protected boolean selected;

    {
        this.callbacks = new ArrayList<>();
    }

    public CheckTreeItemModelImpl() {
        this("no-text", null, true);
    }

    public CheckTreeItemModelImpl(String text, Image icon, boolean selected, Action... actions) {
        super(text, icon, actions);
        this.selected = selected;
    }

    @Override
    public boolean selected() {
        return this.selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void setOnSelect(Callback<CheckTreeItemModel>... cb) {
        this.callbacks.addAll(Arrays.asList(cb));
    }

    @Override
    public List<Callback<CheckTreeItemModel>> getOnSelectListeners() {
        return this.callbacks;
    }
}
