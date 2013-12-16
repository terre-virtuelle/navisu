package bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTree;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl.options.LayerTreeOptionsControllerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsManagerServices;
import bzh.terrevirtuelle.navisu.core.util.Checker;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import javafx.scene.Node;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 08/11/2013 23:17
 */
public class LayerTreeImpl implements LayerTree, LayerTreeServices, ComponentState {

    protected static final Logger LOGGER = Logger.getLogger(LayerTreeImpl.class.getName());

    @UsedService OptionsManagerServices optionsManagerServices;

    protected BorderPane container;

    protected TreeView treeView;
    protected TreeItem rootItem;

    protected Map<String, CheckBoxTreeItem<String>> groupMap = new HashMap<>();

    @Override
    public void componentInitiated() {

        this.container = new BorderPane();
        //this.container.setMaxWidth(250);

        this.container.getStylesheets().add(LayerTreeImpl.class.getResource("LayerTree.css").toExternalForm());

        this.rootItem = new TreeItem();
        this.treeView = new TreeView(this.rootItem);

        this.container.setCenter(this.treeView);

        this.treeView.setShowRoot(false);
        this.treeView.setCellFactory(CheckBoxTreeCell.<String>forTreeView());

        this.optionsManagerServices.add(new LayerTreeOptionsControllerImpl());
    }

    @Override
    public void createGroup(String groupName, GeoLayer<?>... layers) {

        Checker.notNull(groupName, "Group titleText is null.");
        Checker.keyNotExistsInMap(this.groupMap, groupName, "Group named \"" + groupName + "\" already exists in layer checktree.");

        CheckBoxTreeItem<String> groupItem = new CheckBoxTreeItem<>(groupName);
        this.rootItem.getChildren().add(groupItem);

        for(GeoLayer geoLayer : layers) {

            GeoLayerTreeItem geoLayerItem = new GeoLayerTreeItem(geoLayer);

            groupItem.setExpanded(true);
            groupItem.getChildren().add(geoLayerItem);
        }
    }

    @Override
    public void addGeoLayer(String groupName, GeoLayer<?>... layers) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Display<Node> getDisplayService() {
        return Display.factory.newDisplayNode(this.container);
    }

    @Override
    public void componentStarted() { /* Nothing to do here */ }

    @Override
    public void componentStopped() { /* Nothing to do here */ }
}
