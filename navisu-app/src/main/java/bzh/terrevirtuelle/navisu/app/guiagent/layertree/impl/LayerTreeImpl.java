package bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTree;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.utility.Checker;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import javafx.scene.Node;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import org.capcaval.c3.component.ComponentState;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * NaVisu
 *
 * @author tibus
 * @date 08/11/2013 23:17
 */
public class LayerTreeImpl implements LayerTree, LayerTreeServices, ComponentState {

    protected TreeView treeView;
    protected TreeItem rootItem;

    protected Map<String, CheckBoxTreeItem<String>> groupMap = new HashMap<>();

    @Override
    public void componentInitiated() {

        this.rootItem = new TreeItem();
        this.treeView = new TreeView(this.rootItem);

        this.treeView.setShowRoot(false);
        this.treeView.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
    }

    @Override
    public void createGroup(String groupName, GeoLayer<?>... layers) {

        Checker.notNull(this.groupMap, "GroupMap is null.");

        Checker.notNull(groupName, "Group name is null.");
        Checker.keyNotExistsInMap(this.groupMap, groupName, "Group named \"" + groupName + "\" already exists in layer tree.");

        CheckBoxTreeItem<String> groupItem = new CheckBoxTreeItem<>(groupName);
        this.rootItem.getChildren().add(groupItem);

        Arrays.asList(layers).forEach((geoLayer) -> {

            GeoLayerTreeItem geoLayerItem = new GeoLayerTreeItem(geoLayer);
            geoLayerItem.setExpanded(true);
            groupItem.getChildren().add(geoLayerItem);
        });
    }

    @Override
    public void addGeoLayer(String groupName, GeoLayer<?>... layers) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Display<Node> getDisplayService() {
        return Display.factory.newDisplayNode(this.treeView);
    }

    @Override
    public void componentStarted() { /* Nothing to do here */ }

    @Override
    public void componentStopped() { /* Nothing to do here */ }
}
