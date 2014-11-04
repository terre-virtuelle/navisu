package bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTree;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.LayerManager;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.impl.WorldWindGeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWindView;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;
import org.capcaval.c3.component.ComponentState;

/**
 * NaVisu
 *
 * @author Jordan Modified Serge Morvan
 * @date 23/02/2014 17:46
 */
public class LayerCheckTreeImpl
        implements LayerTree, LayerTreeServices, ComponentState {

    private TreeView<GeoLayer> treeView;
    protected GeoWorldWindView geoView;
    protected LayerManager<Layer> layerManager;
    private CheckBoxTreeItem<GeoLayer> rootItem0;
    private List<CheckBoxTreeItem<GeoLayer>> rootItems;

    @Override
    public void componentInitiated() {

        this.rootItem0 = new CheckBoxTreeItem<>();
        this.treeView = new TreeView<>(rootItem0);
        rootItems = new ArrayList<>();
        this.geoView = bzh.terrevirtuelle.navisu.core.view.geoview.GeoView.factory.newWorldWindGeo3DView();
        this.layerManager = this.geoView.getLayerManager();
        this.rootItem0.setExpanded(true);

        this.treeView.setShowRoot(false);
        this.treeView.setCellFactory(CheckBoxTreeCell.<GeoLayer>forTreeView((TreeItem<GeoLayer> objectTreeItem) -> new SimpleBooleanProperty(objectTreeItem.getValue().isVisible()), new StringConverter<TreeItem<GeoLayer>>() {
            @Override
            public String toString(TreeItem<GeoLayer> geoLayerTreeItem) {
                return geoLayerTreeItem.getValue().getName();
            }

            @Override
            public TreeItem<GeoLayer> fromString(String s) {
                return null;
            }
        }));
        rootItems.add(createNode(rootItem0, "On-earth layers", "16x16-icon-earth.png"));
        rootItems.add(createNode(rootItem0, "On-screen layers", "boussole.png"));
        CheckBoxTreeItem<GeoLayer> charts = createNode(rootItem0, "Charts", "charts-16x16.png");
        rootItems.add(charts);
        CheckBoxTreeItem<GeoLayer> raster = createNode(charts, "Raster charts", null);
        rootItems.add(raster);
        CheckBoxTreeItem<GeoLayer> tmp = createNode(raster, "BSB/KAP charts", null);
        rootItems.add(tmp);
        tmp = createNode(raster, "GeoTiff charts", null);
        rootItems.add(tmp);
        CheckBoxTreeItem<GeoLayer> vector = createNode(charts, "Vector charts", null);
        rootItems.add(vector);
        tmp = createNode(vector, "S57 charts", null);
        rootItems.add(tmp);
        tmp = createNode(vector, "S57 catalog", null);
        rootItems.add(tmp);
        CheckBoxTreeItem<GeoLayer> kml = createNode(rootItem0, "KML files", null);
        rootItems.add(kml);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {

    }

    @Override
    public void createGroup(String groupName, GeoLayer<?>... layers) {
        for (GeoLayer geoLayer : layers) {
            System.out.println(groupName + "  geoLayer " + geoLayer.getName());
            CheckBoxTreeItem<GeoLayer> treeItem;
            treeItem = new CheckBoxTreeItem<>(geoLayer);
            treeItem.setSelected(geoLayer.isVisible());
            treeItem.selectedProperty().addListener((ObservableValue<? extends Boolean> obs, Boolean oldValue, Boolean newValue) -> {
                geoLayer.setVisible(newValue);

                if (geoLayer instanceof Layer) {
                    ((Layer) geoLayer.getDisplayLayer()).setEnabled(newValue);
                }
            });
            System.out.println("treeItem " + search(groupName));
            search(groupName).getChildren().add(treeItem);
        }
    }

    @Override
    public CheckBoxTreeItem<GeoLayer> createNode(CheckBoxTreeItem<GeoLayer> root, String nodeName, String iconName) {
        GeoLayer geoLayer = new WorldWindGeoLayer(new RenderableLayer());
        geoLayer.setName(nodeName);
        CheckBoxTreeItem<GeoLayer> treeItem = null;
        if (iconName != null) {
            treeItem = new CheckBoxTreeItem<>(geoLayer, getIcon(iconName));
        } else {
            treeItem = new CheckBoxTreeItem<>(geoLayer);
        }
        treeItem.setSelected(geoLayer.isVisible());
        treeItem.selectedProperty().addListener((ObservableValue<? extends Boolean> obs, Boolean oldValue, Boolean newValue) -> {
            geoLayer.setVisible(newValue);

            if (geoLayer instanceof Layer) {
                ((Layer) geoLayer.getDisplayLayer()).setEnabled(newValue);
            }
        });
        layerManager.createGroup(nodeName, geoLayer);
        root.getChildren().add(treeItem);
        return treeItem;
    }

    @Override
    public void addGeoLayer(String groupName, GeoLayer<?>... layers) {
        createGroup(groupName, layers);
    }

    @Override
    public Display<Node> getDisplayService() {
        return Display.factory.newDisplayNode(treeView);
    }

    private Node getIcon(String name) {
        return new ImageView(new Image(LayerCheckTreeImpl.class.getResourceAsStream(name)));
    }

    private CheckBoxTreeItem<GeoLayer> search(String name) {
        CheckBoxTreeItem<GeoLayer> result = null;
        for (CheckBoxTreeItem<GeoLayer> cb : rootItems) {
            if (cb.getValue().getName().equals(name)) {
                result = cb;
            }
        }
        return result;
    }

}
