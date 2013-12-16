package bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl;

import bzh.terrevirtuelle.navisu.api.checktree.CheckTree;
import bzh.terrevirtuelle.navisu.api.checktree.model.CheckTreeItemModel;
import bzh.terrevirtuelle.navisu.api.checktree.model.TreeItemModel;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTree;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.util.Checker;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import javafx.scene.Node;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import org.capcaval.c3.component.ComponentState;

import java.util.HashMap;
import java.util.Map;

/**
 * NaVisu
 *
 * @author tibus
 * @date 16/12/2013 22:02
 */
public class LayerCheckTreeImpl implements LayerTree, LayerTreeServices, ComponentState {

    protected CheckTree<TreeItemModel> tree;
    protected Map<String, CheckBoxTreeItem<String>> groupMap;

    @Override
    public void componentInitiated() {

        this.tree = new CheckTree<>();
        this.groupMap = new HashMap<>();
    }

    @Override
    public void createGroup(String groupName, GeoLayer<?>... layers) {

        Checker.notNull(groupName, "Group titleText is null.");
        Checker.keyNotExistsInMap(this.groupMap, groupName, "Group named \"" + groupName + "\" already exists in layer checktree.");

        final Image layerIcon = new Image(LayerCheckTreeImpl.class.getResourceAsStream("layer_icon.png"));

        final TreeItem<TreeItemModel> groupItem = new TreeItem<>(TreeItemModel.create(groupName, layerIcon));
        groupItem.setExpanded(true);
        this.tree.root().getChildren().add(groupItem);

        for(GeoLayer geoLayer : layers) {

            final TreeItemModel layerItemModel = CheckTreeItemModel.create(geoLayer.getName(), geoLayer.isVisible());
            final TreeItem<TreeItemModel> layerItem = new TreeItem<>(layerItemModel);
            groupItem.getChildren().add(layerItem);

            CheckTreeItemModel.cast(layerItemModel).setOnSelect((item) -> {
                geoLayer.setVisible(item.selected());
            });
        }
    }

    @Override
    public void addGeoLayer(String groupName, GeoLayer<?>... layers) {
        //TODO to implement !!
    }

    @Override
    public Display<Node> getDisplayService() {
        return Display.factory.newDisplayNode(tree);
    }

    @Override
    public void componentStarted() {}

    @Override
    public void componentStopped() {}
}
