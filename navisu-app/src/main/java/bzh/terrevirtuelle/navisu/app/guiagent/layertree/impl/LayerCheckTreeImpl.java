package bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTree;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import gov.nasa.worldwind.layers.Layer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.capcaval.c3.component.ComponentState;

/**
 * NaVisu
 *
 * @author Jordan
 * @date 23/02/2014 17:46
 */
public class LayerCheckTreeImpl implements LayerTree, LayerTreeServices, ComponentState {

    private TreeView<GeoLayer> treeView;

    private CheckBoxTreeItem<GeoLayer> rootItem;

    @Override
    public void componentInitiated() {

        this.rootItem = new CheckBoxTreeItem<>();
        this.treeView = new TreeView<>(rootItem);
        this.rootItem.setExpanded(true);
        this.treeView.setShowRoot(false);
        this.treeView.setCellFactory(CheckBoxTreeCell.<GeoLayer>forTreeView(new Callback<TreeItem<GeoLayer>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TreeItem<GeoLayer> objectTreeItem) {
                return new SimpleBooleanProperty(objectTreeItem.getValue().isVisible());
            }
        } , new StringConverter<TreeItem<GeoLayer>>() {
            @Override
            public String toString(TreeItem<GeoLayer> geoLayerTreeItem) {
                return geoLayerTreeItem.getValue().getName();
            }

            @Override
            public TreeItem<GeoLayer> fromString(String s) {
                return null;
            }
        }));
    }

    @Override
    public void componentStarted() {

    }

    @Override
    public void componentStopped() {

    }

    @Override
    public void createGroup(String groupName, GeoLayer<?>... layers) {

        final Image layerIcon = new Image(LayerCheckTreeImpl.class.getResourceAsStream("layer_icon.png"));

        for(GeoLayer geoLayer : layers) {

            final ImageView icon = new ImageView(layerIcon);

            CheckBoxTreeItem<GeoLayer> treeItem = new CheckBoxTreeItem<>(geoLayer, icon);

            treeItem.setSelected(geoLayer.isVisible());
            treeItem.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> obs, Boolean oldValue, Boolean newValue) {
                    geoLayer.setVisible(newValue);

                    if(geoLayer instanceof Layer) {
                        ((Layer)geoLayer.getDisplayLayer()).setEnabled(newValue);
                    }
                }
            });
            this.rootItem.getChildren().add(treeItem);
        }
    }

    @Override
    public void addGeoLayer(String groupName, GeoLayer<?>... layers) {

    }

    @Override
    public Display<Node> getDisplayService() {
       return Display.factory.newDisplayNode(treeView);
    }
}
