package bzh.terrevirtuelle.navisu.api.checktree.model.impl;

import bzh.terrevirtuelle.navisu.api.checktree.Action;
import bzh.terrevirtuelle.navisu.api.checktree.model.TreeItemModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;

/**
 * NaVisu
 *
 * @author tibus
 * @date 12/12/2013 22:33
 */
public class TreeItemModelImpl implements TreeItemModel {

    protected String text;
    protected List<Action> actions;
    protected Image icon;
    
    {
        this.actions = new ArrayList<>();
    }

    public TreeItemModelImpl() {
        this.text = "no-text";
    }

    public TreeItemModelImpl(String text, Image icon, Action... actions) {
        this.text = text;
        this.actions.addAll(Arrays.asList(actions));
        this.icon = icon;
    }

    @Override
    public String text() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public List<Action> getActions() {
        return this.actions;
    }

    @Override
    public void addActions(Action... actions) {
        this.actions.addAll(Arrays.asList(actions));
    }

    @Override
    public Image icon() {
        return this.icon;
    }

    @Override
    public void setIcon(Image image) {
        this.icon = image;
    }

    @Override
    public boolean hasIcon() {
        return this.icon != null;
    }
}
